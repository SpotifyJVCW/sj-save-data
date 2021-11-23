package br.com.spotifyjvcw.usecase;

import br.com.spotifyjvcw.domain.Artist;
import br.com.spotifyjvcw.host.data.request.ArtistRequest;

import java.time.LocalDate;
import java.util.List;

public interface ArtistInteractionsWithDB {

    List<Artist> getAll();

    Artist getByDate(LocalDate date, String clientId);

    void saveAll(List<ArtistRequest> artists, String clientId);

}
