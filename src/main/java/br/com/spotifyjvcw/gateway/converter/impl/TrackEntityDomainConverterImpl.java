package br.com.spotifyjvcw.gateway.converter.impl;

import br.com.spotifyjvcw.domain.Track;
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
    public List<Track> entityToDomain(List<TrackEntity> trackEntities) {
        if(isNull(trackEntities))
            return new ArrayList<>();

        return trackEntities.stream().map(this::entityToDomain).collect(Collectors.toList());
    }

    @Override
    public List<TrackEntity> domainToEntity(List<Track> trackDomains) {
        if(isNull(trackDomains))
            return new ArrayList<>();

        return trackDomains.stream().map(this::domainToEntity).collect(Collectors.toList());
    }


    @Override
    public Track entityToDomain(TrackEntity trackEntity) {
        if(isNull(trackEntity))
            return null;

        return Track.builder()
                .date(trackEntity.getTrackId().getDate())
                .tracksLong(toArray(trackEntity.getTracksLong()))
                .tracksMedium(toArray(trackEntity.getTracksMedium()))
                .tracksShort(toArray(trackEntity.getTracksShort()))
                .token(tokenEntityDomainConverter
                        .entityToDomain(trackEntity.getTrackId().getTokenEntity())).build();
    }

    @Override
    public TrackEntity domainToEntity(Track trackDomain) {
        if(isNull(trackDomain))
            return null;

        return TrackEntity.builder()
                .tracksLong(toString(trackDomain.getTracksLong()))
                .tracksMedium(toString(trackDomain.getTracksMedium()))
                .tracksShort(toString(trackDomain.getTracksShort()))
                .trackId(TrackId.builder()
                        .date(trackDomain.getDate())
                        .tokenEntity(tokenEntityDomainConverter
                                .domainToEntity(trackDomain.getToken())).build()).build();
    }

    private String[] toArray(String string){
        return ";".split(string);
    }

    private String toString(String[] array){
        StringBuilder string = new StringBuilder();

        for (String s : array) string.append(s).append(";");

        return string.toString();
    }
}
