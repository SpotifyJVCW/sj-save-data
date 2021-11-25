package br.com.spotifyjvcw.usecase.converter;

import br.com.spotifyjvcw.domain.Token;
import br.com.spotifyjvcw.host.data.request.TokenRequest;

public interface TokenRequestToTokenDomainConverter {

    Token exeute(TokenRequest tokenRequest);
}
