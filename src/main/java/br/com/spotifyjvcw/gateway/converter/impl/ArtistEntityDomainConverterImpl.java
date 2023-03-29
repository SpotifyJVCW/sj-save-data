package br.com.spotifyjvcw.gateway.converter.impl;

import br.com.spotifyjvcw.domain.HistoricArtist;
import br.com.spotifyjvcw.gateway.converter.TokenEntityDomainConverter;
import br.com.spotifyjvcw.gateway.converter.ArtistEntityDomainConverter;
import br.com.spotifyjvcw.gateway.entity.ArtistEntity;
import br.com.spotifyjvcw.gateway.entity.ids.ArtistId;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;

@Component
@RequiredArgsConstructor
public class ArtistEntityDomainConverterImpl implements ArtistEntityDomainConverter {

    private final TokenEntityDomainConverter tokenEntityDomainConverter;

    @Override
    public List<HistoricArtist> entityToDomain(List<ArtistEntity> artistEntities) {
        if(isNull(artistEntities))
            return new ArrayList<>();

        return artistEntities.stream().map(this::entityToDomain).collect(Collectors.toList());
    }

    @Override
    public List<ArtistEntity> domainToEntity(List<HistoricArtist> artistsDomains) {
        if(isNull(artistsDomains))
            return new ArrayList<>();

        return artistsDomains.stream().map(this::domainToEntity).collect(Collectors.toList());
    }


    @Override
    public HistoricArtist entityToDomain(ArtistEntity artistEntity) {
        if(isNull(artistEntity))
            return null;

        return HistoricArtist.builder()
                .date(artistEntity.getArtistId().getDate())
                .artistsLong(toArray(artistEntity.getArtistsLong()))
                .artistsMedium(toArray(artistEntity.getArtistsMedium()))
                .artistsShort(toArray(artistEntity.getArtistsShort()))
                .token(tokenEntityDomainConverter
                        .entityToDomain(artistEntity.getArtistId().getTokenEntity())).build();
    }

    @Override
    public ArtistEntity domainToEntity(HistoricArtist historicArtistDomain) {
        if(isNull(historicArtistDomain))
            return null;

        return ArtistEntity.builder()
                .artistsLong(toString(historicArtistDomain.getArtistsLong()))
                .artistsMedium(toString(historicArtistDomain.getArtistsMedium()))
                .artistsShort(toString(historicArtistDomain.getArtistsShort()))
                .artistId(ArtistId.builder()
                        .date(historicArtistDomain.getDate())
                        .tokenEntity(tokenEntityDomainConverter
                                .domainToEntity(historicArtistDomain.getToken())).build()).build();
    }

    private String[] toArray(String string){
        return string.split(";");
    }

    private String toString(String[] array){
        StringBuilder string = new StringBuilder();

        for (String s : array) string.append(s).append(";");

        return string.toString();
    }
}
