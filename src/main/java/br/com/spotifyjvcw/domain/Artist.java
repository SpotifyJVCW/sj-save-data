package br.com.spotifyjvcw.domain;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Artist {

    private String id;
    private String name;
    private Integer lastPosition;
    private Integer newPosition;

    public void setPositions(int newPosition, int lastPosition) {
        this.newPosition = newPosition;
        this.lastPosition = lastPosition;
    }

    public String generateLine() {
        String difference = lastPosition == -1 ? "new" : (newPosition.equals(lastPosition) ? "-" : String.valueOf(lastPosition - newPosition));
       return String.format("%d\t%s\t\t\t| %s\n", newPosition, name, difference);
    }
}
