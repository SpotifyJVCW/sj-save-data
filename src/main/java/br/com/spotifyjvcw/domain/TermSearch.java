package br.com.spotifyjvcw.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TermSearch {

    LONG(1),
    MEDIUM(5),
    SHORT(10);

    private final int minPosition;
}
