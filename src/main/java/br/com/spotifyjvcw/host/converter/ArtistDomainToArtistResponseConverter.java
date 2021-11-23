package br.com.spotifyjvcw.host.converter;

import br.com.spotifyjvcw.domain.Artist;
import br.com.spotifyjvcw.host.data.response.ArtistResponse;

import java.util.List;

public interface ArtistDomainToArtistResponseConverter {

    List<ArtistResponse> execute(List<Artist> artistsDomain);

    ArtistResponse execute(Artist artistDomain);
}
