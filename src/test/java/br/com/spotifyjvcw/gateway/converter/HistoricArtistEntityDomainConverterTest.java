package br.com.spotifyjvcw.gateway.converter;

import br.com.spotifyjvcw.domain.HistoricArtist;
import br.com.spotifyjvcw.domain.Token;
import br.com.spotifyjvcw.gateway.converter.impl.ArtistEntityDomainConverterImpl;
import br.com.spotifyjvcw.gateway.converter.impl.TokenEntityDomainConverterImpl;
import br.com.spotifyjvcw.gateway.entity.ArtistEntity;
import br.com.spotifyjvcw.gateway.entity.TokenEntity;
import br.com.spotifyjvcw.gateway.entity.ids.ArtistId;
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

class HistoricArtistEntityDomainConverterTest {

    ArtistEntityDomainConverter converter;

    @Mock
    TokenEntityDomainConverterImpl tokenEntityDomainConverter;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.initMocks(this);
        converter = new ArtistEntityDomainConverterImpl(tokenEntityDomainConverter);
    }

    @DisplayName("dado uma lista de ArtistEntity " +
                 "quando chamado metodo entityToDomain " +
                 "então deve ser retornado uma lista de Artist")
    @Test
    void test1(){
        when(tokenEntityDomainConverter.entityToDomain(Mockito.any(TokenEntity.class))).thenCallRealMethod();

        TokenEntity tokenEntity = new TokenEntity();

        List<ArtistEntity> artistEntities = new ArrayList<>();
        artistEntities.add(ArtistEntity.builder()
                .artistsLong("testeLong1;testeLong2")
                .artistsMedium("testeMedium1;testeMedium2")
                .artistsShort("testeShort1;testeShort2")
                        .artistId(ArtistId.builder()
                                .date(LocalDate.now())
                                .tokenEntity(tokenEntity).build()).build());

        List<HistoricArtist> historicArtists = converter.entityToDomain(artistEntities);

        verify(tokenEntityDomainConverter, times(1)).entityToDomain(tokenEntity);
        assertNotNull(historicArtists);

        assertEquals("testeLong1", historicArtists.get(0).getArtistsLong()[0]);
        assertEquals("testeLong2", historicArtists.get(0).getArtistsLong()[1]);

        assertEquals("testeMedium1", historicArtists.get(0).getArtistsMedium()[0]);
        assertEquals("testeMedium2", historicArtists.get(0).getArtistsMedium()[1]);

        assertEquals("testeShort1", historicArtists.get(0).getArtistsShort()[0]);
        assertEquals("testeShort2", historicArtists.get(0).getArtistsShort()[1]);
    }

    @DisplayName("dado uma lista de Artist " +
                 "quando chamado metodo domainToEntity " +
                 "então deve ser retornado uma lista de ArtistEntity")
    @Test
    void test2(){
        when(tokenEntityDomainConverter.domainToEntity(Mockito.any(Token.class))).thenCallRealMethod();

        Token token = new Token();

        List<HistoricArtist> historicArtists = new ArrayList<>();
        historicArtists.add(HistoricArtist.builder()
                .artistsLong(new String[]{ "testeLong1","testeLong2"})
                .artistsMedium(new String[]{ "testeMedium1","testeMedium2"})
                .artistsShort(new String[]{ "testeShort1","testeShort2"})
                .date(LocalDate.now())
                .token(token).build());

        List<ArtistEntity> artistsEntity = converter.domainToEntity(historicArtists);

        verify(tokenEntityDomainConverter, times(1)).domainToEntity(token);

        assertNotNull(artistsEntity);

        assertEquals("testeLong1;testeLong2;", artistsEntity.get(0).getArtistsLong());
        assertEquals("testeMedium1;testeMedium2;", artistsEntity.get(0).getArtistsMedium());
        assertEquals("testeShort1;testeShort2;", artistsEntity.get(0).getArtistsShort());
    }

    @DisplayName("dado uma Artist nulo " +
                 "quando chamado metodo domainToEntity " +
                 "então deve ser retornado nulo")
    @Test
    void test3(){
        List<ArtistEntity> artistEntities = converter.domainToEntity((List<HistoricArtist>) null);
        ArtistEntity artistEntity = converter.domainToEntity((HistoricArtist) null);

        assertTrue(artistEntities.isEmpty());
        assertNull(artistEntity);
    }

    @DisplayName("dado uma ArtistEntity nulo " +
                 "quando chamado metodo entityToDomain " +
                 "então deve ser retornado nulo")
    @Test
    void test4(){
        List<HistoricArtist> historicArtists = converter.entityToDomain((List<ArtistEntity>) null);
        HistoricArtist historicArtist = converter.entityToDomain((ArtistEntity) null);

        assertTrue(historicArtists.isEmpty());
        assertNull(historicArtist);
    }

}