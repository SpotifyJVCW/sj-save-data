package br.com.spotifyjvcw.host;

import br.com.spotifyjvcw.exception.EventExceptionHandler;
import br.com.spotifyjvcw.host.converter.TokenDomainToResponseConverter;
import br.com.spotifyjvcw.host.data.request.RefreshTokenRequest;
import br.com.spotifyjvcw.host.data.request.TokenRequest;
import br.com.spotifyjvcw.host.data.response.TokenResponse;
import br.com.spotifyjvcw.usecase.TokenInteractionsWithDB;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


@RestController
@RequiredArgsConstructor
@RequestMapping("/tokens")
@Validated
public class TokenEndpoint {

    private final TokenInteractionsWithDB tokenInteractionsWithDB;
    private final TokenDomainToResponseConverter tokenDomainToResponseConverter;

    @Operation(tags = "Token", description = "Traz um object TokenResponse a partir de clientId", responses = {
            @ApiResponse(responseCode = "200", description = "successful operation", content = @Content( mediaType = "application/json",
                    array = @ArraySchema( schema = @Schema(implementation = TokenResponse.class)))),
            @ApiResponse(responseCode = "400", description = "Invalid Request", content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = EventExceptionHandler.Error.class))),
            @ApiResponse(responseCode = "404", description = "Page not found", content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = EventExceptionHandler.Error.class))),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = EventExceptionHandler.Error.class)))
    })
    @GetMapping("/{clientId}")
    public ResponseEntity<TokenResponse> getTokenByClientId(@Valid
                @NotBlank @PathVariable String clientId){
        return ResponseEntity.ok(tokenDomainToResponseConverter
                .execute(tokenInteractionsWithDB.getTokenByClientId(clientId)));
    }

    @Operation(tags = "Token", description = "Registra um novo clientId, accessToken e refreshToken", responses = {
            @ApiResponse(responseCode = "200", description = "successful operation", content = @Content( mediaType = "application/json",
                    array = @ArraySchema( schema = @Schema(implementation = TokenResponse.class)))),
            @ApiResponse(responseCode = "400", description = "Invalid Request", content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = EventExceptionHandler.Error.class))),
            @ApiResponse(responseCode = "404", description = "Page not found", content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = EventExceptionHandler.Error.class))),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = EventExceptionHandler.Error.class)))
    })
    @PostMapping
    public ResponseEntity<Void> insertToken(@Valid
                @RequestBody TokenRequest tokenRequest, HttpServletRequest request,
                UriComponentsBuilder builder){
        return ResponseEntity.created(builder.path(request.getRequestURI() + "/" +
                tokenInteractionsWithDB.saveNewClientId(tokenRequest)).build().toUri()).build();
    }

    @Operation(tags = "Token", description = "Atualiza um refreshToken a partir de um clientId", responses = {
            @ApiResponse(responseCode = "200", description = "successful operation", content = @Content( mediaType = "application/json",
                    array = @ArraySchema( schema = @Schema(implementation = TokenResponse.class)))),
            @ApiResponse(responseCode = "400", description = "Invalid Request", content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = EventExceptionHandler.Error.class))),
            @ApiResponse(responseCode = "404", description = "Page not found", content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = EventExceptionHandler.Error.class))),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = EventExceptionHandler.Error.class)))
    })
    @PostMapping("/refresh/{clientId}")
    public ResponseEntity<Void> updateRefreshToken(@Valid
                @NotBlank @PathVariable String clientId,
                @NotNull @RequestBody RefreshTokenRequest refreshTokenRequest){
        tokenInteractionsWithDB.refreshToken(clientId, refreshTokenRequest);
        return ResponseEntity.ok().build();
    }
}
