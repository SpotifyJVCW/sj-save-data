package br.com.spotifyjvcw.gateway.converter;

import br.com.spotifyjvcw.domain.Track;
import br.com.spotifyjvcw.domain.Token;
import br.com.spotifyjvcw.gateway.converter.impl.TrackEntityDomainConverterImpl;
import br.com.spotifyjvcw.gateway.converter.impl.TokenEntityDomainConverterImpl;
import br.com.spotifyjvcw.gateway.entity.TrackEntity;
import br.com.spotifyjvcw.gateway.entity.TokenEntity;
import br.com.spotifyjvcw.gateway.entity.ids.TrackId;
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

class TrackEntityDomainConverterTest {

    TrackEntityDomainConverter converter;

    @Mock
    TokenEntityDomainConverterImpl tokenEntityDomainConverter;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.initMocks(this);
        converter = new TrackEntityDomainConverterImpl(tokenEntityDomainConverter);
    }

    @DisplayName("dado uma lista de TrackEntity " +
            "quando chamado metodo entityToDomain " +
            "então deve ser retornado uma lista de Track")
    @Test
    void test1(){
        when(tokenEntityDomainConverter.entityToDomain(Mockito.any(TokenEntity.class))).thenCallRealMethod();

        TokenEntity tokenEntity = new TokenEntity();

        List<TrackEntity> trackEntities = new ArrayList<>();
        trackEntities.add(TrackEntity.builder()
                .tracksLong("testeLong1;testeLong2")
                .tracksMedium("testeMedium1;testeMedium2")
                .tracksShort("testeShort1;testeShort2")
                .trackId(TrackId.builder()
                        .date(LocalDate.now())
                        .tokenEntity(tokenEntity).build()).build());

        List<Track> tracks = converter.entityToDomain(trackEntities);

        verify(tokenEntityDomainConverter, times(1)).entityToDomain(tokenEntity);
        assertNotNull(tracks);

        assertEquals("testeLong1", tracks.get(0).getTracksLong()[0]);
        assertEquals("testeLong2", tracks.get(0).getTracksLong()[1]);

        assertEquals("testeMedium1", tracks.get(0).getTracksMedium()[0]);
        assertEquals("testeMedium2", tracks.get(0).getTracksMedium()[1]);

        assertEquals("testeShort1", tracks.get(0).getTracksShort()[0]);
        assertEquals("testeShort2", tracks.get(0).getTracksShort()[1]);
    }

    @DisplayName("dado uma lista de Track " +
            "quando chamado metodo domainToEntity " +
            "então deve ser retornado uma lista de TrackEntity")
    @Test
    void test2(){
        when(tokenEntityDomainConverter.domainToEntity(Mockito.any(Token.class))).thenCallRealMethod();

        Token token = new Token();

        List<Track> tracks = new ArrayList<>();
        tracks.add(Track.builder()
                .tracksLong(new String[]{ "testeLong1","testeLong2"})
                .tracksMedium(new String[]{ "testeMedium1","testeMedium2"})
                .tracksShort(new String[]{ "testeShort1","testeShort2"})
                .date(LocalDate.now())
                .token(token).build());

        List<TrackEntity> tracksEntity = converter.domainToEntity(tracks);

        verify(tokenEntityDomainConverter, times(1)).domainToEntity(token);

        assertNotNull(tracksEntity);

        assertEquals("testeLong1;testeLong2;", tracksEntity.get(0).getTracksLong());
        assertEquals("testeMedium1;testeMedium2;", tracksEntity.get(0).getTracksMedium());
        assertEquals("testeShort1;testeShort2;", tracksEntity.get(0).getTracksShort());
    }

    @DisplayName("dado uma Track nulo " +
            "quando chamado metodo domainToEntity " +
            "então deve ser retornado nulo")
    @Test
    void test3(){
        List<TrackEntity> trackEntities = converter.domainToEntity((List<Track>) null);
        TrackEntity trackEntity = converter.domainToEntity((Track) null);

        assertTrue(trackEntities.isEmpty());
        assertNull(trackEntity);
    }

    @DisplayName("dado uma TrackEntity nulo " +
            "quando chamado metodo entityToDomain " +
            "então deve ser retornado nulo")
    @Test
    void test4(){
        List<Track> tracks = converter.entityToDomain((List<TrackEntity>) null);
        Track track = converter.entityToDomain((TrackEntity) null);

        assertTrue(tracks.isEmpty());
        assertNull(track);
    }
}