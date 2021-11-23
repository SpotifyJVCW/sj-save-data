package br.com.spotifyjvcw.usecase;

import br.com.spotifyjvcw.domain.Track;
import br.com.spotifyjvcw.host.data.request.TrackRequest;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface TrackInteractionsWithDB {

    List<Track> getAll();

    Optional<Track> getByDate(LocalDate date, String clientId);

    void saveAll(List<TrackRequest> tracks, String clientId);

}
