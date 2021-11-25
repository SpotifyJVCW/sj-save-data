package br.com.spotifyjvcw.usecase.converter.impl;

import br.com.spotifyjvcw.domain.Token;
import br.com.spotifyjvcw.host.data.request.TokenRequest;
import br.com.spotifyjvcw.usecase.converter.TokenRequestToTokenDomainConverter;
import org.springframework.stereotype.Component;

@Component
public class TokenRequestToTokenDomainConverterImpl implements TokenRequestToTokenDomainConverter {
    @Override
    public Token exeute(TokenRequest tokenRequest) {
        return Token.builder()
                .clientId(tokenRequest.getClientId())
                .refreshToken(tokenRequest.getRefreshToken())
                .accessToken(tokenRequest.getAccessToken()).build();
    }
}
