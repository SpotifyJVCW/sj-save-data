package br.com.spotifyjvcw.gateway;

import br.com.spotifyjvcw.domain.HistoricTrack;

import java.time.LocalDate;
import java.util.List;

public interface TrackDBGateway {

    List<HistoricTrack> getAllTracks();

    HistoricTrack getTrackByDateAndClientId(LocalDate date, String clientId);

    void saveAllTracks(List<HistoricTrack> historicTracks);
}
