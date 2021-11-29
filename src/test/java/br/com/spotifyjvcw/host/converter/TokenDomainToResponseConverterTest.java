package br.com.spotifyjvcw.host.converter;

import br.com.spotifyjvcw.domain.Artist;
import br.com.spotifyjvcw.domain.Token;
import br.com.spotifyjvcw.host.converter.impl.ArtistDomainToArtistResponseConverterImpl;
import br.com.spotifyjvcw.host.converter.impl.TokenDomainToResponseConverterImpl;
import br.com.spotifyjvcw.host.data.response.ArtistResponse;
import br.com.spotifyjvcw.host.data.response.TokenResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TokenDomainToResponseConverterTest {

    TokenDomainToResponseConverter converter;

    @BeforeEach
    public void setUp(){
        converter = new TokenDomainToResponseConverterImpl();
    }

    @DisplayName("dado uma lista de Tokens " +
                 "quando chamado metodo execute " +
                 "então deve ser retornado uma Lista de TokenResponse")
    @Test
    void test1(){
        List<Token> tokens = new ArrayList<>();
        tokens.add(Token.builder()
                .refreshToken("refresh")
                .clientId("client")
                .accessToken("access")
                .build());

        List<TokenResponse> tokenResponses = converter.execute(tokens);

        assertNotNull(tokenResponses);
        assertEquals("refresh", tokenResponses.get(0).getRefreshToken());
        assertEquals("client", tokenResponses.get(0).getClientId());
        assertEquals("access", tokenResponses.get(0).getAccessToken());
    }
}