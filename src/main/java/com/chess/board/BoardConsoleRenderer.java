package com.chess.board;

import com.chess.enums.Color;
import com.chess.piece.Coordinates;
import com.chess.enums.Horizontal;
import com.chess.piece.Piece;

import java.util.Set;

import static java.util.Collections.emptySet;

public class BoardConsoleRenderer {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_WHITE_PIECE_COLOR = "\u001B[97m";
    public static final String ANSI_BLACK_PIECE_COLOR = "\u001B[30m";
    public static final String ANSI_WHITE_SQUARE_BACKGROUND = "\u001B[47m";
    public static final String ANSI_BLACK_SQUARE_BACKGROUND = "\u001B[0;100m";
    public static final String ANSI_HIGHLIGHTED_SQUARE_BACKGROUND = "\u001B[106m";

    public void render(Board board,Piece pieceToMove) {
        Set<Coordinates> availablePieceMoves=emptySet();
        if (pieceToMove!=null){
            availablePieceMoves=pieceToMove.getGetAvailableMove(board);
        }
        for (int vertical = 8; vertical >= 1; vertical--) {
            String line = "";

            for (Horizontal horizontal : Horizontal.values()) {
                Coordinates coordinates = new Coordinates(horizontal, vertical);
                boolean isHighlight=availablePieceMoves.contains(coordinates);

                if (board.isSquareEmpty(coordinates)) {
                    line += getSpringForEmptySquare(coordinates,isHighlight);
                } else {
                    line += getPiecesSprite(board.getPiece(coordinates),isHighlight);}

            }
            line += ANSI_RESET;
            System.out.println(line);
        }
    }
    public void render(Board board){
        render(board,null);
    }

    private String getPiecesSprite(Piece piece,boolean isHighlight) {
        return colorizeSprite(" " + selectUnicodeSpriteForPiece(piece) + " ", piece.color, Board.isSquareDark(piece.coordinates),isHighlight);
    }

    private String selectUnicodeSpriteForPiece(Piece piece) {
        switch (piece.getClass().getSimpleName()) {
            case "Pawn":
                return "♙";
            case "Knight":
                return "♘";
            case "Bishop":
                return "♗";
            case "Queen":
                return "♕";
            case "Rook":
                return "♖";
            case "King":
                return "♔";
        }
        return "";
    }

    private String colorizeSprite(String sprite, Color pieceColor, boolean isSquareDark,boolean isHghLighted) {
        //format =background color+ font color + text  //watch-(ansi colors)
        String result = sprite;
        if (isHghLighted){
            result = ANSI_HIGHLIGHTED_SQUARE_BACKGROUND + result;
        }
        if (pieceColor == Color.WHITE) {
            result = ANSI_WHITE_PIECE_COLOR + result;
        } else {
            result = ANSI_BLACK_PIECE_COLOR + result;
        }
        if (isSquareDark) {
            result = ANSI_BLACK_SQUARE_BACKGROUND + result;
        } else {
            result = ANSI_WHITE_SQUARE_BACKGROUND + result;
        }
        return result;
    }

    private String getSpringForEmptySquare(Coordinates coordinates,boolean isHighlight) {
        return colorizeSprite("   ", Color.BlACK, Board.isSquareDark(coordinates),isHighlight);
    }

}
