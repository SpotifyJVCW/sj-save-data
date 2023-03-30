package br.com.spotifyjvcw.usecase.converter.impl;

import br.com.spotifyjvcw.domain.Token;
import br.com.spotifyjvcw.domain.HistoricArtist;
import br.com.spotifyjvcw.host.data.request.ArtistRequest;
import br.com.spotifyjvcw.usecase.converter.ArtistRequestToArtistDomainConverter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ArtistRequestToArtistDomainConverterImpl implements ArtistRequestToArtistDomainConverter {

    @Override
    public List<HistoricArtist> execute(List<ArtistRequest> artistRequests, String clientId) {
        List<HistoricArtist> historicArtists = new ArrayList<>();

        for(ArtistRequest request : artistRequests){
            historicArtists.add(execute(request, clientId));
        }

        return historicArtists;
    }

    private HistoricArtist execute(ArtistRequest artistRequest, String clientId){
        return HistoricArtist.builder()
                .date(artistRequest.getDate())
                .token(Token.builder().clientId(clientId).build())
                .artistsLong(artistRequest.getArtistsLong())
                .artistsMedium(artistRequest.getArtistsMedium())
                .artistsShort(artistRequest.getArtistsShort()).build();
    }
}
