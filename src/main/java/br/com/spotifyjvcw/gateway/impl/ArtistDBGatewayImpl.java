package br.com.spotifyjvcw.gateway.impl;

import br.com.spotifyjvcw.domain.Artist;
import br.com.spotifyjvcw.gateway.ArtistDBGateway;
import br.com.spotifyjvcw.gateway.converter.ArtistEntityDomainConverter;
import br.com.spotifyjvcw.gateway.repository.ArtistRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
@RequiredArgsConstructor
public class ArtistDBGatewayImpl implements ArtistDBGateway {

    private final ArtistRepository artistsRepository;
    private final ArtistEntityDomainConverter entityDomainConverter;

    @Override
    public List<Artist> getAllArtists() {
        return entityDomainConverter.entityToDomain(artistsRepository.findAll());
    }

    @Override
    public Artist getArtistByDateAndClientId(LocalDate date, String clientId) {
        return entityDomainConverter.entityToDomain(
                artistsRepository.findArtistByClientIdAndDate(clientId, date));
    }

    @Override
    public void saveAllArtists(List<Artist> artists) {
        artistsRepository.saveAll(entityDomainConverter.domainToEntity(artists));
    }
}
