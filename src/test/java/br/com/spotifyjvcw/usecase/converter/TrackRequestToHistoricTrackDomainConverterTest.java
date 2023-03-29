package br.com.spotifyjvcw.usecase.converter;

import br.com.spotifyjvcw.domain.HistoricTrack;
import br.com.spotifyjvcw.host.data.request.TrackRequest;
import br.com.spotifyjvcw.usecase.converter.impl.TrackRequestToTrackDomainConverterImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TrackRequestToHistoricTrackDomainConverterTest {
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

        List<HistoricTrack> historicTracks = converter.execute(trackRequests, "testeId");

        assertNotNull(historicTracks);
        assertEquals("testeLong", historicTracks.get(0).getTracksLong()[0]);
        assertEquals("testeMedium", historicTracks.get(0).getTracksMedium()[0]);
        assertEquals("testeShort", historicTracks.get(0).getTracksShort()[0]);
        assertEquals(LocalDate.now().toString(), historicTracks.get(0).getDate().toString());
    }
}