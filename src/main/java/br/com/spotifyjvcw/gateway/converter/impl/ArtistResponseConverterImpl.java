package br.com.spotifyjvcw.gateway.converter.impl;

import br.com.spotifyjvcw.domain.Artist;
import br.com.spotifyjvcw.gateway.converter.ArtistResponseConverter;
import br.com.spotifyjvcw.gateway.feign.data.response.ArtistResponse;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;

@Component
public class ArtistResponseConverterImpl implements ArtistResponseConverter {
    @Override
    public List<Artist> convertToDomain(List<ArtistResponse> artistResponseList) {
        if (isNull(artistResponseList))
            return new ArrayList<>();

        return artistResponseList.stream().map(this::convertToDomain).collect(Collectors.toList());
    }

    @Override
    public Artist convertToDomain(ArtistResponse artistResponse) {
        if (isNull(artistResponse))
            return null;

        return Artist.builder()
                .id(artistResponse.getId())
                .name(artistResponse.getName())
                .build();
    }
}
