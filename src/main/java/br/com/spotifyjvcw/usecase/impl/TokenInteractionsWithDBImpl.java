package br.com.spotifyjvcw.usecase.impl;

import br.com.spotifyjvcw.domain.Token;
import br.com.spotifyjvcw.exception.especific.ClientAlreadyExistsException;
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

import java.util.List;

import static java.util.Objects.isNull;

@Component
@RequiredArgsConstructor
@Log
public class TokenInteractionsWithDBImpl implements TokenInteractionsWithDB {

    private final TokenDBGateway tokenDBGateway;
    private final TokenRequestToTokenDomainConverter tokenRequestToTokenDomainConverter;

    @Override
    public List<Token> getAllTokens() {
        return tokenDBGateway.getAll();
    }

    @Override
    public Token getTokenByClientId(String clientId) {
        Token token;
        try {
            token = tokenDBGateway.getTokenByClientId(clientId);
        }
        catch (Exception e){
            log.severe(e.getMessage());
            throw new InternalServerErrorException(e.getMessage(), e.getCause());
        }

        if(isNull(token))
            throw new NotFoundException("Token não encontrado!");
        return token;
    }

    @Override
    public String saveNewClientId(TokenRequest tokenRequest) {
        if(!isNull(tokenDBGateway.getTokenByClientId(tokenRequest.getClientId())))
            throw new ClientAlreadyExistsException("ClientId " + tokenRequest.getClientId() + " já cadastrado");
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
