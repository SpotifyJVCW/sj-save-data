package br.com.spotifyjvcw.domain;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Track {

    private String id;
    private String name;
    private Integer lastPosition;
    private Integer newPosition;
    private Artist artist;
}
