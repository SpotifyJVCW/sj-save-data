package br.com.spotifyjvcw.usecase.converter;

import br.com.spotifyjvcw.domain.HistoricArtist;
import br.com.spotifyjvcw.host.data.request.ArtistRequest;
import br.com.spotifyjvcw.usecase.converter.impl.ArtistRequestToArtistDomainConverterImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ArtistRequestToHistoricArtistDomainConverterTest {

    ArtistRequestToArtistDomainConverter converter;

    @BeforeEach
    public void setUp(){
        converter = new ArtistRequestToArtistDomainConverterImpl();
    }

    @DisplayName("dado uma lista de ArtistRequest e um clientId " +
                 "quando chamado metodo execute " +
                 "então deve ser retornado uma Lista de Artists")
    @Test
    void test1(){
        List<ArtistRequest> artistRequests = new ArrayList<>();
        artistRequests.add(ArtistRequest.builder()
                .artistsLong(new String[]{"testeLong"})
                .artistsMedium(new String[]{"testeMedium"})
                .artistsShort(new String[]{"testeShort"})
                .date(LocalDate.now()).build());

        List<HistoricArtist> historicArtists = converter.execute(artistRequests, "testeId");

        assertNotNull(historicArtists);
        assertEquals("testeLong", historicArtists.get(0).getArtistsLong()[0]);
        assertEquals("testeMedium", historicArtists.get(0).getArtistsMedium()[0]);
        assertEquals("testeShort", historicArtists.get(0).getArtistsShort()[0]);
        assertEquals(LocalDate.now().toString(), historicArtists.get(0).getDate().toString());
    }
}