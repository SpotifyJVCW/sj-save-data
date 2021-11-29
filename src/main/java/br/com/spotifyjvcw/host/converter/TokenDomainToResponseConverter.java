package br.com.spotifyjvcw.host.converter;

import br.com.spotifyjvcw.domain.Token;
import br.com.spotifyjvcw.host.data.response.TokenResponse;

import java.util.List;

public interface TokenDomainToResponseConverter {

    List<TokenResponse> execute(List<Token> tokens);

    TokenResponse execute(Token token);
}
