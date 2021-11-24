package br.com.spotifyjvcw.usecase.converter;

import br.com.spotifyjvcw.domain.Artist;
import br.com.spotifyjvcw.host.data.request.ArtistRequest;
import br.com.spotifyjvcw.usecase.converter.impl.ArtistRequestToArtistDomainConverterImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ArtistRequestToArtistDomainConverterTest {

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

        List<Artist> artists = converter.execute(artistRequests, "testeId");

        assertNotNull(artists);
        assertEquals("testeLong", artists.get(0).getArtistsLong()[0]);
        assertEquals("testeMedium", artists.get(0).getArtistsMedium()[0]);
        assertEquals("testeShort", artists.get(0).getArtistsShort()[0]);
        assertEquals(LocalDate.now().toString(), artists.get(0).getDate().toString());
    }
}