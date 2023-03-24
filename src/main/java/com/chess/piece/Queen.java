package com.chess.piece;

import com.chess.entity.CoordinatesShift;
import com.chess.enums.Color;

import java.util.HashSet;
import java.util.Set;

public class Queen extends BigRangePiece {
    public Queen(Color color, Coordinates coordinates) {
        super(color, coordinates);
    }

    @Override
    protected Set<CoordinatesShift> getPieceMoves() {
        HashSet<CoordinatesShift> result = new HashSet<>();
        for (int i = -7; i <= 7; i++) {
            if (i == 0) continue;
            //bottom left -to top right
            result.add(new CoordinatesShift(i, i));
            //top left to bottom right
            result.add(new CoordinatesShift(i, -i));
            //left to right
            result.add(new CoordinatesShift(i, 0));
            //right to left
            result.add(new CoordinatesShift(0, i));

        }
        return result;

    }
}
