package br.com.spotifyjvcw.domain;

import br.com.spotifyjvcw.domain.contract.Position;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import static br.com.spotifyjvcw.utils.PositionUtils.STYLE_CENTER_TEXT;
import static br.com.spotifyjvcw.utils.PositionUtils.getColor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Artist implements Position {

    private String id;
    private String name;
    private Integer lastPosition;
    private Integer newPosition;

    public void setPositions(int newPosition, int lastPosition) {
        this.newPosition = newPosition;
        this.lastPosition = lastPosition;
    }

    public String generateLine() {
       return String.format("%d;%s;%s\n", newPosition + 1, name, getDifference());
    }

    @Override
    public String generateLineHtml() {
        String difference = getDifference();
        return String.format("<tr %s><td %s>%s</td><td>%s</td><td %s>%s</td></tr>\n", getColor(difference), STYLE_CENTER_TEXT, newPosition + 1, name, STYLE_CENTER_TEXT, difference);
    }

    private String getDifference() {
        return lastPosition == -1 ? "new" : (newPosition.equals(lastPosition) ? "-" : String.valueOf(lastPosition - newPosition));
    }

    @Override
    public int isPositionChanged() {
        return newPosition - lastPosition;
    }
}
