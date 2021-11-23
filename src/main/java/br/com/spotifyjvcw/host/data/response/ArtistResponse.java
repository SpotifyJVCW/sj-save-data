package br.com.spotifyjvcw.host.data.response;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ArtistResponse {

    private LocalDate date;

    private String[] artistsLong;
    private String[] artistsMedium;
    private String[] artistsShort;
}
