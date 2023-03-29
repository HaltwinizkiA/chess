package com.chess.board;

import com.chess.coordinate.Move;
import com.chess.enums.Horizontal;
import com.chess.piece.Coordinates;
import com.chess.piece.Piece;
import com.chess.piece.PieceFactory;

public class BoardFactory {
    private final PieceFactory pieceFactory = new PieceFactory();

    public Board fromFen(String fen) {
        // standart FEN - rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1;
        //random FEN - rnbqkbnr/p2p1ppp/8/p2p2pN/2R1P1P1/1P2Q3/P2PPP1P/R1B1KBN1 w Qkq - 0 1
        Board board = new Board(fen);
        String[] parts = fen.split(" ");
        String piecePosition = parts[0];
        String[] rows = piecePosition.split("/");
        for (int i = 0; i < rows.length; i++) {
            int vertical = 8 - i;
            String row = rows[i];
            int verticalIndex = 0;
            for (int j = 0; j < row.length(); j++) {
                char fenChar = row.charAt(j);
                if (Character.isLetter(fenChar)) {
                    Horizontal horizontal = Horizontal.values()[verticalIndex];
                    Coordinates coordinates = new Coordinates(horizontal, vertical);
                    board.setPieces(pieceFactory.fromFenChar(fenChar, coordinates), coordinates);
                    verticalIndex++;

                } else {
                    verticalIndex += Character.getNumericValue(fenChar);
                }

            }
        }
        return board;
    }

    public String FENGeneration(Board board) {
        StringBuilder FENBuilder = new StringBuilder();
        for (int i = 8; i > 0; i--) {
            int empty = 0;
            for (int j = 0; j < 8; j++) {
                Piece piece = board.getPieces().get(new Coordinates(Horizontal.values()[j], i));
                if (piece == null) {
                    empty++;
                } else {
                    if (empty != 0) {
                        FENBuilder.append(empty);
                        empty = 0;
                    }
                    FENBuilder.append(piece.getFENSymbol());

                }
            }
            if (empty != 0) {
                FENBuilder.append(empty);
            }
            FENBuilder.append("/");
        }
        return FENBuilder.toString();
    }

    public Board copy(Board board) {
        String fen=FENGeneration(board);
        Board copy = fromFen(fen);
        return copy;
    }
}
