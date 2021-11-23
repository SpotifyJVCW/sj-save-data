package br.com.spotifyjvcw.gateway.repository;

import br.com.spotifyjvcw.gateway.entity.ArtistEntity;
import br.com.spotifyjvcw.gateway.entity.ids.ArtistId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public interface ArtistRepository extends JpaRepository<ArtistEntity, ArtistId> {

    @Query("select artist from ArtistEntity artist " +
            "JOIN artist.artistId.tokenEntity token " +
            "WHERE token.clientId = :clientId " +
            "AND artist.artistId.date = :date")
    ArtistEntity findArtistByClientIdAndDate(String clientId, LocalDate date);
}
