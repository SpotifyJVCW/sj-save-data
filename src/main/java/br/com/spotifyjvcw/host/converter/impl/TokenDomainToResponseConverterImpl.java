package br.com.spotifyjvcw.host.converter.impl;

import br.com.spotifyjvcw.domain.Token;
import br.com.spotifyjvcw.host.converter.TokenDomainToResponseConverter;
import br.com.spotifyjvcw.host.data.response.TokenResponse;
import org.springframework.stereotype.Component;

@Component
public class TokenDomainToResponseConverterImpl implements TokenDomainToResponseConverter {
    @Override
    public TokenResponse execute(Token token) {
        return TokenResponse.builder()
                .accessToken(token.getAccessToken())
                .refreshToken(token.getRefreshToken())
                .build();
    }
}
