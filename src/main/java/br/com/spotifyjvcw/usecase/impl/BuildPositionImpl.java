package br.com.spotifyjvcw.usecase.impl;

import br.com.spotifyjvcw.domain.*;
import br.com.spotifyjvcw.domain.contract.Position;
import br.com.spotifyjvcw.gateway.ArtistAPIGateway;
import br.com.spotifyjvcw.gateway.ArtistDBGateway;
import br.com.spotifyjvcw.gateway.TrackAPIGateway;
import br.com.spotifyjvcw.gateway.TrackDBGateway;
import br.com.spotifyjvcw.usecase.BuildPosition;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Component
@RequiredArgsConstructor
public class BuildPositionImpl implements BuildPosition {

    private final ArtistAPIGateway artistAPIGateway;
    private final ArtistDBGateway artistDBGateway;
    private final TrackDBGateway trackDBGateway;
    private final TrackAPIGateway trackAPIGateway;

    @Override
    public List<Position> buildArtist(TermSearch termSearch, String clientId, LocalDate date) {
        date = Objects.nonNull(date) ? date : LocalDate.now();
        HistoricArtist historicArtistNormal = artistDBGateway.getArtistByDateAndClientId(date, clientId);
        HistoricArtist historicArtistMinusADay = artistDBGateway.getArtistByDateAndClientId(date.minusDays(1L), clientId);

        List<Artist> artistList = artistAPIGateway.findNameArtistsById(Arrays.asList(historicArtistNormal.getByTerm(termSearch)), clientId);

        List<Position> objectPositionList = new ArrayList<>(artistList);
        completetWithPosition(objectPositionList, Arrays.asList(historicArtistMinusADay.getByTerm(termSearch)));
        return objectPositionList;
    }

    @Override
    public List<Position> buildTrack(TermSearch termSearch, String clientId, LocalDate date) {
        date = Objects.nonNull(date) ? date : LocalDate.now();
        HistoricTrack historicTrackNormal = trackDBGateway.getTrackByDateAndClientId(date, clientId);
        HistoricTrack historicTrackMinusADay = trackDBGateway.getTrackByDateAndClientId(date.minusDays(1L), clientId);

        List<Track> trackList = trackAPIGateway.findNameTrackById(Arrays.asList(historicTrackNormal.getByTerm(termSearch)), clientId);

        List<Position> objectPositionList = new ArrayList<>(trackList);
        completetWithPosition(objectPositionList, Arrays.asList(historicTrackMinusADay.getByTerm(termSearch)));
        return objectPositionList;
    }

    private void completetWithPosition(List<Position> objectPositionList, List<String> minusADayPositionIdList) {
        for (int position = 0; position < objectPositionList.size(); position++) {
            Position objectPosition = objectPositionList.get(position);
            objectPosition.setPositions(position, minusADayPositionIdList.indexOf(objectPosition.getId()));
        }
    }
}
