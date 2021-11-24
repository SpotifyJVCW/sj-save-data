package br.com.spotifyjvcw;

import br.com.spotifyjvcw.host.converter.ArtistDomainToArtistResponseConverter;
import br.com.spotifyjvcw.host.converter.TrackDomainToTrackResponseConverter;
import br.com.spotifyjvcw.usecase.ArtistInteractionsWithDB;
import br.com.spotifyjvcw.usecase.TrackInteractionsWithDB;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertTrue;

@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes={ Application.class })
@ActiveProfiles("local")
public class ApplicationTests {

    @MockBean
    public ArtistInteractionsWithDB artistInteractionsWithDB;

    @MockBean
    public ArtistDomainToArtistResponseConverter artistDomainToArtistResponseConverter;

    @MockBean
    public TrackInteractionsWithDB trackInteractionsWithDB;

    @MockBean
    public TrackDomainToTrackResponseConverter trackDomainToTrackResponseConverter;

    @Test
    void contextLoads(){
        assertTrue(true);
    }
}
