package br.com.spotifyjvcw.gateway.feign.impl;

import br.com.spotifyjvcw.gateway.feign.SpotifyAPIInterface;
import br.com.spotifyjvcw.gateway.feign.data.response.ArtistResponse;
import br.com.spotifyjvcw.gateway.feign.data.response.TrackResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Slf4j
@Component
public class SpotifyAPIInterfaceImpl implements SpotifyAPIInterface {

    @Value("${refresh-data.url}")
    private String domainUrl;

    @Value("${refresh-data.path.get-artists-ids}")
    private String findArtistsByIdsPath;

    @Value("${refresh-data.path.get-tracks-ids}")
    private String findTracksByIdsPath;

    @Override
    public List<ArtistResponse> findArtistByIds(String[] ids, String clientId) {
        RestTemplate request = new RestTemplate();
        ResponseEntity<ArtistResponse[]> response = request.postForEntity(
                URI.create(domainUrl + findArtistsByIdsPath + "/" + clientId), ids, ArtistResponse[].class);

        if(!response.getStatusCode().is2xxSuccessful()) {
            log.error("Houve um erro para buscar artists do clienteId: {} | Erro: {}", clientId, (Object) response.getBody());
            return new ArrayList<>();
        }

        return Arrays.asList(Objects.requireNonNull(response.getBody()));
    }

    @Override
    public List<TrackResponse> findTracksByIds(String[] ids, String clientId) {
        RestTemplate request = new RestTemplate();
        ResponseEntity<TrackResponse[]> response = request.postForEntity(
                URI.create(domainUrl + findTracksByIdsPath + "/" + clientId), ids, TrackResponse[].class);

        if(!response.getStatusCode().is2xxSuccessful()) {
            log.error("Houve um erro para buscar tracks do clienteId: {} | Erro: {}", clientId, (Object) response.getBody());
            return new ArrayList<>();
        }

        return Arrays.asList(Objects.requireNonNull(response.getBody()));
    }
}