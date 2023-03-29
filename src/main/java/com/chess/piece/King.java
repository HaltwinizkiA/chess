package com.chess.piece;

import com.chess.board.Board;
import com.chess.coordinate.CoordinatesShift;
import com.chess.enums.Color;

import java.util.HashSet;
import java.util.Set;

public class King extends Piece {
    public King(Color color, Coordinates coordinates) {
        super(color, coordinates);
    }

    public King(Color color, Coordinates coordinates, String FENSymbol) {
        super(color, coordinates, FENSymbol);
    }

    @Override
    protected Set<CoordinatesShift> getPieceMoves() {
        HashSet<CoordinatesShift> result = new HashSet<>();

        for (int horizontalShift = -1; horizontalShift <= 1; horizontalShift++) {
            for (int verticalShift = -1; verticalShift <= 1; verticalShift++) {
                if (horizontalShift == 0 && verticalShift == 0) {
                    continue;
                }
                result.add(new CoordinatesShift(horizontalShift, verticalShift));

            }


        }
        return result;

    }

    @Override
    protected boolean isSquareAvailableForMove(Coordinates coordinates, Board board) {
        boolean result = super.isSquareAvailableForMove(coordinates, board);
        if (result) {
            return !board.isSquareInBattleByColor(coordinates, color.change());

        }

        return false;
    }
}


