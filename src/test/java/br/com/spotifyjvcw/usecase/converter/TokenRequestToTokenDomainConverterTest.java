package br.com.spotifyjvcw.usecase.converter;

import br.com.spotifyjvcw.domain.Token;
import br.com.spotifyjvcw.host.data.request.TokenRequest;
import br.com.spotifyjvcw.usecase.converter.impl.TokenRequestToTokenDomainConverterImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TokenRequestToTokenDomainConverterTest {

    TokenRequestToTokenDomainConverter converter;

    @BeforeEach
    public void setUp(){
        converter = new TokenRequestToTokenDomainConverterImpl();
    }

    @DisplayName("dado um TokenRequest  " +
                 "quando chamado metodo execute " +
                 "então deve ser retornado um Token")
    @Test
    void test1(){

        TokenRequest tokenRequest = TokenRequest.builder()
                .clientId("client")
                .refreshToken("refresh")
                .accessToken("access")
                .build();

        Token token = converter.exeute(tokenRequest);

        assertNotNull(token);
        assertEquals("refresh", token.getRefreshToken());
        assertEquals("access", token.getAccessToken());
        assertEquals("client", token.getClientId());
    }
}