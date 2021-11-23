package br.com.spotifyjvcw.host.data.request;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TrackRequest {

    private LocalDate date;

    private String[] tracksLong;
    private String[] tracksMedium;
    private String[] tracksShort;
}
