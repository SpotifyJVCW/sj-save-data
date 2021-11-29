package br.com.spotifyjvcw.usecase;

import br.com.spotifyjvcw.domain.Token;
import br.com.spotifyjvcw.exception.especific.InternalServerErrorException;
import br.com.spotifyjvcw.exception.especific.NotFoundException;
import br.com.spotifyjvcw.gateway.TokenDBGateway;
import br.com.spotifyjvcw.host.data.request.TokenRequest;
import br.com.spotifyjvcw.usecase.converter.TokenRequestToTokenDomainConverter;
import br.com.spotifyjvcw.usecase.impl.TokenInteractionsWithDBImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TokenInteractionsWithDBTest {

    TokenInteractionsWithDB usecase;

    @Mock
    TokenDBGateway tokenDBGateway;

    @Mock
    TokenRequestToTokenDomainConverter tokenRequestToTokenDomainConverter;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        usecase = new TokenInteractionsWithDBImpl(tokenDBGateway, tokenRequestToTokenDomainConverter);
    }


    @DisplayName("quando chamado metodo getAllTokens " +
                 "então deve ser retornado uma Lista de Tokens")
    @Test
    void test1(){
        when(tokenDBGateway.getAll()).thenReturn(new ArrayList<>());

        List<Token> tokens = usecase.getAllTokens();

        verify(tokenDBGateway, times(1)).getAll();
    }

    @DisplayName("dado um clientId " +
                 "quando chamado metodo getTokenByClientId " +
                 "então deve ser retornado um Token")
    @Test
    void test2(){
        when(tokenDBGateway.getTokenByClientId(Mockito.anyString())).thenReturn(new Token());

        Token token = usecase.getTokenByClientId("teste");

        assertNotNull(token);
        verify(tokenDBGateway, times(1)).getTokenByClientId("teste");
    }

    @DisplayName("dado um clientId " +
            "quando chamado metodo getTokenByClientId e for lancado uma exception" +
            "então deve ser lançado um InternalServerErrorException")
    @Test
    void test3(){
        doThrow(IllegalArgumentException.class).when(tokenDBGateway).getTokenByClientId(Mockito.anyString());

        assertThrows(InternalServerErrorException.class , ()
                -> usecase.getTokenByClientId("teste"));

        verify(tokenDBGateway, times(1)).getTokenByClientId("teste");
    }

    @DisplayName("dado um clientId " +
                 "quando chamado metodo getTokenByClientId e não for encontrado nenhum token" +
                 "então deve ser lançado um NotFoundException")
    @Test
    void test4(){
        when(tokenDBGateway.getTokenByClientId(Mockito.anyString())).thenReturn(null);

        assertThrows(NotFoundException.class , ()
                -> usecase.getTokenByClientId("teste"));

        verify(tokenDBGateway, times(1)).getTokenByClientId("teste");
    }

    @DisplayName("dado um TokenRequest " +
                 "quando chamado metodo saveNewClientId " +
                 "então deve ser retornado uma String clienId")
    @Test
    void test5(){
        Token token = Token.builder()
                        .accessToken("access")
                        .clientId("client")
                        .refreshToken("refresh").build();

        when(tokenDBGateway.saveClientId(Mockito.any(Token.class))).thenReturn(token);
        when(tokenRequestToTokenDomainConverter.exeute(Mockito.any(TokenRequest.class))).thenReturn(new Token());

        String clientId = usecase.saveNewClientId(new TokenRequest());

        assertEquals("client", clientId);
        verify(tokenDBGateway, times(1)).saveClientId(Mockito.any(Token.class));
    }

}