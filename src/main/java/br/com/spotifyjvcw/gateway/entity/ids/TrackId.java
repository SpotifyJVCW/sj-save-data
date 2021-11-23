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
public class TrackId implements Serializable {

    private static final long SEARIAL_VERSION_ID = 1L;

    @ManyToOne
    private TokenEntity tokenEntity;
    private LocalDate date;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TrackId trackId = (TrackId) o;
        return Objects.equals(tokenEntity, trackId.tokenEntity) && Objects.equals(date, trackId.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tokenEntity, date);
    }
}
