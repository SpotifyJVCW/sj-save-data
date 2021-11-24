package br.com.spotifyjvcw.usecase.converter;

import br.com.spotifyjvcw.domain.Track;
import br.com.spotifyjvcw.host.data.request.TrackRequest;
import br.com.spotifyjvcw.usecase.converter.impl.TrackRequestToTrackDomainConverterImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TrackRequestToTrackDomainConverterTest {
    TrackRequestToTrackDomainConverter converter;

    @BeforeEach
    public void setUp(){
        converter = new TrackRequestToTrackDomainConverterImpl();
    }

    @DisplayName("dado uma lista de TrackRequest e um clientId " +
            "quando chamado metodo execute " +
            "então deve ser retornado uma Lista de Tracks")
    @Test
    void test1(){
        List<TrackRequest> trackRequests = new ArrayList<>();
        trackRequests.add(TrackRequest.builder()
                .tracksLong(new String[]{"testeLong"})
                .tracksMedium(new String[]{"testeMedium"})
                .tracksShort(new String[]{"testeShort"})
                .date(LocalDate.now()).build());

        List<Track> tracks = converter.execute(trackRequests, "testeId");

        assertNotNull(tracks);
        assertEquals("testeLong", tracks.get(0).getTracksLong()[0]);
        assertEquals("testeMedium", tracks.get(0).getTracksMedium()[0]);
        assertEquals("testeShort", tracks.get(0).getTracksShort()[0]);
        assertEquals(LocalDate.now().toString(), tracks.get(0).getDate().toString());
    }
}