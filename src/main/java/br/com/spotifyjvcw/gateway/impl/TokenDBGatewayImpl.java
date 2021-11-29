package br.com.spotifyjvcw.gateway.impl;

import br.com.spotifyjvcw.domain.Token;
import br.com.spotifyjvcw.gateway.TokenDBGateway;
import br.com.spotifyjvcw.gateway.converter.TokenEntityDomainConverter;
import br.com.spotifyjvcw.gateway.entity.TokenEntity;
import br.com.spotifyjvcw.gateway.repository.TokenReposiory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class TokenDBGatewayImpl implements TokenDBGateway {

    private final TokenReposiory tokenReposiory;
    private final TokenEntityDomainConverter entityDomainConverter;

    @Override
    public Token getTokenByClientId(String clientId) {
        Optional<TokenEntity> opToken = tokenReposiory.findById(clientId);

        if(opToken.isEmpty())
            return null;

        return entityDomainConverter.entityToDomain(opToken.get());
    }

    @Override
    public Token saveClientId(Token token) {
        return  entityDomainConverter
                    .entityToDomain(tokenReposiory
                        .save(entityDomainConverter.domainToEntity(token)));
    }

    @Override
    public void refreshToken(String clientId, String refresToken) {
        tokenReposiory.save(TokenEntity.builder()
                    .refreshToken(refresToken)
                    .clientId(clientId).build());
    }

    @Override
    public List<Token> getAll() {
        return entityDomainConverter.entityToDomain(tokenReposiory.findAll());
    }
}
