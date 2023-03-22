package com.chess.entity.piece;

import com.chess.entity.Board;
import com.chess.entity.Color;
import com.chess.entity.Coordinates;

import java.util.HashSet;
import java.util.Set;

public abstract class Piece {
    public final Color color;
    public Coordinates coordinates;
    Set<Coordinates> getAvailableMove;

    public Piece(Color color, Coordinates coordinates) {
        this.color = color;
        this.coordinates = coordinates;
    }

    protected abstract Set<CoordinatesShift> getPieceMoves();

    public Set<Coordinates> getGetAvailableMove(Board board) {
        Set<Coordinates> result = new HashSet<>();

        for (CoordinatesShift coordinatesShift : getPieceMoves()) {
            if (coordinates.canShift(coordinatesShift)) {
                Coordinates newCoordinates1 = coordinates.shift(coordinatesShift);
                if (isSquareAvailableForMove(newCoordinates1, board)) {
                    result.add(newCoordinates1);
                }
            }
        }

        return result;
    }

    private boolean isSquareAvailableForMove(Coordinates coordinates, Board board) {
        return board.isSquareEmpty(coordinates) || board.getPiece(coordinates).color != color;

    }
}
