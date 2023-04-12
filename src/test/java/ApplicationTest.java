import br.com.spotifyjvcw.Application;
import br.com.spotifyjvcw.domain.Artist;
import br.com.spotifyjvcw.domain.contract.Position;
import br.com.spotifyjvcw.usecase.BuildPosition;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles("test")
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes = {Application.class})
public class ApplicationTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BuildPosition buildPosition;


    @Test
    public void test1() throws Exception {
        when(buildPosition.buildArtist(Mockito.any(), Mockito.anyString(), Mockito.any())).thenReturn(createMockPosition());
        when(buildPosition.buildTrack(Mockito.any(), Mockito.anyString(), Mockito.any())).thenReturn(createMockPosition());

        mockMvc.perform(MockMvcRequestBuilders.get("/rotine/mail/test")).andExpect(status().isOk());

    }

    private List<Position> createMockPosition() {
        List<Position> positionList = new ArrayList<>();

        positionList.add(new Artist("1", "teste1", 1, 10));
        positionList.add(new Artist("2", "teste2", 1, 10));
        positionList.add(new Artist("3", "teste3", 1, 10));
        positionList.add(new Artist("4", "teste4", 10, 1));
        positionList.add(new Artist("5", "teste5", 10, 1));
        positionList.add(new Artist("6", "teste6", 10, 10));
        positionList.add(new Artist("7", "teste7", 10, 10));
        positionList.add(new Artist("8", "teste8", 1, 10));
        positionList.add(new Artist("9", "teste9", 1, 10));
        positionList.add(new Artist("10", "teste10", 1, 10));
        positionList.add(new Artist("11", "teste11", 1, 10));

        return positionList;
    }
}
