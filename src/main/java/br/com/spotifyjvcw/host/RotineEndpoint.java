package br.com.spotifyjvcw.host;

import br.com.spotifyjvcw.usecase.SendMail;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<Void> sendMailWhenChanged(@PathVariable String clientId, @RequestParam(required = false) String sendEmail){
        sendMail.whenChangePositions(clientId, sendEmail);
        return ResponseEntity.ok().build();
    }
}
