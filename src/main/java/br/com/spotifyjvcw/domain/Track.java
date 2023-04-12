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
        return String.format("%d;%s;%s\n", newPosition + 1, name + "-" + artist.getName(), getDifference());
    }

    @Override
    public String generateLineHtml(String color) {
        return String.format("<tr %s><td>%s</td><td>%s</td><td>%s</td></tr>\n", color, newPosition, name, getDifference());
    }

    private String getDifference() {
        return lastPosition == -1 ? "new" : (newPosition.equals(lastPosition) ? "-" : String.valueOf(lastPosition - newPosition));
    }

    @Override
    public int isPositionChanged() {
        return newPosition - lastPosition;
    }
}
