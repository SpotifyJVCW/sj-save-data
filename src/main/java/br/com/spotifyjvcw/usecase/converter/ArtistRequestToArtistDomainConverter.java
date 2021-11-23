package br.com.spotifyjvcw.usecase.converter;

import br.com.spotifyjvcw.domain.Artist;
import br.com.spotifyjvcw.host.data.request.ArtistRequest;

import java.util.List;

public interface ArtistRequestToArtistDomainConverter {

    List<Artist> execute(List<ArtistRequest> artistsRequests, String clientId);
}
