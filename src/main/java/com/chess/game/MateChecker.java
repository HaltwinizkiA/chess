package com.chess.game;

import com.chess.board.Board;
import com.chess.board.BoardFactory;
import com.chess.coordinate.Move;
import com.chess.enums.Color;
import com.chess.enums.GameStatus;
import com.chess.piece.Coordinates;
import com.chess.piece.King;
import com.chess.piece.Piece;

import java.util.List;
import java.util.Set;

public class MateChecker extends GameStatusCheck {
    @Override
    public GameStatus check(Board board, Color color) {
        //check all possibilities to avoid Mate
        Piece king = board.getPiecesByColor(color).stream().filter(piece -> piece instanceof King).findFirst().get();
        if (!board.isSquareInBattleByColor(king.coordinates, color.change())) {
            return GameStatus.ONGOING;
        }
        //check King is in mate
        List<Piece> pieceList = board.getPiecesByColor(color);
        for (Piece piece : pieceList) {
            Set<Coordinates> availableMove = piece.getGetAvailableMove(board);
            for (Coordinates coordinates : availableMove) {
                Board clone = new BoardFactory().copy(board);
                clone.movePiece(new Move(piece.coordinates, coordinates),false);
                Piece kingClone = clone.getPiecesByColor(color).stream().filter(p -> p instanceof King).findFirst().get();

                if (!clone.isSquareInBattleByColor(kingClone.coordinates, color.change())) {
                    return GameStatus.ONGOING;
                }
            }
        }
        if (color == Color.WHITE) {
            return GameStatus.CHECKMATE_TO_WHITE_KING;
        } else return GameStatus.CHECKMATE_TO_BLACK_KING;
    }
}
