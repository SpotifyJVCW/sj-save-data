package br.com.spotifyjvcw.gateway;

import br.com.spotifyjvcw.domain.Track;

import java.time.LocalDate;
import java.util.List;

public interface TrackDBGateway {

    List<Track> getAllTracks();

    Track getTrackByDateAndClientId(LocalDate date, String clientId);

    void saveAllTracks(List<Track> tracks);
}
