package br.com.spotifyjvcw.host;

import br.com.spotifyjvcw.exception.EventExceptionHandler;
import br.com.spotifyjvcw.host.converter.TrackDomainToTrackResponseConverter;
import br.com.spotifyjvcw.host.data.request.TrackRequest;
import br.com.spotifyjvcw.host.data.response.TrackResponse;
import br.com.spotifyjvcw.usecase.TrackInteractionsWithDB;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/tracks")
@Validated
public class TrackEndpoint {

    private final TrackInteractionsWithDB trackInteractionsWithDB;
    private final TrackDomainToTrackResponseConverter trackDomainToTrackResponseConverter;

    @Operation(tags = "Tracks", description = "Traz todos os tracks", responses = {
            @ApiResponse(responseCode = "200", description = "successful operation", content = @Content( mediaType = "application/json",
                    array = @ArraySchema( schema = @Schema(implementation = TrackResponse.class))))
    })
    @GetMapping
    public List<TrackResponse> getAllTracks(){
        return trackDomainToTrackResponseConverter.execute(trackInteractionsWithDB.getAll());
    }

    @Operation(tags = "Tracks", description = "Traz um object TrackResponse a partir de clientId e uma data", responses = {
            @ApiResponse(responseCode = "200", description = "successful operation", content = @Content( mediaType = "application/json",
                    array = @ArraySchema( schema = @Schema(implementation = TrackResponse.class)))),
            @ApiResponse(responseCode = "400", description = "Invalid Request", content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = EventExceptionHandler.Error.class))),
            @ApiResponse(responseCode = "404", description = "Page not found", content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = EventExceptionHandler.Error.class))),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = EventExceptionHandler.Error.class)))
    })
    @GetMapping("/{clientId}/{date}")
    public ResponseEntity<TrackResponse> getTrackByDate(@Valid
            @NotBlank @PathVariable String clientId,
            @NotNull @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date){
        return ResponseEntity.ok(trackDomainToTrackResponseConverter
                .execute(trackInteractionsWithDB.getByDate(date, clientId)));
    }

    @Operation(tags = "Tracks", description = "Salva uma lista de Tracks para um clientId", responses = {
            @ApiResponse(responseCode = "200", description = "successful operation", content = @Content( mediaType = "application/json",
                    array = @ArraySchema( schema = @Schema(implementation = TrackResponse.class)))),
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
            @NotNull @RequestBody List<TrackRequest> tracksRequest){
        trackInteractionsWithDB.saveAll(tracksRequest, clientId);
        return ResponseEntity.ok().build();
    }
}
