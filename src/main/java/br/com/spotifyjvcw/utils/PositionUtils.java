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
}
