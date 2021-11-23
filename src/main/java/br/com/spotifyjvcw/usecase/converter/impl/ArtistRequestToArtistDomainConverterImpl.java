package br.com.spotifyjvcw.usecase.converter.impl;

import br.com.spotifyjvcw.domain.Token;
import br.com.spotifyjvcw.domain.Artist;
import br.com.spotifyjvcw.host.data.request.ArtistRequest;
import br.com.spotifyjvcw.usecase.converter.ArtistRequestToArtistDomainConverter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ArtistRequestToArtistDomainConverterImpl implements ArtistRequestToArtistDomainConverter {

    @Override
    public List<Artist> execute(List<ArtistRequest> artistRequests, String clientId) {
        List<Artist> artists = new ArrayList<>();

        for(ArtistRequest request : artistRequests){
            artists.add(execute(request, clientId));
        }

        return artists;
    }

    private Artist execute(ArtistRequest artistRequest, String clientId){
        return Artist.builder()
                .date(artistRequest.getDate())
                .token(Token.builder().clientId(clientId).build())
                .artistsLong(artistRequest.getArtistsLong())
                .artistsMedium(artistRequest.getArtistsMedium())
                .artistsShort(artistRequest.getArtistsShort()).build();
    }
}
