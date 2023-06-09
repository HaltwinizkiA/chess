package com.chess.piece;

import com.chess.board.Board;
import com.chess.coordinate.CoordinatesShift;
import com.chess.enums.Color;

import java.util.HashSet;
import java.util.Set;

public class Pawn extends Piece {
    //metamorphose in PieceFactory -pawnMetamorphose()
    boolean TakingOnThePass = false;

    public Pawn(Color color, Coordinates coordinates) {
        super(color, coordinates);
    }

    public Pawn(Color color, Coordinates coordinates, String fenSymbol) {
        super(color, coordinates, fenSymbol);
    }

    //todo diagonal walking(Taking on the pass)
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
    protected Set<CoordinatesShift> getPieceBattle() {
        HashSet<CoordinatesShift> result = new HashSet<>();
        if (color == Color.WHITE) {
            result.add(new CoordinatesShift(1, 1));
            result.add(new CoordinatesShift(-1, 1));
        } else {
            result.add(new CoordinatesShift(1, -1));
            result.add(new CoordinatesShift(-1, -1));
        }
        return result;
    }

    public boolean isTakingOnThePass() {
        return TakingOnThePass;
    }

    public void setTakingOnThePass(boolean takingOnThePass) {
        TakingOnThePass = takingOnThePass;
    }

    public boolean takeOnThePass(Board board, Coordinates to) {
        for (Piece piece : board.getPiecesByColor(this.color.change())) {
            if (piece.getClass() == Pawn.class) {
                Pawn enemyPawn = (Pawn) piece;
                if (enemyPawn.coordinates.vertical == this.coordinates.vertical && to.horizontal == enemyPawn.coordinates.horizontal && enemyPawn.TakingOnThePass) {
                    return board.isSquareEmpty(to);
                }
            }

        }
        return false;
    }

    @Override
    protected boolean isSquareAvailableForMove(Coordinates coordinates, Board board) {
        if (this.coordinates.horizontal == coordinates.horizontal) {
            //if lie on 2 or 7 vertical and have a piece in front of
            if (this.coordinates.vertical == 2 || this.coordinates.vertical == 7) {
                if (board.getPiece(new Coordinates(this.coordinates.horizontal, this.coordinates.vertical + 1)) != null && this.color == Color.WHITE || board.getPiece(new Coordinates(this.coordinates.horizontal, this.coordinates.vertical - 1)) != null && this.color == Color.BlACK) {
                    return false;
                }
            }
            return board.isSquareEmpty(coordinates);
        } else {
            //take on the pass
            if (takeOnThePass(board, coordinates)) {
                return true;
            }

            if (board.isSquareEmpty(coordinates)) {
                return false;
            } else {
                return board.getPiece(coordinates).color != this.color;
            }

        }
    }

}

