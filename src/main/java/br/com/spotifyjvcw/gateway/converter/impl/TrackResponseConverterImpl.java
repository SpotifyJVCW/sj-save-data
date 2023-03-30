package br.com.spotifyjvcw.gateway.converter.impl;

import br.com.spotifyjvcw.domain.Track;
import br.com.spotifyjvcw.gateway.converter.ArtistResponseConverter;
import br.com.spotifyjvcw.gateway.converter.TrackResponseConverter;
import br.com.spotifyjvcw.gateway.feign.data.response.TrackResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;

@Component
@RequiredArgsConstructor
public class TrackResponseConverterImpl implements TrackResponseConverter {

    private final ArtistResponseConverter artistResponseConverter;
    @Override
    public List<Track> convertToDomain(List<TrackResponse> trackResponseList) {
        if (isNull(trackResponseList))
            return new ArrayList<>();

        return trackResponseList.stream().map(this::convertToDomain).collect(Collectors.toList());
    }

    private Track convertToDomain(TrackResponse trackResponse) {
        if (isNull(trackResponse))
            return null;

        return Track.builder()
                .id(trackResponse.getId())
                .artist(artistResponseConverter.convertToDomain(trackResponse.getArtistResponse()))
                .name(trackResponse.getName())
                .build();
    }
}
