package br.com.spotifyjvcw.gateway.converter.impl;

import br.com.spotifyjvcw.domain.Track;
import br.com.spotifyjvcw.gateway.converter.TokenEntityDomainConverter;
import br.com.spotifyjvcw.gateway.converter.TrackEntityDomainConverter;
import br.com.spotifyjvcw.gateway.entity.TrackEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class TrackEntityDomainConverterImpl implements TrackEntityDomainConverter {

    private final TokenEntityDomainConverter tokenEntityDomainConverter;

    @Override
    public List<Track> entityToDomain(List<TrackEntity> trackEntities) {
        return trackEntities.stream().map(this::entityToDomain).collect(Collectors.toList());
    }

    @Override
    public List<TrackEntity> domainToEntity(List<Track> trackDomains) {
        return trackDomains.stream().map(this::domainToEntity).collect(Collectors.toList());
    }


    @Override
    public Track entityToDomain(TrackEntity trackEntity) {
        return Track.builder()
                .date(trackEntity.getDate())
                .tracksLong(toArray(trackEntity.getTracksLong()))
                .tracksMedium(toArray(trackEntity.getTracksMedium()))
                .tracksShort(toArray(trackEntity.getTracksShort()))
                .token(tokenEntityDomainConverter
                        .entityToDomain(trackEntity.getTokenEntity())).build();
    }

    @Override
    public TrackEntity domainToEntity(Track trackDomain) {
        return TrackEntity.builder()
                .date(trackDomain.getDate())
                .tracksLong(toString(trackDomain.getTracksLong()))
                .tracksMedium(toString(trackDomain.getTracksMedium()))
                .tracksShort(toString(trackDomain.getTracksShort()))
                .tokenEntity(tokenEntityDomainConverter
                        .domainToEntity(trackDomain.getToken())).build();
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
