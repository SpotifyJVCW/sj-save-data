package br.com.spotifyjvcw.gateway.converter.impl;

import br.com.spotifyjvcw.domain.Token;
import br.com.spotifyjvcw.gateway.converter.TokenEntityDomainConverter;
import br.com.spotifyjvcw.gateway.entity.TokenEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;

@Component
public class TokenEntityDomainConverterImpl implements TokenEntityDomainConverter {

    @Override
    public List<Token> entityToDomain(List<TokenEntity> tokenEntities) {
        return tokenEntities.stream().map(this::entityToDomain).collect(Collectors.toList());
    }

    @Override
    public Token entityToDomain(TokenEntity tokenEntity) {
        if(isNull(tokenEntity))
            return null;

        return Token.builder()
                .accessToken(tokenEntity.getAccessToken())
                .clientId(tokenEntity.getClientId())
                .refreshToken(tokenEntity.getRefreshToken()).build();
    }

    @Override
    public TokenEntity domainToEntity(Token tokenDomain) {
        if(isNull(tokenDomain))
            return null;

        return TokenEntity.builder()
                .accessToken(tokenDomain.getAccessToken())
                .clientId(tokenDomain.getClientId())
                .refreshToken(tokenDomain.getRefreshToken()).build();
    }


}
