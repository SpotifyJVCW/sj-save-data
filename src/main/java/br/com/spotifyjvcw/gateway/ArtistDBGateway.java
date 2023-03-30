package br.com.spotifyjvcw.gateway;

import br.com.spotifyjvcw.domain.HistoricArtist;

import java.time.LocalDate;
import java.util.List;

public interface ArtistDBGateway {

    List<HistoricArtist> getAllArtists();

    HistoricArtist getArtistByDateAndClientId(LocalDate date, String clientId);

    void saveAllArtists(List<HistoricArtist> historicArtists);
}
