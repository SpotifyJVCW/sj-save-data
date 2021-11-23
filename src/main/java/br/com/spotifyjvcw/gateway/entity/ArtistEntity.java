package br.com.spotifyjvcw.gateway.entity;

import br.com.spotifyjvcw.gateway.entity.ids.ArtistId;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ArtistEntity  {

    @EmbeddedId
    private ArtistId artistId;

    @Column(length = 2000)
    private String artistsLong;
    @Column(length = 2000)
    private String artistsMedium;
    @Column(length = 2000)
    private String artistsShort;
}
