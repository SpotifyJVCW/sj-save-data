package br.com.spotifyjvcw.gateway.converter;

import br.com.spotifyjvcw.domain.HistoricTrack;
import br.com.spotifyjvcw.gateway.entity.TrackEntity;

import java.util.List;

public interface TrackEntityDomainConverter {

    HistoricTrack entityToDomain(TrackEntity trackEntity);
    List<HistoricTrack> entityToDomain(List<TrackEntity> trackEntities);

    TrackEntity domainToEntity(HistoricTrack historicTrackDomain);
    List<TrackEntity> domainToEntity(List<HistoricTrack> historicTrackDomains);
}
