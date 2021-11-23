package br.com.spotifyjvcw.gateway.converter;

import br.com.spotifyjvcw.domain.Track;
import br.com.spotifyjvcw.gateway.entity.TrackEntity;

import java.util.List;

public interface TrackEntityDomainConverter {

    Track entityToDomain(TrackEntity trackEntity);
    List<Track> entityToDomain(List<TrackEntity> trackEntities);

    TrackEntity domainToEntity(Track trackDomain);
    List<TrackEntity> domainToEntity(List<Track> trackDomains);
}
