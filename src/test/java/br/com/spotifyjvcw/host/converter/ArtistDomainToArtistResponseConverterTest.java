package br.com.spotifyjvcw.host.converter;

import br.com.spotifyjvcw.domain.Artist;
import br.com.spotifyjvcw.host.converter.impl.ArtistDomainToArtistResponseConverterImpl;
import br.com.spotifyjvcw.host.data.response.ArtistResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ArtistDomainToArtistResponseConverterTest {

    ArtistDomainToArtistResponseConverter converter;

    @BeforeEach
    public void setUp(){
        converter = new ArtistDomainToArtistResponseConverterImpl();
    }

    @DisplayName("dado uma lista de Artist " +
                 "quando chamado metodo execute " +
                 "então deve ser retornado uma Lista de ArtistResponse")
    @Test
    void test1(){
        List<Artist> artists = new ArrayList<>();
        artists.add(Artist.builder()
                .artistsLong(new String[]{"testeLong"})
                .artistsMedium(new String[]{"testeMedium"})
                .artistsShort(new String[]{"testeShort"})
                .date(LocalDate.now()).build());

        List<ArtistResponse> artistsResponse = converter.execute(artists);

        assertNotNull(artistsResponse);
        assertEquals("testeLong", artistsResponse.get(0).getArtistsLong()[0]);
        assertEquals("testeMedium", artistsResponse.get(0).getArtistsMedium()[0]);
        assertEquals("testeShort", artistsResponse.get(0).getArtistsShort()[0]);
        assertEquals(LocalDate.now().toString(), artistsResponse.get(0).getDate().toString());
    }
}