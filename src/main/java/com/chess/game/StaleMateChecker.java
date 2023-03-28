package com.chess.game;

import com.chess.board.Board;
import com.chess.enums.Color;
import com.chess.enums.GameStatus;
import com.chess.piece.Coordinates;
import com.chess.piece.Piece;

import java.util.List;
import java.util.Set;

public class StaleMateChecker extends GameStatusCheck {

    @Override
    public GameStatus check(Board board, Color color) {
        List<Piece> pieces = board.getPiecesByColor(color);
        for (Piece piece : pieces) {

            Set<Coordinates>  coordinatesSet = piece.getGetAvailableMove(board);
            if (coordinatesSet.size() > 0) {
                return GameStatus.ONGOING;
            }
        }
         return GameStatus.STALEMATE;

    }
}
