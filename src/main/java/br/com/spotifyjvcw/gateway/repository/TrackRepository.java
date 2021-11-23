package br.com.spotifyjvcw.gateway.repository;

import br.com.spotifyjvcw.gateway.entity.TrackEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public interface TrackRepository extends JpaRepository<TrackEntity, LocalDate> {

    @Query("select track from TrackEntity track " +
            "JOIN track.tokenEntity token " +
            "WHERE token.clientId = :clientId " +
            "AND track.date = :date")
    TrackEntity findTrackByClientIdAndDate(String clientId, LocalDate date);
}
