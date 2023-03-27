package com.chess.piece;

import com.chess.board.Board;
import com.chess.entity.CoordinatesShift;
import com.chess.enums.Color;

import java.util.HashSet;
import java.util.Set;

public class Pawn extends Piece {
    public Pawn(Color color, Coordinates coordinates) {
        super(color, coordinates);
    }
//todo Realisierung metamorphose in alle piece
    @Override
    protected Set<CoordinatesShift> getPieceMoves() {
        HashSet<CoordinatesShift> result = new HashSet<>();

        if (this.color == Color.WHITE) {
            result.add(new CoordinatesShift(0, 1));
            result.add(new CoordinatesShift(1, 1));
            result.add(new CoordinatesShift(-1, 1));

            if (this.coordinates.vertical == 2) {
                result.add(new CoordinatesShift(0, 2));
            }

        } else {
            result.add(new CoordinatesShift(0, -1));
            result.add(new CoordinatesShift(1, -1));
            result.add(new CoordinatesShift(-1, -1));
            if (this.coordinates.vertical == 7) {
                result.add(new CoordinatesShift(0, -2));
            }
        }


        return result;
    }

    @Override
    protected boolean isSquareAvailableForMove(Coordinates coordinates, Board board) {
        if (this.coordinates.horizontal == coordinates.horizontal) {
            //if lie on 2 or 7 vertical and have a piece in front of
            if (this.coordinates.vertical == 2 || this.coordinates.vertical == 7) {
                if (board.getPiece(new Coordinates(this.coordinates.horizontal, this.coordinates.vertical + 1)) != null || board.getPiece(new Coordinates(this.coordinates.horizontal, this.coordinates.vertical - 1)) != null) {
                    return false;
                }
            }

            return board.isSquareEmpty(coordinates);
        } else {
            if (board.isSquareEmpty(coordinates)) {
                return false;
            } else {
                return board.getPiece(coordinates).color != this.color;
            }

        }

    }
}
