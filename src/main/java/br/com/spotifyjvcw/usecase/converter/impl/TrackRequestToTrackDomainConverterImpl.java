package br.com.spotifyjvcw.usecase.converter.impl;

import br.com.spotifyjvcw.domain.Token;
import br.com.spotifyjvcw.domain.Track;
import br.com.spotifyjvcw.host.data.request.TrackRequest;
import br.com.spotifyjvcw.usecase.converter.TrackRequestToTrackDomainConverter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class TrackRequestToTrackDomainConverterImpl implements TrackRequestToTrackDomainConverter {

    @Override
    public List<Track> execute(List<TrackRequest> trackRequests, String clientId) {
        List<Track> tracks = new ArrayList<>();

        for(TrackRequest request : trackRequests){
            tracks.add(execute(request, clientId));
        }

        return tracks;
    }

    private Track execute(TrackRequest trackRequest, String clientId){
        return Track.builder()
                .date(trackRequest.getDate())
                .token(Token.builder().clientId(clientId).build())
                .tracksLong(trackRequest.getTracksLong())
                .tracksMedium(trackRequest.getTracksMedium())
                .tracksShort(trackRequest.getTracksShort()).build();
    }
}
