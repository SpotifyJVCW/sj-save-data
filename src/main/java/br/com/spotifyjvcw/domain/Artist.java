package br.com.spotifyjvcw.domain;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Artist {

    private LocalDate date;

    private String[] artistsLong;
    private String[] artistsMedium;
    private String[] artistsShort;

    private Token token;
}