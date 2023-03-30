package br.com.spotifyjvcw.gateway.impl;

import br.com.spotifyjvcw.domain.Artist;
import br.com.spotifyjvcw.gateway.ArtistAPIGateway;
import br.com.spotifyjvcw.gateway.converter.ArtistResponseConverter;
import br.com.spotifyjvcw.gateway.feign.SpotifyAPIInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ArtistAPIGatewayImpl implements ArtistAPIGateway {

    private final SpotifyAPIInterface spotifyAPIInterface;
    private final ArtistResponseConverter artistResponseConverter;
    @Override
    public List<Artist> findNameArtistsById(List<String> artistsId, String clientId) {
        return artistResponseConverter.convertToDomain(spotifyAPIInterface.findArtistByIds(artistsId.toArray(new String[0]), clientId));
    }
}
