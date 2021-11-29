package br.com.spotifyjvcw.host.data.request;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TokenRequest {

    @NotBlank
    @NotNull
    private String clientId;
    @NotNull
    @NotBlank
    private String accessToken;
    @NotNull
    @NotBlank
    private String refreshToken;
}
