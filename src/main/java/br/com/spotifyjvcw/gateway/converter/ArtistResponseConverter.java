package br.com.spotifyjvcw.gateway.converter;

import br.com.spotifyjvcw.domain.Artist;
import br.com.spotifyjvcw.gateway.feign.data.response.ArtistResponse;

import java.util.List;

public interface ArtistResponseConverter {

    List<Artist> convertToDomain(List<ArtistResponse> artistResponseList);
    Artist convertToDomain(ArtistResponse artistResponse);
}
