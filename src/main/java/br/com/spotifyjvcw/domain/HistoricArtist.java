package br.com.spotifyjvcw.domain;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HistoricArtist {

    private LocalDate date;

    private String[] artistsLong;
    private String[] artistsMedium;
    private String[] artistsShort;

    private Token token;

    public String[] getByTerm(TermSearch termSearch) {
        switch (termSearch) {
            case LONG:
                return artistsLong;
            case MEDIUM:
                return artistsMedium;
            case SHORT:
                return artistsShort;
        }
        return new String[]{};
    }
}