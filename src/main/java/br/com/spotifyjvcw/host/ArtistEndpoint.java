package br.com.spotifyjvcw.host;

import br.com.spotifyjvcw.domain.TermSearch;
import br.com.spotifyjvcw.exception.EventExceptionHandler;
import br.com.spotifyjvcw.host.converter.ArtistDomainToArtistResponseConverter;
import br.com.spotifyjvcw.host.data.request.ArtistRequest;
import br.com.spotifyjvcw.host.data.response.ArtistResponse;
import br.com.spotifyjvcw.usecase.ArtistInteractionsWithDB;
import br.com.spotifyjvcw.usecase.CreateCsv;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

import static br.com.spotifyjvcw.host.header.HeaderConstruct.constructFileResponse;
import static java.time.format.DateTimeFormatter.ISO_DATE;

@RestController
@RequiredArgsConstructor
@RequestMapping("/artists")
@Validated
public class ArtistEndpoint {

    private final ArtistInteractionsWithDB artistInteractionsWithDB;
    private final ArtistDomainToArtistResponseConverter artistDomainToArtistResponseConverter;
    private final CreateCsv createCsv;

    @Operation(tags = "Artists", description = "Traz todos os Artists", responses = {
            @ApiResponse(responseCode = "200", description = "successful operation", content = @Content( mediaType = "application/json",
                    array = @ArraySchema( schema = @Schema(implementation = ArtistResponse.class))))
    })
    @GetMapping
    public List<ArtistResponse> getAllArtists(){
        return artistDomainToArtistResponseConverter.execute(artistInteractionsWithDB.getAll());
    }

    @Operation(tags = "Artists", description = "Traz um object ArtistResponse a partir de clientId e uma data", responses = {
            @ApiResponse(responseCode = "200", description = "successful operation", content = @Content( mediaType = "application/json",
                    array = @ArraySchema( schema = @Schema(implementation = ArtistResponse.class)))),
            @ApiResponse(responseCode = "400", description = "Invalid Request", content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = EventExceptionHandler.Error.class))),
            @ApiResponse(responseCode = "404", description = "Page not found", content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = EventExceptionHandler.Error.class))),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = EventExceptionHandler.Error.class)))
    })
    @GetMapping("/{clientId}/{date}")
    public ResponseEntity<ArtistResponse> getArtistByDate(@Valid
            @NotBlank @PathVariable String clientId,
            @NotNull @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date){
        return ResponseEntity.ok(artistDomainToArtistResponseConverter
                .execute(artistInteractionsWithDB.getByDate(date, clientId)));
    }

    @Operation(tags = "Artists", description = "Salva uma lista de Artists para um clientId", responses = {
            @ApiResponse(responseCode = "200", description = "successful operation", content = @Content( mediaType = "application/json",
                    array = @ArraySchema( schema = @Schema(implementation = ArtistResponse.class)))),
            @ApiResponse(responseCode = "400", description = "Invalid Request", content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = EventExceptionHandler.Error.class))),
            @ApiResponse(responseCode = "404", description = "Page not found", content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = EventExceptionHandler.Error.class))),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = EventExceptionHandler.Error.class)))
    })
    @PostMapping("/{clientId}")
    public ResponseEntity<Void> saveAll(@Valid
            @NotBlank @PathVariable String clientId,
            @NotNull @RequestBody List<ArtistRequest> artistsRequest){
        artistInteractionsWithDB.saveAll(artistsRequest, clientId);
        return ResponseEntity.ok().build();
    }

    @Operation(tags = "Artists", description = "Cria um CSV de Artists para um clientId em um determinado periodo", responses = {
            @ApiResponse(responseCode = "200", description = "successful operation", content = @Content( mediaType = "application/json",
                    array = @ArraySchema( schema = @Schema(implementation = ArtistResponse.class)))),
            @ApiResponse(responseCode = "400", description = "Invalid Request", content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = EventExceptionHandler.Error.class))),
            @ApiResponse(responseCode = "404", description = "Page not found", content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = EventExceptionHandler.Error.class))),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = EventExceptionHandler.Error.class)))
    })
    @PostMapping("/download-csv/{clientId}")
    public ResponseEntity<ByteArrayResource> createCsv(@Valid
                                            @NotBlank @PathVariable String clientId,
                                            @NotNull @RequestParam TermSearch termSearch,
                                            @RequestParam LocalDate csvDate){
        return constructFileResponse(String.format("%s-%s.csv", termSearch.toString(), csvDate.format(ISO_DATE)), createCsv.generateArtist(termSearch, clientId, csvDate));
    }
}
