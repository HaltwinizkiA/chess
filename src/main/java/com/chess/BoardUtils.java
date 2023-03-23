package com.chess;

import com.chess.entity.Coordinates;
import com.chess.enums.Horizontal;

import java.util.ArrayList;
import java.util.List;

public class BoardUtils {
    public static List<Coordinates> getDiagonalCoordinates(Coordinates from, Coordinates target) {
        //precondition - coordinates lie on the same diagonal
        List<Coordinates> result = new ArrayList<>();
        int horizontalShift = from.horizontal.ordinal() < target.horizontal.ordinal() ? 1 : -1;
        int verticalShift = from.vertical < target.vertical ? 1 : -1;
        for (
                int horizontalIndex=from.horizontal.ordinal()+horizontalShift,vertical=from.vertical+verticalShift;

            horizontalIndex!=target.horizontal.ordinal()&&vertical!=target.vertical;
            horizontalIndex+=horizontalShift, vertical+=verticalShift
        ){
            result.add(new Coordinates(Horizontal.values()[horizontalIndex],vertical));
        }

        return result;
    }
}
