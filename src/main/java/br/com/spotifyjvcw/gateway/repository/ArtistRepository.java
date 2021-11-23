package br.com.spotifyjvcw.gateway.repository;

import br.com.spotifyjvcw.gateway.entity.ArtistEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public interface ArtistRepository extends JpaRepository<ArtistEntity, LocalDate> {

    @Query("select artist from ArtistEntity artist " +
            "JOIN artist.tokenEntity token " +
            "WHERE token.clientId = :clientId " +
            "AND artist.date = :date")
    ArtistEntity findArtistByClientIdAndDate(String clientId, LocalDate date);
}
