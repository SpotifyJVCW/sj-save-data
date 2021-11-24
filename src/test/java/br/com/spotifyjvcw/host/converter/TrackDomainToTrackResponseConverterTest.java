package br.com.spotifyjvcw.host.converter;

import br.com.spotifyjvcw.domain.Track;
import br.com.spotifyjvcw.host.converter.impl.TrackDomainToTrackResponseConverterImpl;
import br.com.spotifyjvcw.host.data.response.TrackResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TrackDomainToTrackResponseConverterTest {
    TrackDomainToTrackResponseConverter converter;

    @BeforeEach
    public void setUp(){
        converter = new TrackDomainToTrackResponseConverterImpl();
    }

    @DisplayName("dado uma lista de Track " +
            "quando chamado metodo execute " +
            "então deve ser retornado uma Lista de TrackResponse")
    @Test
    void test1(){
        List<Track> tracks = new ArrayList<>();
        tracks.add(Track.builder()
                .tracksLong(new String[]{"testeLong"})
                .tracksMedium(new String[]{"testeMedium"})
                .tracksShort(new String[]{"testeShort"})
                .date(LocalDate.now()).build());

        List<TrackResponse> tracksResponse = converter.execute(tracks);

        assertNotNull(tracksResponse);
        assertEquals("testeLong", tracksResponse.get(0).getTracksLong()[0]);
        assertEquals("testeMedium", tracksResponse.get(0).getTracksMedium()[0]);
        assertEquals("testeShort", tracksResponse.get(0).getTracksShort()[0]);
        assertEquals(LocalDate.now().toString(), tracksResponse.get(0).getDate().toString());
    }
}