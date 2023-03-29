package br.com.spotifyjvcw.host.converter.impl;

import br.com.spotifyjvcw.domain.HistoricTrack;
import br.com.spotifyjvcw.host.converter.TrackDomainToTrackResponseConverter;
import br.com.spotifyjvcw.host.data.response.TrackResponse;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class TrackDomainToTrackResponseConverterImpl implements TrackDomainToTrackResponseConverter {
    @Override
    public List<TrackResponse> execute(List<HistoricTrack> tracksDomain) {
        return tracksDomain.stream().map(this::execute).collect(Collectors.toList());
    }

    @Override
    public TrackResponse execute(HistoricTrack historicTrackDomain) {
        return TrackResponse.builder()
                .date(historicTrackDomain.getDate())
                .tracksLong(historicTrackDomain.getTracksLong())
                .tracksMedium(historicTrackDomain.getTracksMedium())
                .tracksShort(historicTrackDomain.getTracksShort()).build();
    }
}
