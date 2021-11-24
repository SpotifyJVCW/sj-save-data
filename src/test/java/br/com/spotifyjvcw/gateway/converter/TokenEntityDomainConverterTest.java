package br.com.spotifyjvcw.gateway.converter;

import br.com.spotifyjvcw.domain.Token;
import br.com.spotifyjvcw.gateway.converter.impl.TokenEntityDomainConverterImpl;
import br.com.spotifyjvcw.gateway.entity.TokenEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TokenEntityDomainConverterTest {

    TokenEntityDomainConverter converter;

    @BeforeEach
    void setUp(){
        converter = new TokenEntityDomainConverterImpl();
    }

    @DisplayName("dado um TokenEntity " +
                 "quando chamado metodo entityToDomain " +
                 "então deve ser retornado um Token")
    @Test
    void test1(){

        TokenEntity tokenEntity = TokenEntity.builder()
                .accessToken("access")
                .clientId("client")
                .refreshToken("refresh").build();

        Token token = converter.entityToDomain(tokenEntity);

        assertNotNull(token);
        assertEquals("access", token.getAccessToken());
        assertEquals("client", token.getClientId());
        assertEquals("refresh", token.getRefreshToken());
    }

    @DisplayName("dado um Token " +
                 "quando chamado metodo domainToEntity " +
                 "então deve ser retornado um TokenEntity")
    @Test
    void test2(){

        Token token = Token.builder()
                .accessToken("access")
                .clientId("client")
                .refreshToken("refresh").build();

        TokenEntity tokenEntity = converter.domainToEntity(token);

        assertNotNull(tokenEntity);
        assertEquals("access", tokenEntity.getAccessToken());
        assertEquals("client", tokenEntity.getClientId());
        assertEquals("refresh", tokenEntity.getRefreshToken());
    }

    @DisplayName("dado um Token nulo" +
                 "quando chamado metodo domainToEntity " +
                 "então deve ser retornado nulo")
    @Test
    void test3(){

        TokenEntity tokenEntity = converter.domainToEntity(null);

        assertNull(tokenEntity);
    }

    @DisplayName("dado um TokenEntity nulo" +
                 "quando chamado metodo entityToDomain " +
                 "então deve ser retornado nulo")
    @Test
    void test4(){

        Token token = converter.entityToDomain(null);

        assertNull(token);
    }
}