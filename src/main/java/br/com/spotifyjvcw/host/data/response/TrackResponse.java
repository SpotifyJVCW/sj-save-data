package br.com.spotifyjvcw.host.data.response;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TrackResponse {

    private LocalDate date;

    private String[] tracksLong;
    private String[] tracksMedium;
    private String[] tracksShort;
}
