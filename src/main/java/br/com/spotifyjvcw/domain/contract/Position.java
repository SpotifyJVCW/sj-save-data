package br.com.spotifyjvcw.domain.contract;

public interface Position {

    void setPositions(int newPosition, int lastPosition);
    String generateLine();
    String getId();
}
