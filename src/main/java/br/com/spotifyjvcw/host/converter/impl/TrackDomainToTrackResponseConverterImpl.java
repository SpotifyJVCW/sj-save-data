package br.com.spotifyjvcw.host.converter.impl;

import br.com.spotifyjvcw.domain.Track;
import br.com.spotifyjvcw.host.converter.TrackDomainToTrackResponseConverter;
import br.com.spotifyjvcw.host.data.response.TrackResponse;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class TrackDomainToTrackResponseConverterImpl implements TrackDomainToTrackResponseConverter {
    @Override
    public List<TrackResponse> execute(List<Track> tracksDomain) {
        return tracksDomain.stream().map(this::execute).collect(Collectors.toList());
    }

    @Override
    public TrackResponse execute(Track trackDomain) {
        return TrackResponse.builder()
                .date(trackDomain.getDate())
                .tracksLong(trackDomain.getTracksLong())
                .tracksMedium(trackDomain.getTracksMedium())
                .tracksShort(trackDomain.getTracksShort()).build();
    }
}
