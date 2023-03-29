package br.com.spotifyjvcw.gateway;

import br.com.spotifyjvcw.domain.HistoricArtist;
import br.com.spotifyjvcw.gateway.converter.impl.ArtistEntityDomainConverterImpl;
import br.com.spotifyjvcw.gateway.entity.ArtistEntity;
import br.com.spotifyjvcw.gateway.impl.ArtistDBGatewayImpl;
import br.com.spotifyjvcw.gateway.repository.ArtistRepository;
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

class HistoricArtistDBGatewayTest {

    ArtistDBGateway gateway;

    @Mock
    ArtistRepository artistRepository;

    @Mock
    ArtistEntityDomainConverterImpl artistEntityDomainConverter;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.initMocks(this);
        gateway = new ArtistDBGatewayImpl(artistRepository, artistEntityDomainConverter);
    }

    @DisplayName("quando chamado metodo getAllArtists " +
                 "então deve ser retornado uma Lista de Artists")
    @Test
    void test1(){
        when(artistRepository.findAll()).thenReturn(new ArrayList<>());
        when(artistEntityDomainConverter.entityToDomain(Mockito.anyList())).thenCallRealMethod();

        List<HistoricArtist> historicArtists = gateway.getAllArtists();

        verify(artistRepository, times(1)).findAll();
        verify(artistEntityDomainConverter, times(1)).entityToDomain(Mockito.anyList());
        assertNotNull(historicArtists);
    }

    @DisplayName("dado uma data e um clientId " +
                 "quando chamado metodo getArtistByDateAndClientId " +
                 "então deve ser retornado um Artist")
    @Test
    void test2(){
        when(artistRepository.findArtistByClientIdAndDate(Mockito.anyString(),
                Mockito.any(LocalDate.class))).thenReturn(new ArtistEntity());
        when(artistEntityDomainConverter.entityToDomain(Mockito.any(ArtistEntity.class))).thenReturn(new HistoricArtist());

        HistoricArtist historicArtist = gateway.getArtistByDateAndClientId(LocalDate.now(), "teste");

        verify(artistRepository, times(1))
                .findArtistByClientIdAndDate("teste", LocalDate.now());

        verify(artistEntityDomainConverter, times(1))
                .entityToDomain(Mockito.any(ArtistEntity.class));

        assertNotNull(historicArtist);
    }


    @DisplayName("dado uma lista de Artists " +
                 "quando chamado metodo saveAllArtists " +
                 "então deve ser chamado saveAll")
    @Test
    void test3(){
        when(artistRepository.saveAll(Mockito.anyList())).thenReturn(null);
        when(artistEntityDomainConverter.domainToEntity(Mockito.any(HistoricArtist.class))).thenReturn(new ArtistEntity());

        gateway.saveAllArtists(new ArrayList<>());

        verify(artistRepository, times(1)).saveAll(Mockito.anyList());
        verify(artistEntityDomainConverter, times(1)).domainToEntity(Mockito.anyList());
    }
}