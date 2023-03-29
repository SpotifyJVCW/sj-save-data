package br.com.spotifyjvcw.gateway;

import br.com.spotifyjvcw.domain.HistoricTrack;
import br.com.spotifyjvcw.gateway.converter.impl.TrackEntityDomainConverterImpl;
import br.com.spotifyjvcw.gateway.entity.TrackEntity;
import br.com.spotifyjvcw.gateway.impl.TrackDBGatewayImpl;
import br.com.spotifyjvcw.gateway.repository.TrackRepository;
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

class HistoricTrackDBGatewayTest {
    
    TrackDBGateway gateway;

    @Mock
    TrackRepository trackRepository;

    @Mock
    TrackEntityDomainConverterImpl trackEntityDomainConverter;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.initMocks(this);
        gateway = new TrackDBGatewayImpl(trackRepository, trackEntityDomainConverter);
    }

    @DisplayName("quando chamado metodo getAllTracks " +
            "então deve ser retornado uma Lista de Tracks")
    @Test
    void test1(){
        when(trackRepository.findAll()).thenReturn(new ArrayList<>());
        when(trackEntityDomainConverter.entityToDomain(Mockito.anyList())).thenCallRealMethod();

        List<HistoricTrack> historicTracks = gateway.getAllTracks();

        verify(trackRepository, times(1)).findAll();
        verify(trackEntityDomainConverter, times(1)).entityToDomain(Mockito.anyList());
        assertNotNull(historicTracks);
    }

    @DisplayName("dado uma data e um clientId " +
            "quando chamado metodo getTrackByDateAndClientId " +
            "então deve ser retornado um Track")
    @Test
    void test2(){
        when(trackRepository.findTrackByClientIdAndDate(Mockito.anyString(),
                Mockito.any(LocalDate.class))).thenReturn(new TrackEntity());
        when(trackEntityDomainConverter.entityToDomain(Mockito.any(TrackEntity.class))).thenReturn(new HistoricTrack());

        HistoricTrack historicTrack = gateway.getTrackByDateAndClientId(LocalDate.now(), "teste");

        verify(trackRepository, times(1))
                .findTrackByClientIdAndDate("teste", LocalDate.now());

        verify(trackEntityDomainConverter, times(1))
                .entityToDomain(Mockito.any(TrackEntity.class));

        assertNotNull(historicTrack);
    }


    @DisplayName("dado uma lista de Tracks " +
            "quando chamado metodo saveAllTracks " +
            "então deve ser chamado saveAll")
    @Test
    void test3(){
        when(trackRepository.saveAll(Mockito.anyList())).thenReturn(null);
        when(trackEntityDomainConverter.domainToEntity(Mockito.any(HistoricTrack.class))).thenReturn(new TrackEntity());

        gateway.saveAllTracks(new ArrayList<>());

        verify(trackRepository, times(1)).saveAll(Mockito.anyList());
        verify(trackEntityDomainConverter, times(1)).domainToEntity(Mockito.anyList());
    }
}