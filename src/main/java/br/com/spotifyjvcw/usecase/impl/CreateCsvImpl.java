package br.com.spotifyjvcw.usecase.impl;

import br.com.spotifyjvcw.domain.TermSearch;
import br.com.spotifyjvcw.usecase.BuildPosition;
import br.com.spotifyjvcw.usecase.CreateCsv;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.time.LocalDate;

import static br.com.spotifyjvcw.utils.PositionUtils.generateArchive;

@Component
@RequiredArgsConstructor
public class CreateCsvImpl implements CreateCsv {

    private final BuildPosition buildPosition;

    @Override
    public ByteArrayResource generateArtist(TermSearch termSearch, String clientId, LocalDate csvDate) {
        return new ByteArrayResource(generateArchive(buildPosition.buildArtist(termSearch, clientId, csvDate)).getBytes(StandardCharsets.UTF_8));
    }

    @Override
    public ByteArrayResource generateTrack(TermSearch termSearch, String clientId, LocalDate csvDate) {
        return new ByteArrayResource(generateArchive(buildPosition.buildTrack(termSearch, clientId, csvDate)).getBytes(StandardCharsets.UTF_8));
    }
}
