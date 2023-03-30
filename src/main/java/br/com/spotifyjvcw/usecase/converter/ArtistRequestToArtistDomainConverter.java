package br.com.spotifyjvcw.usecase.converter;

import br.com.spotifyjvcw.domain.HistoricArtist;
import br.com.spotifyjvcw.host.data.request.ArtistRequest;

import java.util.List;

public interface ArtistRequestToArtistDomainConverter {

    List<HistoricArtist> execute(List<ArtistRequest> artistsRequests, String clientId);
}
