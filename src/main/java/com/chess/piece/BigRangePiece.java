package com.chess.piece;

import com.chess.board.BoardUtils;
import com.chess.board.Board;
import com.chess.enums.Color;

import java.util.List;

public abstract class BigRangePiece extends Piece {
    public BigRangePiece(Color color, Coordinates coordinates) {
        super(color, coordinates);
    }

    @Override
    protected boolean checkPieceOnTheWay(Coordinates coordinates, Board board) {
        //check if there is a piece on the way
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
        return super.checkPieceOnTheWay(coordinates, board);
    }

    @Override
    protected boolean isSquareAvailableForMove(Coordinates coordinates, Board board) {
        boolean result = super.isSquareAvailableForMove(coordinates, board);
        if (result) {
            //check pieces on the way
            return checkPieceOnTheWay(coordinates, board);
        } else {
            return false;
        }
    }

}
