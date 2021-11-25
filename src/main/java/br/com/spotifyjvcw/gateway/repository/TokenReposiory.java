package br.com.spotifyjvcw.gateway.repository;

import br.com.spotifyjvcw.gateway.entity.TokenEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TokenReposiory extends JpaRepository<TokenEntity, String> {
}
