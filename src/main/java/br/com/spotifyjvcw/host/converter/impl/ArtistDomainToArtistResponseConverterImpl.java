package br.com.spotifyjvcw.host.converter.impl;

import br.com.spotifyjvcw.domain.Artist;
import br.com.spotifyjvcw.host.converter.ArtistDomainToArtistResponseConverter;
import br.com.spotifyjvcw.host.data.response.ArtistResponse;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ArtistDomainToArtistResponseConverterImpl implements ArtistDomainToArtistResponseConverter {
    @Override
    public List<ArtistResponse> execute(List<Artist> artistsDomain) {
        return artistsDomain.stream().map(this::execute).collect(Collectors.toList());
    }

    @Override
    public ArtistResponse execute(Artist artistDomain) {
        return ArtistResponse.builder()
                .date(artistDomain.getDate())
                .artistsLong(artistDomain.getArtistsLong())
                .artistsMedium(artistDomain.getArtistsMedium())
                .artistsShort(artistDomain.getArtistsShort()).build();
    }
}
