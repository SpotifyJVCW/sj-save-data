package br.com.spotifyjvcw.gateway;

import br.com.spotifyjvcw.domain.Token;

public interface TokenDBGateway {

    Token getTokenByClientId(String clientId);

    Token saveClientId(Token token);

    void refreshToken(String clientId, String refresToken);
}
