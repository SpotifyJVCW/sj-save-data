package br.com.spotifyjvcw.host.data.request;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TokenRequest {

    private String clientId;
    private String accessToken;
    private String refreshToken;
}
