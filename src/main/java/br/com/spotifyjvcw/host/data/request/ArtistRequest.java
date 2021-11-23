package br.com.spotifyjvcw.host.data.request;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ArtistRequest {

    private LocalDate date;

    private String[] artistsLong;
    private String[] artistsMedium;
    private String[] artistsShort;
}
