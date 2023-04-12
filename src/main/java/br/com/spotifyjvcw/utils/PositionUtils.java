package br.com.spotifyjvcw.utils;

import br.com.spotifyjvcw.domain.contract.Position;

import java.util.List;

public class PositionUtils {

    public static String generateArchive(List<Position> objectPositionList) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("#;Nome;Ranking\n");
        objectPositionList.forEach(position -> stringBuilder.append(position.generateLine()));

        return stringBuilder.toString();
    }

    public static String generateHtml(List<Position> objectPositionList) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("<table>\n");
        stringBuilder.append("<tr><th>#</th><th>Nome</th><th>Ranking</th></tr>\n");

        String prefix = "style='background-color: %s'";
        objectPositionList.forEach(position -> stringBuilder.append(position.generateLineHtml(String.format(prefix,
                position.isPositionChanged() > 0 ? "green" : position.isPositionChanged() < 0 ? "red" : "#f2f2f2"))));

        stringBuilder.append("</table>");
        return stringBuilder.toString();
    }
}
