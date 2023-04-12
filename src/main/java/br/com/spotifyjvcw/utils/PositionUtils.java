package br.com.spotifyjvcw.utils;

import br.com.spotifyjvcw.domain.contract.Position;

import java.util.List;

import static java.lang.Integer.parseInt;

public class PositionUtils {

    public static final String STYLE_CENTER_TEXT = "style='text-align: center;'";

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

        objectPositionList.forEach(position -> stringBuilder.append(position.generateLineHtml()));

        stringBuilder.append("</table>");
        return stringBuilder.toString();
    }

    public static String getColor(String positionInformation) {
        String color = "";

        if ("-".equals(positionInformation))
            color = "f2f2f2";
        else if ("new".equals(positionInformation))
            color = "blue";
        else if (parseInt(positionInformation) > 0)
            color = "green";
        else if (parseInt(positionInformation) < 0)
            color = "red";

        return String.format("style='background-color: %s'", color);
    }
}
