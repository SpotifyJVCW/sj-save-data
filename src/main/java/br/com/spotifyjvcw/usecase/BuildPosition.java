package br.com.spotifyjvcw.usecase;

import br.com.spotifyjvcw.domain.TermSearch;
import br.com.spotifyjvcw.domain.contract.Position;

import java.time.LocalDate;
import java.util.List;

public interface BuildPosition {

    List<Position> buildArtist(TermSearch termSearch, String clientId, LocalDate date);
    List<Position> buildTrack(TermSearch termSearch, String clientId, LocalDate date);
}
