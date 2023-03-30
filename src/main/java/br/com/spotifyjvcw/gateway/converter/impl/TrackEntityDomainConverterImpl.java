package br.com.spotifyjvcw.gateway.converter.impl;

import br.com.spotifyjvcw.domain.HistoricTrack;
import br.com.spotifyjvcw.gateway.converter.TokenEntityDomainConverter;
import br.com.spotifyjvcw.gateway.converter.TrackEntityDomainConverter;
import br.com.spotifyjvcw.gateway.entity.TrackEntity;
import br.com.spotifyjvcw.gateway.entity.ids.TrackId;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;

@Component
@RequiredArgsConstructor
public class TrackEntityDomainConverterImpl implements TrackEntityDomainConverter {

    private final TokenEntityDomainConverter tokenEntityDomainConverter;

    @Override
    public List<HistoricTrack> entityToDomain(List<TrackEntity> trackEntities) {
        if(isNull(trackEntities))
            return new ArrayList<>();

        return trackEntities.stream().map(this::entityToDomain).collect(Collectors.toList());
    }

    @Override
    public List<TrackEntity> domainToEntity(List<HistoricTrack> historicTrackDomains) {
        if(isNull(historicTrackDomains))
            return new ArrayList<>();

        return historicTrackDomains.stream().map(this::domainToEntity).collect(Collectors.toList());
    }


    @Override
    public HistoricTrack entityToDomain(TrackEntity trackEntity) {
        if(isNull(trackEntity))
            return null;

        return HistoricTrack.builder()
                .date(trackEntity.getTrackId().getDate())
                .tracksLong(toArray(trackEntity.getTracksLong()))
                .tracksMedium(toArray(trackEntity.getTracksMedium()))
                .tracksShort(toArray(trackEntity.getTracksShort()))
                .token(tokenEntityDomainConverter
                        .entityToDomain(trackEntity.getTrackId().getTokenEntity())).build();
    }

    @Override
    public TrackEntity domainToEntity(HistoricTrack historicTrackDomain) {
        if(isNull(historicTrackDomain))
            return null;

        return TrackEntity.builder()
                .tracksLong(toString(historicTrackDomain.getTracksLong()))
                .tracksMedium(toString(historicTrackDomain.getTracksMedium()))
                .tracksShort(toString(historicTrackDomain.getTracksShort()))
                .trackId(TrackId.builder()
                        .date(historicTrackDomain.getDate())
                        .tokenEntity(tokenEntityDomainConverter
                                .domainToEntity(historicTrackDomain.getToken())).build()).build();
    }

    private String[] toArray(String string){
        return string.split(";");
    }

    private String toString(String[] array){
        StringBuilder string = new StringBuilder();

        for (String s : array) string.append(s).append(";");

        return string.toString();
    }
}
