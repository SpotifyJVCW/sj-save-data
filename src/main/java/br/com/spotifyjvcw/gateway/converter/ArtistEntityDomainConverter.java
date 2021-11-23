package br.com.spotifyjvcw.gateway.converter;

import br.com.spotifyjvcw.domain.Artist;
import br.com.spotifyjvcw.gateway.entity.ArtistEntity;

import java.util.List;

public interface ArtistEntityDomainConverter {

    Artist entityToDomain(ArtistEntity artistEntity);
    List<Artist> entityToDomain(List<ArtistEntity> artistEntities);

    ArtistEntity domainToEntity(Artist artistDomain);
    List<ArtistEntity> domainToEntity(List<Artist> artistDomains);
}
