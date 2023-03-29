package br.com.spotifyjvcw.host;

import br.com.spotifyjvcw.ApplicationTests;
import br.com.spotifyjvcw.domain.HistoricTrack;
import br.com.spotifyjvcw.host.data.response.TrackResponse;
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

import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class HistoricTrackEndpointTest extends ApplicationTests {
    TrackEndpoint endpoint;

    @Autowired
    MockMvc mockMvc;

    @BeforeEach
    public void seUp(){
        MockitoAnnotations.initMocks(this);
        endpoint = new TrackEndpoint(trackInteractionsWithDB, trackDomainToTrackResponseConverter);
    }

    @DisplayName("quando chamado o endpoint de getAllTracks, " +
            "então é retornado status 200")
    @Test
    void test1(){
        when(trackInteractionsWithDB.getAll()).thenReturn(new ArrayList<>());
        when(trackDomainToTrackResponseConverter.execute(Mockito.anyList())).thenReturn(new ArrayList<>());

        try {
            mockMvc.perform(get("/tracks")
                            .contentType(MediaType.APPLICATION_JSON)
                            .accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk());
        }
        catch (Exception e){
            fail();
        }

        verify(trackInteractionsWithDB, times(1)).getAll();
        verify(trackDomainToTrackResponseConverter, times(1)).execute(Mockito.anyList());
    }

    @DisplayName("dado um clientId e uma data válida, " +
            "quando chamado o endpoint de getTrackByDate, " +
            "então é retornado status 200")
    @Test
    void test2(){
        when(trackInteractionsWithDB.getByDate(Mockito.any(LocalDate.class), Mockito.anyString())).thenReturn(new HistoricTrack());
        when(trackDomainToTrackResponseConverter.execute(Mockito.any(HistoricTrack.class))).thenReturn(new TrackResponse());

        try {
            mockMvc.perform(get("/tracks/teste/" + LocalDate.now())
                            .contentType(MediaType.APPLICATION_JSON)
                            .accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk());
        }
        catch (Exception e){
            fail();
        }
        verify(trackInteractionsWithDB, times(1)).getByDate(LocalDate.now(), "teste");
        verify(trackDomainToTrackResponseConverter, times(1)).execute(Mockito.any(HistoricTrack.class));
    }

    @DisplayName("dado um clientId e uma data válida, " +
            "quando chamado o endpoint de getTrackByDate, " +
            "então é retornado status 200")
    @ParameterizedTest
    @CsvSource({
            "clientId inválido,     /tracks/ /2021-11-24",
            "Data inválida,         /tracks/teste/2021-11d-24"
    })
    void test3(String testCase, String url){
        try {
            mockMvc.perform(get(url)
                            .contentType(MediaType.APPLICATION_JSON)
                            .accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isBadRequest());
        }
        catch (Exception e){
            fail();
        }
    }

    @DisplayName("dado um clientId válido, " +
            "quando chamado o endpoint de saveAll, " +
            "então é retornado status 200")
    @Test
    void test4(){
        doNothing().when(trackInteractionsWithDB).saveAll(Mockito.anyList(), Mockito.anyString());

        try {
            mockMvc.perform(post("/tracks/teste")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content("[\n" +
                                    "  {\n" +
                                    "    \"date\": \"2021-11-24\",\n" +
                                    "    \"tracksLong\": [\n" +
                                    "      \"string\"\n" +
                                    "    ],\n" +
                                    "    \"tracksMedium\": [\n" +
                                    "      \"string\"\n" +
                                    "    ],\n" +
                                    "    \"tracksShort\": [\n" +
                                    "      \"string\"\n" +
                                    "    ]\n" +
                                    "  }\n" +
                                    "]")
                            .accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk());
        }
        catch (Exception e){
            fail();
        }
        verify(trackInteractionsWithDB, times(1)).saveAll(Mockito.anyList(), Mockito.anyString());
    }
}