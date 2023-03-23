package com.chess.piece;

import com.chess.enums.Color;
import com.chess.entity.Coordinates;
import com.chess.entity.CoordinatesShift;

import java.util.Set;

public class Rook extends Piece{
    public Rook(Color color, Coordinates coordinates) {
        super(color, coordinates);
    }

    @Override
    protected Set<CoordinatesShift> getPieceMoves() {
        return null;
    }
}
