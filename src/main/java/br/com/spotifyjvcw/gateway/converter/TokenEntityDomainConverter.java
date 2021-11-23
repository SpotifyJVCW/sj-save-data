package br.com.spotifyjvcw.gateway.converter;

import br.com.spotifyjvcw.domain.Token;
import br.com.spotifyjvcw.gateway.entity.TokenEntity;

public interface TokenEntityDomainConverter {

    Token entityToDomain(TokenEntity tokenEntity);

    TokenEntity domainToEntity(Token tokenDomain);
}
