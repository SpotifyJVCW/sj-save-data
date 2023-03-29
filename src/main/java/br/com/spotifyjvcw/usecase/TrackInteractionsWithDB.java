package br.com.spotifyjvcw.usecase;

import br.com.spotifyjvcw.domain.HistoricTrack;
import br.com.spotifyjvcw.host.data.request.TrackRequest;

import java.time.LocalDate;
import java.util.List;

public interface TrackInteractionsWithDB {

    List<HistoricTrack> getAll();

    HistoricTrack getByDate(LocalDate date, String clientId);

    void saveAll(List<TrackRequest> tracks, String clientId);

}
