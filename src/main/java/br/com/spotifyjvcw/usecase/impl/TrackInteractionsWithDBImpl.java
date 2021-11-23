package br.com.spotifyjvcw.usecase.impl;

import br.com.spotifyjvcw.domain.Track;
import br.com.spotifyjvcw.gateway.TrackDBGateway;
import br.com.spotifyjvcw.host.data.request.TrackRequest;
import br.com.spotifyjvcw.usecase.TrackInteractionsWithDB;
import br.com.spotifyjvcw.usecase.converter.TrackRequestToTrackDomainConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static java.util.Objects.isNull;

@Component
@RequiredArgsConstructor
public class TrackInteractionsWithDBImpl implements TrackInteractionsWithDB {

    private final TrackDBGateway trackDBGateway;
    private final TrackRequestToTrackDomainConverter toTrackDomainConverter;


    @Override
    public List<Track> getAll() {
        return trackDBGateway.getAllTracks();
    }

    @Override
    public Optional<Track> getByDate(LocalDate date, String clientId) {
        Track track = trackDBGateway.getTrackByDateAndClientId(date, clientId);

        if (isNull(track))
            return Optional.empty();

        return Optional.of(track);
    }

    @Override
    public void saveAll(List<TrackRequest> tracks, String clientId) {

        try {
            trackDBGateway.saveAllTracks(toTrackDomainConverter.execute(tracks, clientId));
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
