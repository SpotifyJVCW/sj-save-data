package br.com.spotifyjvcw.gateway.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TrackEntity implements Serializable {

    private static final long SEARIAL_VERSION_ID = 1L;

    @Id
    private LocalDate date;

    @Column(length = 2000)
    private String tracksLong;
    @Column(length = 2000)
    private String tracksMedium;
    @Column(length = 2000)
    private String tracksShort;

    @ManyToOne
    private TokenEntity tokenEntity;
}