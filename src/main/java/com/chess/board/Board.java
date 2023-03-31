package com.chess.board;

import com.chess.coordinate.Move;
import com.chess.enums.Color;
import com.chess.enums.Horizontal;
import com.chess.piece.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class Board {

    public final String startFen;
    private final List<Move> moveHistory = new ArrayList<>();
    HashMap<Coordinates, Piece> pieces = new HashMap<Coordinates, Piece>();

    public Board(String startFen) {
        this.startFen = startFen;
    }

    public static boolean isSquareDark(Coordinates coordinates) {
        return (((coordinates.horizontal.ordinal() + 1) + coordinates.vertical) % 2) == 0;
    }

    public HashMap<Coordinates, Piece> getPieces() {
        return pieces;
    }

    public void setPieces(Piece piece, Coordinates coordinates) {
        piece.coordinates = coordinates;
        pieces.put(coordinates, piece);
    }

    public void removePiece(Coordinates coordinates) {
        pieces.remove(coordinates);

    }

    public void movePiece(Move move, boolean withPawnMetamorphoseCheck) {
        //argument withPawnMetamorphoseCheck needed to turn off the metamorphosis logic
        //
        Piece piece = getPiece(move.from);
        //metamorphose for Pawn
        if (checkIsPawnInMetamorphose(piece, move.to) && withPawnMetamorphoseCheck) {
            piece = new PieceFactory().pawnMetamorphose(piece.color, move.to);
        }
        //taking on the pass
        if (piece.getClass() == Pawn.class) {
            Pawn pawn = (Pawn) piece;
            pawn.setTakingOnThePass(canBeAttackedThroughTakingOnThePass(pawn, move));
            takingOnThePass(pawn, move);
        }
        removeTakingOnThePass(piece.color.change());
        //remove a Piece from its previous position
        removePiece(move.from);
        setPieces(piece, move.to);
        moveHistory.add(move);

    }


    private boolean canBeAttackedThroughTakingOnThePass(Pawn piece, Move move) {
        //marks a pawn for taking on the pass
        if (piece.coordinates.vertical == 7 || piece.coordinates.vertical == 2) {
            int difference = piece.coordinates.vertical - move.to.vertical;
            return difference == 2 || difference == -2;
        }
        return false;

    }

    private void takingOnThePass(Pawn pawn, Move move) {
        if (pawn.takeOnThePass(this, move.to)) {
            if (pawn.color == Color.WHITE) {
                removePiece(new Coordinates(move.to.horizontal, move.to.vertical - 1));
            } else removePiece(new Coordinates(move.to.horizontal, move.to.vertical + 1));
        }
    }


    private void removeTakingOnThePass(Color color) {
        getPiecesByColor(color).stream().filter(piece -> piece instanceof Pawn).forEach(piece -> ((Pawn) piece).setTakingOnThePass(false));
    }


    public boolean checkIsPawnInMetamorphose(Piece piece, Coordinates to) {
        if (piece.getClass() == Pawn.class) {
            return piece.color == Color.BlACK && to.vertical == 1 || piece.color == Color.WHITE && to.vertical == 8;

        }
        return false;
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

    public List<Piece> getPiecesByColor(Color color) {
        List<Piece> result = new ArrayList<>();
        for (Piece piece : pieces.values()) {
            if (piece.color == color) {
                result.add(piece);
            }
        }
        return result;
    }

    public void setupDefaultPeacesPositions() {
        //set pawn without FEN using.
        //obsolete
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

}
