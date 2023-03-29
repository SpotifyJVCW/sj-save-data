package br.com.spotifyjvcw.host.converter;

import br.com.spotifyjvcw.domain.HistoricTrack;
import br.com.spotifyjvcw.host.data.response.TrackResponse;

import java.util.List;

public interface TrackDomainToTrackResponseConverter {

    List<TrackResponse> execute(List<HistoricTrack> tracksDomain);

    TrackResponse execute(HistoricTrack historicTrackDomain);
}
