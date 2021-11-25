package br.com.spotifyjvcw.usecase.impl;

import br.com.spotifyjvcw.domain.Token;
import br.com.spotifyjvcw.exception.especific.InternalServerErrorException;
import br.com.spotifyjvcw.exception.especific.NotFoundException;
import br.com.spotifyjvcw.gateway.TokenDBGateway;
import br.com.spotifyjvcw.host.data.request.RefreshTokenRequest;
import br.com.spotifyjvcw.host.data.request.TokenRequest;
import br.com.spotifyjvcw.usecase.TokenInteractionsWithDB;
import br.com.spotifyjvcw.usecase.converter.TokenRequestToTokenDomainConverter;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.stereotype.Component;

import static java.util.Objects.isNull;

@Component
@RequiredArgsConstructor
@Log
public class TokenInteractionsWithDBImpl implements TokenInteractionsWithDB {

    private final TokenDBGateway tokenDBGateway;
    private final TokenRequestToTokenDomainConverter tokenRequestToTokenDomainConverter;

    @Override
    public Token getTokenByClientId(String clientId) {
        try {
            Token token = tokenDBGateway.getTokenByClientId(clientId);

            if(isNull(token))
                throw new NotFoundException("Token não encontrado!");
            return token;
        }
        catch (Exception e){
            log.severe(e.getMessage());
            throw new InternalServerErrorException(e.getMessage(), e.getCause());
        }
    }

    @Override
    public String saveNewClientId(TokenRequest tokenRequest) {
        try {
            return tokenDBGateway.saveClientId(tokenRequestToTokenDomainConverter
                    .exeute(tokenRequest)).getClientId();
        }
        catch (Exception e){
            log.severe(e.getMessage());
            throw new InternalServerErrorException(e.getMessage(), e.getCause());
        }
    }

    @Override
    public void refreshToken(String clientId, RefreshTokenRequest refreshTokenRequest) {
        try {
            tokenDBGateway.refreshToken(clientId, refreshTokenRequest.getRefreshToken());
        }
        catch (Exception e){
            log.severe(e.getMessage());
            throw new InternalServerErrorException(e.getMessage(), e.getCause());
        }
    }
}
