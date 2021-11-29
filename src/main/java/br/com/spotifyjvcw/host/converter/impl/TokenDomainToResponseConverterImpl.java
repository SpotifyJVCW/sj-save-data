package br.com.spotifyjvcw.host.converter.impl;

import br.com.spotifyjvcw.domain.Token;
import br.com.spotifyjvcw.host.converter.TokenDomainToResponseConverter;
import br.com.spotifyjvcw.host.data.response.TokenResponse;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class TokenDomainToResponseConverterImpl implements TokenDomainToResponseConverter {
    @Override
    public List<TokenResponse> execute(List<Token> tokens) {
        return tokens.stream().map(this::execute).collect(Collectors.toList());
    }

    @Override
    public TokenResponse execute(Token token) {
        return TokenResponse.builder()
                .accessToken(token.getAccessToken())
                .clientId(token.getClientId())
                .refreshToken(token.getRefreshToken())
                .build();
    }
}
