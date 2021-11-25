package br.com.spotifyjvcw.host.converter;

import br.com.spotifyjvcw.domain.Token;
import br.com.spotifyjvcw.host.data.response.TokenResponse;

public interface TokenDomainToResponseConverter {

    TokenResponse execute(Token token);
}
