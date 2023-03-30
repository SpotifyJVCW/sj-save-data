package br.com.spotifyjvcw.gateway.feign;

import br.com.spotifyjvcw.gateway.feign.data.response.ArtistResponse;
import br.com.spotifyjvcw.gateway.feign.data.response.TrackResponse;

import java.util.List;

public interface SpotifyAPIInterface {

    List<ArtistResponse> findArtistByIds(String[] ids, String clientId);
    List<TrackResponse> findTracksByIds(String[] ids, String clientId);
}
