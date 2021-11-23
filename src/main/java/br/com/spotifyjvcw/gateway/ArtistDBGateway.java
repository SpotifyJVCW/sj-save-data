package br.com.spotifyjvcw.gateway;

import br.com.spotifyjvcw.domain.Artist;

import java.time.LocalDate;
import java.util.List;

public interface ArtistDBGateway {

    List<Artist> getAllArtists();

    Artist getArtistByDateAndClientId(LocalDate date, String clientId);

    void saveAllArtists(List<Artist> artists);
}
