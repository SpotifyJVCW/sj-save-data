package br.com.spotifyjvcw.gateway.feign.data.response;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TrackResponse {
    private String id;
    private String name;
    private String[] genres;
    private Integer popularity;
    private ArtistResponse artistResponse;
}

