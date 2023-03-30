package br.com.spotifyjvcw.gateway.impl;

import br.com.spotifyjvcw.domain.Track;
import br.com.spotifyjvcw.gateway.TrackAPIGateway;
import br.com.spotifyjvcw.gateway.converter.TrackResponseConverter;
import br.com.spotifyjvcw.gateway.feign.SpotifyAPIInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class TrackAPIGatewayImpl implements TrackAPIGateway {

    private final SpotifyAPIInterface spotifyAPIInterface;
    private final TrackResponseConverter trackResponseConverter;
    @Override
    public List<Track> findNameTrackById(List<String> tracksId, String clientId) {
        return trackResponseConverter.convertToDomain(spotifyAPIInterface.findTracksByIds(tracksId.toArray(new String[0]), clientId));
    }
}
