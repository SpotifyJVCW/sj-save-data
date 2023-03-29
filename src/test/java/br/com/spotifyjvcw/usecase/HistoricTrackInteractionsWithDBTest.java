package br.com.spotifyjvcw.usecase;

import br.com.spotifyjvcw.domain.HistoricTrack;
import br.com.spotifyjvcw.exception.especific.InternalServerErrorException;
import br.com.spotifyjvcw.exception.especific.NotFoundException;
import br.com.spotifyjvcw.gateway.TrackDBGateway;
import br.com.spotifyjvcw.host.data.request.TrackRequest;
import br.com.spotifyjvcw.usecase.converter.impl.TrackRequestToTrackDomainConverterImpl;
import br.com.spotifyjvcw.usecase.impl.TrackInteractionsWithDBImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

class HistoricTrackInteractionsWithDBTest {

    TrackInteractionsWithDB usecase;

    @Mock
    TrackDBGateway trackDBGateway;

    @Mock
    TrackRequestToTrackDomainConverterImpl trackDomainConverter;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        usecase = new TrackInteractionsWithDBImpl(trackDBGateway, trackDomainConverter);
    }

    @DisplayName("dado uma data e um clientId " +
            "quando chamado metodo getByDate " +
            "então deve ser retornado um Track")
    @Test
    void test1(){
        when(trackDBGateway.getTrackByDateAndClientId(Mockito.any(LocalDate.class), Mockito.anyString()))
                .thenReturn(new HistoricTrack());

        HistoricTrack historicTrack = usecase.getByDate(LocalDate.now(), "teste");

        verify(trackDBGateway, times(1)).getTrackByDateAndClientId(LocalDate.now(), "teste");
        assertNotNull(historicTrack);
    }

    @DisplayName("quando chamado metodo getAll " +
            "então deve ser retornado lisa de Track")
    @Test
    void test2(){
        when(trackDBGateway.getAllTracks()).thenReturn(new ArrayList<>());

        List<HistoricTrack> historicTracks = usecase.getAll();

        verify(trackDBGateway, times(1)).getAllTracks();
        assertNotNull(historicTracks);
    }

    @DisplayName("dado uma data e um clientId " +
            "quando chamado metodo getByDate e não for encontrado um Tracka " +
            "então deve ser lançado um NotFoundException")
    @Test
    void test3(){
        when(trackDBGateway.getTrackByDateAndClientId(Mockito.any(LocalDate.class), Mockito.anyString()))
                .thenReturn(null);

        LocalDate data = LocalDate.now();

        assertThrows(NotFoundException.class , ()
                -> usecase.getByDate(data, "teste"));

        verify(trackDBGateway, times(1))
                .getTrackByDateAndClientId(LocalDate.now(), "teste");
    }

    @DisplayName("dado uma data e um clientId " +
            "quando chamado metodo getByDate e for lançado uma exception " +
            "então deve ser lançado um InternalServerErrorException")
    @Test
    void test4(){
        when(trackDBGateway.getTrackByDateAndClientId(Mockito.any(LocalDate.class), Mockito.anyString()))
                .thenThrow(IllegalArgumentException.class);

        LocalDate data = LocalDate.now();

        assertThrows(InternalServerErrorException.class , ()
                -> usecase.getByDate(data, "teste"));

        verify(trackDBGateway, times(1))
                .getTrackByDateAndClientId(LocalDate.now(), "teste");
    }

    @DisplayName("dado uma lista de trackas e um clientId " +
            "quando chamado metodo saveAll " +
            "então deve ser salvo os dados no bd")
    @Test
    void test5(){
        doNothing().when(trackDBGateway).saveAllTracks(Mockito.anyList());
        when(trackDomainConverter.execute(Mockito.anyList(), Mockito.anyString())).thenCallRealMethod();

        usecase.saveAll(new ArrayList<>(), "teste");

        verify(trackDBGateway, times(1)).saveAllTracks(new ArrayList<>());
        verify(trackDomainConverter, times(1)).execute(new ArrayList<>(), "teste");
    }

    @DisplayName("dado uma lista de trackas e um clientId " +
            "quando chamado metodo saveAll " +
            "então deve ser salvo os dados no bd")
    @Test
    void test6(){
        doThrow(IllegalArgumentException.class).when(trackDBGateway).saveAllTracks(Mockito.anyList());
        when(trackDomainConverter.execute(Mockito.anyList(), Mockito.anyString())).thenCallRealMethod();

        List<TrackRequest> lista = new ArrayList<>();

        assertThrows(InternalServerErrorException.class , ()
                -> usecase.saveAll(lista, "teste"));

        verify(trackDBGateway, times(1))
                .saveAllTracks(new ArrayList<>());
    }
}