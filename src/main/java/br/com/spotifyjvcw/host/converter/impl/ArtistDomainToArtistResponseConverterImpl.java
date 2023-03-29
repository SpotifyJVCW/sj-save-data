package br.com.spotifyjvcw.host.converter.impl;

import br.com.spotifyjvcw.domain.HistoricArtist;
import br.com.spotifyjvcw.host.converter.ArtistDomainToArtistResponseConverter;
import br.com.spotifyjvcw.host.data.response.ArtistResponse;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ArtistDomainToArtistResponseConverterImpl implements ArtistDomainToArtistResponseConverter {
    @Override
    public List<ArtistResponse> execute(List<HistoricArtist> artistsDomain) {
        return artistsDomain.stream().map(this::execute).collect(Collectors.toList());
    }

    @Override
    public ArtistResponse execute(HistoricArtist historicArtistDomain) {
        return ArtistResponse.builder()
                .date(historicArtistDomain.getDate())
                .artistsLong(historicArtistDomain.getArtistsLong())
                .artistsMedium(historicArtistDomain.getArtistsMedium())
                .artistsShort(historicArtistDomain.getArtistsShort()).build();
    }
}
