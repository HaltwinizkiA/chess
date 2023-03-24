package com.chess.piece;

import com.chess.entity.CoordinatesShift;
import com.chess.enums.Color;

import java.util.HashSet;
import java.util.Set;

public class Pawn extends Piece {
    public Pawn(Color color, Coordinates coordinates) {
        super(color, coordinates);
    }

    @Override
    protected Set<CoordinatesShift> getPieceMoves() {
        HashSet<CoordinatesShift> result = new HashSet<>();

        if (this.color == Color.WHITE) {
            result.add(new CoordinatesShift(0, 1));
            if (this.coordinates.vertical == 2) {
                result.add(new CoordinatesShift(0, 2));
            }
        } else {
            result.add(new CoordinatesShift(0, -1));
            if (this.coordinates.vertical == 7) {
                result.add(new CoordinatesShift(0, -2));
            }
        }


        return result;
    }
}
