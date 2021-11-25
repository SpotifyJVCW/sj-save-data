package br.com.spotifyjvcw.usecase;

import br.com.spotifyjvcw.domain.Token;
import br.com.spotifyjvcw.host.data.request.RefreshTokenRequest;
import br.com.spotifyjvcw.host.data.request.TokenRequest;

public interface TokenInteractionsWithDB {

    Token getTokenByClientId(String clientId);

    String saveNewClientId(TokenRequest tokenRequest);

    void refreshToken(String clientId, RefreshTokenRequest refreshTokenRequest);
}
