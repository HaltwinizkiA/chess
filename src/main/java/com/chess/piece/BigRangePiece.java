package com.chess.piece;

import com.chess.BoardUtils;
import com.chess.board.Board;
import com.chess.enums.Color;

import java.util.List;

public abstract class BigRangePiece extends Piece {
    public BigRangePiece(Color color, Coordinates coordinates) {
        super(color, coordinates);
    }

    @Override
    protected boolean isSquareAvailableForMove(Coordinates coordinates, Board board) {
        boolean result = super.isSquareAvailableForMove(coordinates, board);
        if (result) {
            //check pieces
            List<Coordinates> checkCoordinate;
            if (this.coordinates.horizontal == coordinates.horizontal) {
                checkCoordinate = BoardUtils.getVerticalPermittedCoordinates(this.coordinates, coordinates);

            } else if (this.coordinates.vertical.equals(coordinates.vertical)) {
                checkCoordinate = BoardUtils.getHorizontalPermittedCoordinates(this.coordinates, coordinates);
            } else {
                checkCoordinate = BoardUtils.getDiagonalPermittedCoordinates(this.coordinates, coordinates);
            }
            for (Coordinates c : checkCoordinate) {
                if (!board.isSquareEmpty(c)) {
                    return false;
                }
            }
            return true;
        } else {
            return false;
        }
    }
}
