package br.com.spotifyjvcw.gateway.entity.ids;

import br.com.spotifyjvcw.gateway.entity.TokenEntity;
import lombok.*;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

@Embeddable
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ArtistId implements Serializable {

    private static final long SEARIAL_VERSION_ID = 1L;

    @ManyToOne
    private TokenEntity tokenEntity;
    private LocalDate date;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ArtistId artistId = (ArtistId) o;
        return Objects.equals(tokenEntity, artistId.tokenEntity) && Objects.equals(date, artistId.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tokenEntity, date);
    }
}
