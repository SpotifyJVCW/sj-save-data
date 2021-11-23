package br.com.spotifyjvcw.host.converter;

import br.com.spotifyjvcw.domain.Track;
import br.com.spotifyjvcw.host.data.response.TrackResponse;

import java.util.List;

public interface TrackDomainToTrackResponseConverter {

    List<TrackResponse> execute(List<Track> tracksDomain);

    TrackResponse execute(Track trackDomain);
}
