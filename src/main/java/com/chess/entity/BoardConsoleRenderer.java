package com.chess.entity;

import com.chess.entity.piece.Piece;

import java.util.Set;

import static java.util.Collections.emptySet;

public class BoardConsoleRenderer {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_WHITE_PIECE_COLOR = "\u001B[97m";
    public static final String ANSI_BLACK_PIECE_COLOR = "\u001B[30m";
    public static final String ANSI_WHITE_SQUARE_BACKGROUND = "\u001B[47m";
    public static final String ANSI_BLACK_SQUARE_BACKGROUND = "\u001B[0;100m";


    public void render(Board board) {
        for (int vertical = 8; vertical >= 1; vertical--) {
            String line = "";

            for (Horizontal horizontal : Horizontal.values()) {
                Coordinates coordinates = new Coordinates(horizontal, vertical);
                if (board.isSquareEmpty(coordinates)) {
                    line += getSpringForEmptySquare(coordinates);
                } else {line += getPiecesSprite(board.getPiece(coordinates),true);}

            }
            line += ANSI_RESET;
            System.out.println(line);
        }
    }

    private String getPiecesSprite(Piece piece,boolean isHighlight) {
        return colorizeSprite(" " + selectUnicodeSpriteForPiece(piece) + " ", piece.color, Board.isSquareDark(piece.coordinates));
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

    private String colorizeSprite(String sprite, Color pieceColor, boolean isSquareDark) {
        //format =background color+ font color + text  //watch-(ansi colors)
        String result = sprite;
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

    private String getSpringForEmptySquare(Coordinates coordinates) {

        return colorizeSprite("   ", Color.BlACK, Board.isSquareDark(coordinates));
    }

}
