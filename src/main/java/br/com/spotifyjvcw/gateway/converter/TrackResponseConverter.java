package br.com.spotifyjvcw.gateway.converter;

import br.com.spotifyjvcw.domain.Track;
import br.com.spotifyjvcw.gateway.feign.data.response.TrackResponse;

import java.util.List;

public interface TrackResponseConverter {

    List<Track> convertToDomain(List<TrackResponse> trackResponseList);
}
