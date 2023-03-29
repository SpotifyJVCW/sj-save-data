package br.com.spotifyjvcw.gateway.impl;

import br.com.spotifyjvcw.domain.HistoricTrack;
import br.com.spotifyjvcw.gateway.TrackDBGateway;
import br.com.spotifyjvcw.gateway.converter.TrackEntityDomainConverter;
import br.com.spotifyjvcw.gateway.repository.TrackRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
@RequiredArgsConstructor
public class TrackDBGatewayImpl implements TrackDBGateway {

    private final TrackRepository trackRepository;
    private final TrackEntityDomainConverter entityDomainConverter;

    @Override
    public List<HistoricTrack> getAllTracks() {
        return entityDomainConverter.entityToDomain(trackRepository.findAll());
    }

    @Override
    public HistoricTrack getTrackByDateAndClientId(LocalDate date, String clientId) {
        return entityDomainConverter.entityToDomain(
                trackRepository.findTrackByClientIdAndDate(clientId, date));
    }

    @Override
    public void saveAllTracks(List<HistoricTrack> historicTracks) {
        trackRepository.saveAll(entityDomainConverter.domainToEntity(historicTracks));
    }
}
