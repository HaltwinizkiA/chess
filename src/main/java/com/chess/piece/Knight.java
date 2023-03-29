package com.chess.piece;

import com.chess.coordinate.CoordinatesShift;
import com.chess.enums.Color;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Knight extends Piece {

    private final String FENSymbol = "k";

    public Knight(Color color, Coordinates coordinates) {
        super(color, coordinates);
    }

    public Knight(Color color, Coordinates coordinates, String FENSymbol) {
        super(color, coordinates, FENSymbol);
    }

    @Override
    protected Set<CoordinatesShift> getPieceMoves() {

        return new HashSet<>(Arrays.asList(new CoordinatesShift(-2, 1), new CoordinatesShift(-1, 2),


                new CoordinatesShift(2, -1), new CoordinatesShift(1, -2),

                new CoordinatesShift(-2, -1), new CoordinatesShift(-1, -2),

                new CoordinatesShift(1, 2), new CoordinatesShift(2, 1)


        ));


    }
}
