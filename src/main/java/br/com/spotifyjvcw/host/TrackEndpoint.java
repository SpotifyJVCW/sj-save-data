package br.com.spotifyjvcw.host;

import br.com.spotifyjvcw.domain.Track;
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
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/tracks")
@Validated
public class TrackEndpoint {

    private final TrackInteractionsWithDB trackInteractionsWithDB;
    private final TrackDomainToTrackResponseConverter trackDomainToTrackResponseConverter;

    @Operation(tags = "Reports", description = "Generate Elk reports of preApproved", responses = {
            @ApiResponse(responseCode = "200", description = "successful operation", content = @Content( mediaType = "application/json",
                    array = @ArraySchema( schema = @Schema(implementation = TrackResponse.class)))),
            @ApiResponse(responseCode = "400", description = "Invalid Request", content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = TrackResponse.class))),
            @ApiResponse(responseCode = "404", description = "Page not found", content = @Content(mediaType = "application/json"))
    })
    @GetMapping
    public List<TrackResponse> getAllTracks(){
        return trackDomainToTrackResponseConverter.execute(trackInteractionsWithDB.getAll());
    }

    @GetMapping("/{clientId}/{date}")
    public ResponseEntity<TrackResponse> getTrackByDate(@Valid
            @NotNull @PathVariable String clientId,
            @NotNull @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date){
        Optional<Track> track = trackInteractionsWithDB.getByDate(date, clientId);

        if(track.isEmpty())
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(trackDomainToTrackResponseConverter.execute(track.get()));
    }

    @PostMapping("/{clientId}")
    public ResponseEntity<Void> saveAll(@Valid
            @NotNull @PathVariable String clientId,
            @NotNull @RequestBody List<TrackRequest> tracksRequest){
        trackInteractionsWithDB.saveAll(tracksRequest, clientId);
        return ResponseEntity.ok().build();
    }
}
