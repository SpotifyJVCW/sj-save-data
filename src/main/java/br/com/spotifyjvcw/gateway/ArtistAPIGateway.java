package br.com.spotifyjvcw.gateway;

import br.com.spotifyjvcw.domain.Artist;

import java.util.List;

public interface ArtistAPIGateway {

    List<Artist> findNameArtistsById(List<String> artistsId, String clientId);
}
