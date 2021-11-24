package br.com.spotifyjvcw.usecase;

import br.com.spotifyjvcw.domain.Artist;
import br.com.spotifyjvcw.exception.especific.InternalServerErrorException;
import br.com.spotifyjvcw.exception.especific.NotFoundException;
import br.com.spotifyjvcw.gateway.ArtistDBGateway;
import br.com.spotifyjvcw.host.data.request.ArtistRequest;
import br.com.spotifyjvcw.usecase.converter.impl.ArtistRequestToArtistDomainConverterImpl;
import br.com.spotifyjvcw.usecase.impl.ArtistInteractionsWithDBImpl;
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

class ArtistInteractionsWithDBTest {

    ArtistInteractionsWithDB usecase;

    @Mock
    ArtistDBGateway artistDBGateway;

    @Mock
    ArtistRequestToArtistDomainConverterImpl artistDomainConverter;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        usecase = new ArtistInteractionsWithDBImpl(artistDBGateway, artistDomainConverter);
    }

    @DisplayName("dado uma data e um clientId " +
                 "quando chamado metodo getByDate " +
                 "então deve ser retornado um Artist")
    @Test
    void test1(){
        when(artistDBGateway.getArtistByDateAndClientId(Mockito.any(LocalDate.class), Mockito.anyString()))
                .thenReturn(new Artist());

        Artist artist = usecase.getByDate(LocalDate.now(), "teste");

        verify(artistDBGateway, times(1)).getArtistByDateAndClientId(LocalDate.now(), "teste");
        assertNotNull(artist);
    }

    @DisplayName("quando chamado metodo getAll " +
            "então deve ser retornado lisa de Artist")
    @Test
    void test2(){
        when(artistDBGateway.getAllArtists()).thenReturn(new ArrayList<>());

        List<Artist> artists = usecase.getAll();

        verify(artistDBGateway, times(1)).getAllArtists();
        assertNotNull(artists);
    }

    @DisplayName("dado uma data e um clientId " +
                 "quando chamado metodo getByDate e não for encontrado um Artista " +
                 "então deve ser lançado um NotFoundException")
    @Test
    void test3(){
        when(artistDBGateway.getArtistByDateAndClientId(Mockito.any(LocalDate.class), Mockito.anyString()))
                .thenReturn(null);

        LocalDate data = LocalDate.now();

        assertThrows(NotFoundException.class , ()
                -> usecase.getByDate(data, "teste"));

        verify(artistDBGateway, times(1))
                .getArtistByDateAndClientId(LocalDate.now(), "teste");
    }

    @DisplayName("dado uma data e um clientId " +
                 "quando chamado metodo getByDate e for lançado uma exception " +
                 "então deve ser lançado um InternalServerErrorException")
    @Test
    void test4(){
        when(artistDBGateway.getArtistByDateAndClientId(Mockito.any(LocalDate.class), Mockito.anyString()))
                .thenThrow(IllegalArgumentException.class);

        LocalDate data = LocalDate.now();

        assertThrows(InternalServerErrorException.class , ()
                -> usecase.getByDate(data, "teste"));

        verify(artistDBGateway, times(1))
                .getArtistByDateAndClientId(LocalDate.now(), "teste");
    }

    @DisplayName("dado uma lista de artistas e um clientId " +
                 "quando chamado metodo saveAll " +
                 "então deve ser salvo os dados no bd")
    @Test
    void test5(){
        doNothing().when(artistDBGateway).saveAllArtists(Mockito.anyList());
        when(artistDomainConverter.execute(Mockito.anyList(), Mockito.anyString())).thenCallRealMethod();

        usecase.saveAll(new ArrayList<>(), "teste");

        verify(artistDBGateway, times(1)).saveAllArtists(new ArrayList<>());
        verify(artistDomainConverter, times(1)).execute(new ArrayList<>(), "teste");
    }

    @DisplayName("dado uma lista de artistas e um clientId " +
                 "quando chamado metodo saveAll " +
                 "então deve ser salvo os dados no bd")
    @Test
    void test6(){
        doThrow(IllegalArgumentException.class).when(artistDBGateway).saveAllArtists(Mockito.anyList());
        when(artistDomainConverter.execute(Mockito.anyList(), Mockito.anyString())).thenCallRealMethod();

        List<ArtistRequest> lista = new ArrayList<>();

        assertThrows(InternalServerErrorException.class , ()
                -> usecase.saveAll(lista, "teste"));

        verify(artistDBGateway, times(1))
                .saveAllArtists(new ArrayList<>());
    }
}