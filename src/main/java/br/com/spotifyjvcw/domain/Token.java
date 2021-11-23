package br.com.spotifyjvcw.domain;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Token {

    private String clientId;

    private String accessToken;
    private String refreshToken;
}
