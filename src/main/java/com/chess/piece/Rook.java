package com.chess.piece;

import com.chess.coordinate.CoordinatesShift;
import com.chess.enums.Color;

import java.util.HashSet;
import java.util.Set;

public class Rook extends BigRangePiece {
    public Rook(Color color, Coordinates coordinates) {
        super(color, coordinates);
    }

    public Rook(Color color, Coordinates coordinates, String FENSymbol) {
        super(color, coordinates, FENSymbol);
    }

    @Override
    protected Set<CoordinatesShift> getPieceMoves() {
        HashSet<CoordinatesShift> result = new HashSet<>();
        for (int i = -7; i <= 7; i++) {
            if (i == 0) continue;
            //left to right
            result.add(new CoordinatesShift(i, 0));
            //down to up
            result.add(new CoordinatesShift(0, i));
        }

        return result;
    }
}
