package br.com.spotifyjvcw.gateway.feign.data.response;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ArtistResponse {
    private String id;
    private String name;
    private String[] genres;
    private Integer popularity;
}

