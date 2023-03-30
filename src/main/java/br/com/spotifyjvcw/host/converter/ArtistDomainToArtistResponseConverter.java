package br.com.spotifyjvcw.host.converter;

import br.com.spotifyjvcw.domain.HistoricArtist;
import br.com.spotifyjvcw.host.data.response.ArtistResponse;

import java.util.List;

public interface ArtistDomainToArtistResponseConverter {

    List<ArtistResponse> execute(List<HistoricArtist> artistsDomain);

    ArtistResponse execute(HistoricArtist historicArtistDomain);
}
