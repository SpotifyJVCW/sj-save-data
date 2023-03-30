package br.com.spotifyjvcw.gateway;

import br.com.spotifyjvcw.domain.Track;

import java.util.List;

public interface TrackAPIGateway {

    List<Track> findNameTrackById(List<String> tracksId, String clientId);
}
