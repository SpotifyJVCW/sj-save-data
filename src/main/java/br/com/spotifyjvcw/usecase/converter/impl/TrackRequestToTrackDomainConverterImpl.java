package br.com.spotifyjvcw.usecase.converter.impl;

import br.com.spotifyjvcw.domain.Token;
import br.com.spotifyjvcw.domain.HistoricTrack;
import br.com.spotifyjvcw.host.data.request.TrackRequest;
import br.com.spotifyjvcw.usecase.converter.TrackRequestToTrackDomainConverter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class TrackRequestToTrackDomainConverterImpl implements TrackRequestToTrackDomainConverter {

    @Override
    public List<HistoricTrack> execute(List<TrackRequest> trackRequests, String clientId) {
        List<HistoricTrack> historicTracks = new ArrayList<>();

        for(TrackRequest request : trackRequests){
            historicTracks.add(execute(request, clientId));
        }

        return historicTracks;
    }

    private HistoricTrack execute(TrackRequest trackRequest, String clientId){
        return HistoricTrack.builder()
                .date(trackRequest.getDate())
                .token(Token.builder().clientId(clientId).build())
                .tracksLong(trackRequest.getTracksLong())
                .tracksMedium(trackRequest.getTracksMedium())
                .tracksShort(trackRequest.getTracksShort()).build();
    }
}
