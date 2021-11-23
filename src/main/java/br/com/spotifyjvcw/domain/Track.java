package br.com.spotifyjvcw.domain;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Track {

    private LocalDate date;

    private String[] tracksLong;
    private String[] tracksMedium;
    private String[] tracksShort;

    private Token token;
}