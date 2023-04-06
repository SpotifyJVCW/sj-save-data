package br.com.spotifyjvcw.domain;

import br.com.spotifyjvcw.domain.contract.Position;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

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
        String difference = lastPosition == -1 ? "new" : (newPosition.equals(lastPosition) ? "-" : String.valueOf(lastPosition - newPosition));
       return String.format("%d;%s;%s\n", newPosition + 1, name, difference);
    }

    @Override
    public boolean isPositionChanged() {
        return !Objects.equals(lastPosition, newPosition);
    }
}
