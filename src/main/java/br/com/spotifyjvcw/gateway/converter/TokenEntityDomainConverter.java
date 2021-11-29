package br.com.spotifyjvcw.gateway.converter;

import br.com.spotifyjvcw.domain.Token;
import br.com.spotifyjvcw.gateway.entity.TokenEntity;

import java.util.List;

public interface TokenEntityDomainConverter {

    Token entityToDomain(TokenEntity tokenEntity);

    TokenEntity domainToEntity(Token tokenDomain);

    List<Token> entityToDomain(List<TokenEntity> tokenEntities);
}
