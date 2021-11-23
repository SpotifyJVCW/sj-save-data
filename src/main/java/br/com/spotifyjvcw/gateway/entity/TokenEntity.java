package br.com.spotifyjvcw.gateway.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TokenEntity implements Serializable{

    private static final long SEARIAL_VERSION_ID = 1L;

    @Id
    private String clientId;

    private String accessToken;
    private String refreshToken;
}
