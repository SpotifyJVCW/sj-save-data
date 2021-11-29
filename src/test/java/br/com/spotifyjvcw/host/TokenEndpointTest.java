package br.com.spotifyjvcw.host;

import br.com.spotifyjvcw.ApplicationTests;
import br.com.spotifyjvcw.domain.Token;
import br.com.spotifyjvcw.host.data.request.RefreshTokenRequest;
import br.com.spotifyjvcw.host.data.request.TokenRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class TokenEndpointTest extends ApplicationTests {

    TokenEndpoint endpoint;

    @Autowired
    MockMvc mockMvc;

    @BeforeEach
    public void seUp(){
        MockitoAnnotations.initMocks(this);
        endpoint = new TokenEndpoint(tokenInteractionsWithDB, tokenDomainToResponseConverter);
    }

    @DisplayName("quando chamado o endpoint de getAllTokens, " +
                 "então é retornado status 200")
    @Test
    void test1(){
        when(tokenInteractionsWithDB.getAllTokens()).thenReturn(new ArrayList<>());
        when(tokenDomainToResponseConverter.execute(Mockito.anyList())).thenReturn(new ArrayList<>());

        try {
            mockMvc.perform(get("/tokens")
                            .contentType(MediaType.APPLICATION_JSON)
                            .accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk());
        }
        catch (Exception e){
            fail();
        }

        verify(tokenInteractionsWithDB, times(1)).getAllTokens();
        verify(tokenDomainToResponseConverter, times(1)).execute(Mockito.anyList());
    }

    @DisplayName("dado um clientId válido, " +
                 "quando chamado o endpoint de getTokenByClientId, " +
                 "então é retornado status 200")
    @Test
    void test2(){
        when(tokenInteractionsWithDB.getTokenByClientId(Mockito.anyString())).thenReturn(new Token());
        when(tokenDomainToResponseConverter.execute(Mockito.anyList())).thenReturn(new ArrayList<>());

        try {
            mockMvc.perform(get("/tokens/teste")
                            .contentType(MediaType.APPLICATION_JSON)
                            .accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk());
        }
        catch (Exception e){
            fail();
        }
        verify(tokenInteractionsWithDB, times(1)).getTokenByClientId("teste");
        verify(tokenDomainToResponseConverter, times(1)).execute(Mockito.any(Token.class));
    }

    @DisplayName("dado um clientId válido, " +
                 "quando chamado o endpoint de getTokenByClientId, " +
                 "então é retornado status 400")
    @Test
    void test3(){
        try {
            mockMvc.perform(get("/tokens/ ")
                            .contentType(MediaType.APPLICATION_JSON)
                            .accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isBadRequest());
        }
        catch (Exception e){
            fail();
        }
    }

    @DisplayName("dado um TokenRequest válido, " +
                 "quando chamado o endpoint de insertToken, " +
                 "então é retornado status 201")
    @Test
    void test4(){
        when(tokenInteractionsWithDB.saveNewClientId(Mockito.any(TokenRequest.class))).thenReturn("teste");

        try {
            mockMvc.perform(post("/tokens")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content("\n" +
                                    "  {\n" +
                                    "    \"clientId\": \"client\",\n" +
                                    "    \"accessToken\": \"access\",\n" +
                                    "    \"refreshToken\": \"refresh\"\n" +
                                    "  }\n")
                            .accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isCreated());
        }
        catch (Exception e){
            fail();
        }
        verify(tokenInteractionsWithDB, times(1)).saveNewClientId(Mockito.any(TokenRequest.class));
    }

    @DisplayName("dado um TokenRequest inválido, " +
                 "quando chamado o endpoint de insertToken, " +
                 "então é retornado status 400")
    @ParameterizedTest
    @CsvSource({
            "clientId inválido,            '', access, refresh",
            "access inválida,        client,       '', refresh",
            "refresh inválida,       client, access,''        "
    })
    void test5(String testCase, String clientId, String access, String refresh){

        try {
            mockMvc.perform(post("/tokens")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content("\n" +
                                    "  {\n" +
                                    "    \"clientId\": \"" + clientId + "\",\n" +
                                    "    \"accessToken\": \"" + access + "\",\n" +
                                    "    \"refreshToken\": \"" + refresh + "\"\n" +
                                    "  }\n")
                            .accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isBadRequest());
        }
        catch (Exception e){
            fail();
        }
    }

    @DisplayName("dado um clientId e um refresTokenRequest válido, " +
                 "quando chamado o endpoint de refreshToken, " +
                 "então é retornado status 200")
    @Test
    void test6(){
        doNothing().when(tokenInteractionsWithDB).refreshToken(Mockito.anyString(), Mockito.any(RefreshTokenRequest.class));

        try {
            mockMvc.perform(post("/tokens/refresh/teste")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content("\n" +
                                    "  {\n" +
                                    "    \"refreshToken\": \"refresh\"\n" +
                                    "  }\n")
                            .accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk());
        }
        catch (Exception e){
            fail();
        }
        verify(tokenInteractionsWithDB, times(1))
                .refreshToken(Mockito.anyString(), Mockito.any(RefreshTokenRequest.class));
    }
}