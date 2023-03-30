package br.com.spotifyjvcw.usecase.converter;

import br.com.spotifyjvcw.domain.HistoricTrack;
import br.com.spotifyjvcw.host.data.request.TrackRequest;

import java.util.List;

public interface TrackRequestToTrackDomainConverter {

    List<HistoricTrack> execute(List<TrackRequest> trackRequests, String clientId);
}
