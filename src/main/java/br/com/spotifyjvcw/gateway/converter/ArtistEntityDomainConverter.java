package br.com.spotifyjvcw.gateway.converter;

import br.com.spotifyjvcw.domain.HistoricArtist;
import br.com.spotifyjvcw.gateway.entity.ArtistEntity;

import java.util.List;

public interface ArtistEntityDomainConverter {

    HistoricArtist entityToDomain(ArtistEntity artistEntity);
    List<HistoricArtist> entityToDomain(List<ArtistEntity> artistEntities);

    ArtistEntity domainToEntity(HistoricArtist historicArtistDomain);
    List<ArtistEntity> domainToEntity(List<HistoricArtist> historicArtistDomains);
}
