package br.com.spotifyjvcw.gateway.entity;

import br.com.spotifyjvcw.gateway.entity.ids.TrackId;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TrackEntity {

    @EmbeddedId
    private TrackId trackId;

    @Column(length = 2000)
    private String tracksLong;
    @Column(length = 2000)
    private String tracksMedium;
    @Column(length = 2000)
    private String tracksShort;
}
