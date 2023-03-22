package com.chess.entity.piece;

import com.chess.entity.Color;
import com.chess.entity.Coordinates;

import java.util.Set;

public class King extends Piece{
    public King(Color color, Coordinates coordinates) {
        super(color, coordinates);
    }

    @Override
    protected Set<CoordinatesShift> getPieceMoves() {
        return null;
    }
}
