package br.com.spotifyjvcw.gateway.converter.impl;

import br.com.spotifyjvcw.domain.Artist;
import br.com.spotifyjvcw.gateway.converter.TokenEntityDomainConverter;
import br.com.spotifyjvcw.gateway.converter.ArtistEntityDomainConverter;
import br.com.spotifyjvcw.gateway.entity.ArtistEntity;
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
    public List<Artist> entityToDomain(List<ArtistEntity> artistEntities) {
        if(isNull(artistEntities))
            return new ArrayList<>();

        return artistEntities.stream().map(this::entityToDomain).collect(Collectors.toList());
    }

    @Override
    public List<ArtistEntity> domainToEntity(List<Artist> artistsDomains) {
        if(isNull(artistsDomains))
            return new ArrayList<>();

        return artistsDomains.stream().map(this::domainToEntity).collect(Collectors.toList());
    }


    @Override
    public Artist entityToDomain(ArtistEntity artistEntity) {
        if(isNull(artistEntity))
            return null;

        return Artist.builder()
                .date(artistEntity.getDate())
                .artistsLong(toArray(artistEntity.getArtistsLong()))
                .artistsMedium(toArray(artistEntity.getArtistsMedium()))
                .artistsShort(toArray(artistEntity.getArtistsShort()))
                .token(tokenEntityDomainConverter
                        .entityToDomain(artistEntity.getTokenEntity())).build();
    }

    @Override
    public ArtistEntity domainToEntity(Artist artistDomain) {
        if(isNull(artistDomain))
            return null;

        return ArtistEntity.builder()
                .date(artistDomain.getDate())
                .artistsLong(toString(artistDomain.getArtistsLong()))
                .artistsMedium(toString(artistDomain.getArtistsMedium()))
                .artistsShort(toString(artistDomain.getArtistsShort()))
                .tokenEntity(tokenEntityDomainConverter
                        .domainToEntity(artistDomain.getToken())).build();
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
