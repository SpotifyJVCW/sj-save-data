package br.com.spotifyjvcw.usecase.impl;

import br.com.spotifyjvcw.domain.*;
import br.com.spotifyjvcw.domain.contract.Position;
import br.com.spotifyjvcw.gateway.ArtistAPIGateway;
import br.com.spotifyjvcw.gateway.ArtistDBGateway;
import br.com.spotifyjvcw.gateway.TrackAPIGateway;
import br.com.spotifyjvcw.gateway.TrackDBGateway;
import br.com.spotifyjvcw.usecase.CreateCsv;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Component
@RequiredArgsConstructor
public class CreateCsvImpl implements CreateCsv {

    private final ArtistAPIGateway artistAPIGateway;
    private final ArtistDBGateway artistDBGateway;
    private final TrackDBGateway trackDBGateway;
    private final TrackAPIGateway trackAPIGateway;

    @Override
    public ByteArrayResource generateArtist(TermSearch termSearch, String clientId, LocalDate csvDate) {
        csvDate = Objects.nonNull(csvDate) ? LocalDate.now() : csvDate;
        HistoricArtist historicArtistNormal = artistDBGateway.getArtistByDateAndClientId(csvDate, clientId);
        HistoricArtist historicArtistMinusADay = artistDBGateway.getArtistByDateAndClientId(csvDate.minusDays(1L), clientId);

        List<Artist> artistList = artistAPIGateway.findNameArtistsById(Arrays.asList(historicArtistNormal.getByTerm(termSearch)), clientId);

        List<Position> objectPositionList = new ArrayList<>(artistList);
        completetWithPosition(objectPositionList, Arrays.asList(historicArtistMinusADay.getByTerm(termSearch)));
        return new ByteArrayResource(generateArchive(objectPositionList).getBytes(StandardCharsets.UTF_8));
    }

    @Override
    public ByteArrayResource generateTrack(TermSearch termSearch, String clientId, LocalDate csvDate) {
        csvDate = Objects.nonNull(csvDate) ? LocalDate.now() : csvDate;
        HistoricTrack historicTrackNormal = trackDBGateway.getTrackByDateAndClientId(csvDate, clientId);
        HistoricTrack historicTrackMinusADay = trackDBGateway.getTrackByDateAndClientId(csvDate.minusDays(1L), clientId);

        List<Track> trackList = trackAPIGateway.findNameTrackById(Arrays.asList(historicTrackNormal.getByTerm(termSearch)), clientId);

        List<Position> objectPositionList = new ArrayList<>(trackList);
        completetWithPosition(objectPositionList, Arrays.asList(historicTrackMinusADay.getByTerm(termSearch)));
        return new ByteArrayResource(generateArchive(objectPositionList).getBytes(StandardCharsets.UTF_8));
    }

    private void completetWithPosition(List<Position> objectPositionList, List<String> minusADayArtistIdList) {
        for (int position = 0; position < objectPositionList.size(); position++) {
            Position objectPosition = objectPositionList.get(position);
            objectPosition.setPositions(position, minusADayArtistIdList.indexOf(objectPosition.getId()));
        }
    }

    private String generateArchive(List<Position> objectPositionList) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("#;Nome;Ranking\n");
        objectPositionList.forEach(position -> stringBuilder.append(position.generateLine()));

        return stringBuilder.toString();
    }
}
