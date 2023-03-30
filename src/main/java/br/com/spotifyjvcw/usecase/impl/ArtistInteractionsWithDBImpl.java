package br.com.spotifyjvcw.usecase.impl;

import br.com.spotifyjvcw.domain.HistoricArtist;
import br.com.spotifyjvcw.exception.especific.InternalServerErrorException;
import br.com.spotifyjvcw.exception.especific.NotFoundException;
import br.com.spotifyjvcw.gateway.ArtistDBGateway;
import br.com.spotifyjvcw.host.data.request.ArtistRequest;
import br.com.spotifyjvcw.usecase.ArtistInteractionsWithDB;
import br.com.spotifyjvcw.usecase.converter.ArtistRequestToArtistDomainConverter;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

import static java.util.Objects.isNull;

@Component
@RequiredArgsConstructor
@Log
public class ArtistInteractionsWithDBImpl implements ArtistInteractionsWithDB {

    private final ArtistDBGateway artistDBGateway;
    private final ArtistRequestToArtistDomainConverter toArtistDomainConverter;


    @Override
    public List<HistoricArtist> getAll() {
        return artistDBGateway.getAllArtists();
    }

    @Override
    public HistoricArtist getByDate(LocalDate date, String clientId) {
        HistoricArtist historicArtist;

        try {
            historicArtist = artistDBGateway.getArtistByDateAndClientId(date, clientId);
        }
        catch (Exception e){
            log.severe(e.getMessage());
            throw new InternalServerErrorException(e.getMessage(), e.getCause());
        }

        if (isNull(historicArtist))
            throw new NotFoundException("Objeto Artist não econtrado!");

        return historicArtist;
    }

    @Override
    public void saveAll(List<ArtistRequest> artists, String clientId) {

        try {
            artistDBGateway.saveAllArtists(toArtistDomainConverter.execute(artists, clientId));
        }
        catch (Exception e){
            log.severe(e.getMessage());
            throw new InternalServerErrorException("Erro ao salvar artists!", e.getCause());
        }
    }
}
