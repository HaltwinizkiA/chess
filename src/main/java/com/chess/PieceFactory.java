package com.chess;

import com.chess.entity.Coordinates;
import com.chess.enums.Color;
import com.chess.piece.*;

public class PieceFactory {
    Piece fromFenChar(char fenChar, Coordinates coordinates) {
        switch (fenChar) {

            case 'p':
                return new Pawn(Color.BlACK, coordinates);
            case 'P':
                return new Pawn(Color.WHITE, coordinates);

            case 'r':
                return new Rook(Color.BlACK, coordinates);
            case 'R':
                return new Rook(Color.WHITE, coordinates);

            case 'n':
                return new Knight(Color.BlACK, coordinates);
            case 'N':
                return new Knight(Color.WHITE, coordinates);

            case 'b':
                return new Bishop(Color.BlACK, coordinates);
            case 'B':
                return new Bishop(Color.WHITE, coordinates);

            case 'q':
                return new Queen(Color.BlACK, coordinates);
            case 'Q':
                return new Queen(Color.WHITE, coordinates);

            case 'k':
                return new King(Color.BlACK, coordinates);
            case 'K':
                return new King(Color.WHITE, coordinates);
            default:
                throw new RuntimeException("Unknown FEN char");
            }
        }
    }

