package br.com.spotifyjvcw.host.data.response;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TokenResponse {

    private String clientId;
    private String accessToken;
    private String refreshToken;
}
