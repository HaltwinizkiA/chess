package com.chess.piece;

import com.chess.board.Board;
import com.chess.coordinate.CoordinatesShift;
import com.chess.enums.Color;

import java.util.HashSet;
import java.util.Set;

public abstract class Piece {
    public final Color color;
    public Coordinates coordinates;
    Set<Coordinates> AvailableMove;
    private String FENSymbol;

    public String getFENSymbol() {
        return FENSymbol;
    }

    public Piece(Color color, Coordinates coordinates) {
        this.color = color;
        this.coordinates = coordinates;
        if (this.color==Color.WHITE){
            this.FENSymbol=this.FENSymbol.toUpperCase();
        }else this.FENSymbol=this.FENSymbol.toLowerCase();
    }
    public Piece(Color color, Coordinates coordinates,String FENSymbol) {
        this.color = color;
        this.coordinates = coordinates;
        this.FENSymbol=FENSymbol;
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

    protected boolean isSquareAvailableForMove(Coordinates coordinates, Board board) {

        return board.isSquareEmpty(coordinates) || board.getPiece(coordinates).color != color;

    }

    public Set<Coordinates> getSquaresInBattle(Board board) {
        Set<CoordinatesShift> piecesToBattle = getPieceBattle();
        Set<Coordinates> squareInBattle = new HashSet<>();
        for (CoordinatesShift pieceToBattle : piecesToBattle) {
            if (coordinates.canShift(pieceToBattle)) {
                Coordinates shiftedCoordinates = coordinates.shift(pieceToBattle);

            if (checkPieceOnTheWay(shiftedCoordinates, board)) {
                squareInBattle.add(shiftedCoordinates);
            }
            }

        }
        return squareInBattle;
    }

    protected boolean checkPieceOnTheWay(Coordinates coordinates, Board board) {
        return true;

    }

    protected Set<CoordinatesShift> getPieceBattle() {
        return getPieceMoves();
    }
}
