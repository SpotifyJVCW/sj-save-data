package br.com.spotifyjvcw.host;

import br.com.spotifyjvcw.usecase.SendMail;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/rotine")
public class RotineEndpoint {

    private final SendMail sendMail;

    @Operation(tags = "Rotine", description = "Envia e-mail caso haja mudança na base", responses = {
            @ApiResponse(responseCode = "200", description = "successful operation")
    })
    @GetMapping("/mail/{clientId}")
    public ResponseEntity<Void> sendMailWhenChanged(@PathVariable String clientId){
        sendMail.whenChangePositions(clientId);
        return ResponseEntity.ok().build();
    }
}
