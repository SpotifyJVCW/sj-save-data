package br.com.spotifyjvcw.usecase.impl;

import br.com.spotifyjvcw.domain.HistoricTrack;
import br.com.spotifyjvcw.exception.especific.InternalServerErrorException;
import br.com.spotifyjvcw.exception.especific.NotFoundException;
import br.com.spotifyjvcw.gateway.TrackDBGateway;
import br.com.spotifyjvcw.host.data.request.TrackRequest;
import br.com.spotifyjvcw.usecase.TrackInteractionsWithDB;
import br.com.spotifyjvcw.usecase.converter.TrackRequestToTrackDomainConverter;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

import static java.util.Objects.isNull;

@Component
@RequiredArgsConstructor
@Log
public class TrackInteractionsWithDBImpl implements TrackInteractionsWithDB {

    private final TrackDBGateway trackDBGateway;
    private final TrackRequestToTrackDomainConverter toTrackDomainConverter;


    @Override
    public List<HistoricTrack> getAll() {
        return trackDBGateway.getAllTracks();
    }

    @Override
    public HistoricTrack getByDate(LocalDate date, String clientId) {
        HistoricTrack historicTrack;

        try {
            historicTrack = trackDBGateway.getTrackByDateAndClientId(date, clientId);
        }
        catch (Exception e){
            log.severe(e.getMessage());
            throw new InternalServerErrorException(e.getMessage(), e.getCause());
        }

        if (isNull(historicTrack))
            throw new NotFoundException("Objeto Track não econtrado!");

        return historicTrack;
    }

    @Override
    public void saveAll(List<TrackRequest> tracks, String clientId) {

        try {
            trackDBGateway.saveAllTracks(toTrackDomainConverter.execute(tracks, clientId));
        }
        catch (Exception e){
            log.severe(e.getMessage());
            throw new InternalServerErrorException("Erro ao salvar tracks!", e.getCause());
        }
    }
}
