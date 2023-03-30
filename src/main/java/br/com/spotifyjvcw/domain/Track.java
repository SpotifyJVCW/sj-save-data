package br.com.spotifyjvcw.domain;

import br.com.spotifyjvcw.domain.contract.Position;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Track implements Position {

    private String id;
    private String name;
    private Integer lastPosition;
    private Integer newPosition;
    private Artist artist;

    public void setPositions(int newPosition, int lastPosition) {
        this.newPosition = newPosition;
        this.lastPosition = lastPosition;
    }

    public String generateLine() {
        String difference = lastPosition == -1 ? "new" : (newPosition.equals(lastPosition) ? "-" : String.valueOf(lastPosition - newPosition));
        return String.format("%d;%s;%s\n", newPosition + 1, name + "-" + artist.getName(), difference);
    }
}
