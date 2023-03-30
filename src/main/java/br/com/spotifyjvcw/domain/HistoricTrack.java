package br.com.spotifyjvcw.domain;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HistoricTrack {

    private LocalDate date;

    private String[] tracksLong;
    private String[] tracksMedium;
    private String[] tracksShort;

    private Token token;

    public String[] getByTerm(TermSearch termSearch) {
        switch (termSearch) {
            case LONG:
                return tracksLong;
            case MEDIUM:
                return tracksMedium;
            case SHORT:
                return tracksShort;
        }
        return new String[]{};
    }
}