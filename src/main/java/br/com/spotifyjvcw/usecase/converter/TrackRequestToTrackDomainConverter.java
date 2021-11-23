package br.com.spotifyjvcw.usecase.converter;

import br.com.spotifyjvcw.domain.Track;
import br.com.spotifyjvcw.host.data.request.TrackRequest;

import java.util.List;

public interface TrackRequestToTrackDomainConverter {

    List<Track> execute(List<TrackRequest> trackRequests, String clientId);
}
