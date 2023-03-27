package com.chess.board;

import com.chess.enums.Color;
import com.chess.enums.Horizontal;
import com.chess.piece.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class Board {
    HashMap<Coordinates, Piece> pieces = new HashMap<Coordinates, Piece>();

    public static boolean isSquareDark(Coordinates coordinates) {
        return (((coordinates.horizontal.ordinal() + 1) + coordinates.vertical) % 2) == 0;
    }

    public void setPieces(Piece piece, Coordinates coordinates) {
        piece.coordinates = coordinates;
        pieces.put(coordinates, piece);
    }

    public void removePiece(Coordinates coordinates) {
        pieces.remove(coordinates);

    }

    public void movePiece(Coordinates from, Coordinates to) {
        Piece piece = getPiece(from);
        removePiece(from);
        setPieces(piece, to);
    }

    public void setupDefaultPeacesPositions() {
        //set pawn
        for (Horizontal horizontal : Horizontal.values()) {
            setPieces(new Pawn(Color.WHITE, new Coordinates(horizontal, 2)), new Coordinates(horizontal, 2));
            setPieces(new Pawn(Color.BlACK, new Coordinates(horizontal, 7)), new Coordinates(horizontal, 7));
        }
        //set rook
        setPieces(new Rook(Color.WHITE, new Coordinates(Horizontal.A, 1)), new Coordinates(Horizontal.A, 1));
        setPieces(new Rook(Color.WHITE, new Coordinates(Horizontal.H, 1)), new Coordinates(Horizontal.H, 1));
        setPieces(new Rook(Color.BlACK, new Coordinates(Horizontal.A, 8)), new Coordinates(Horizontal.A, 8));
        setPieces(new Rook(Color.BlACK, new Coordinates(Horizontal.H, 8)), new Coordinates(Horizontal.H, 8));
        //set Knight
        setPieces(new Knight(Color.WHITE, new Coordinates(Horizontal.B, 1)), new Coordinates(Horizontal.B, 1));
        setPieces(new Knight(Color.WHITE, new Coordinates(Horizontal.G, 1)), new Coordinates(Horizontal.G, 1));
        setPieces(new Knight(Color.BlACK, new Coordinates(Horizontal.B, 8)), new Coordinates(Horizontal.B, 8));
        setPieces(new Knight(Color.BlACK, new Coordinates(Horizontal.G, 8)), new Coordinates(Horizontal.G, 8));
        //set Bishop
        setPieces(new Bishop(Color.WHITE, new Coordinates(Horizontal.C, 1)), new Coordinates(Horizontal.C, 1));
        setPieces(new Bishop(Color.WHITE, new Coordinates(Horizontal.F, 1)), new Coordinates(Horizontal.F, 1));
        setPieces(new Bishop(Color.BlACK, new Coordinates(Horizontal.C, 8)), new Coordinates(Horizontal.C, 8));
        setPieces(new Bishop(Color.BlACK, new Coordinates(Horizontal.F, 8)), new Coordinates(Horizontal.F, 8));
        //set queens
        setPieces(new Queen(Color.WHITE, new Coordinates(Horizontal.D, 1)), new Coordinates(Horizontal.D, 1));
        setPieces(new Queen(Color.BlACK, new Coordinates(Horizontal.D, 8)), new Coordinates(Horizontal.D, 8));
        //set Kings
        setPieces(new King(Color.WHITE, new Coordinates(Horizontal.E, 1)), new Coordinates(Horizontal.E, 1));
        setPieces(new King(Color.BlACK, new Coordinates(Horizontal.E, 8)), new Coordinates(Horizontal.E, 8));


    }

    public boolean isSquareEmpty(Coordinates coordinates) {
        return !pieces.containsKey(coordinates);
    }

    public Piece getPiece(Coordinates coordinates) {
        return pieces.get(coordinates);
    }

    public boolean isSquareInBattleByColor(Coordinates coordinates, Color color) {
        List<Piece> piecesByColor = getPiecesByColor(color);

        for (Piece piece : piecesByColor) {
            Set<Coordinates> squareInBattle = piece.getSquaresInBattle(this);
            if (squareInBattle.contains(coordinates)) {
                return true;
            }
        }
        return false;


    }

    private List<Piece> getPiecesByColor(Color color) {
        List<Piece> result = new ArrayList<>();
        for (Piece piece : pieces.values()) {
            if (piece.color == color) {
                result.add(piece);
            }
        }
        return result;
    }
}
