package br.com.spotifyjvcw.usecase;

import br.com.spotifyjvcw.domain.TermSearch;
import org.springframework.core.io.ByteArrayResource;

import java.time.LocalDate;

public interface CreateCsv {

    ByteArrayResource generateArtist(TermSearch termSearch, String clientId, LocalDate csvDate);
    ByteArrayResource generateTrack(TermSearch termSearch, String clientId, LocalDate csvDate);
}
