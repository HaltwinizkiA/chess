package com.chess.game;

import com.chess.board.Board;
import com.chess.board.BoardConsoleRenderer;
import com.chess.coordinate.InputCoordinates;
import com.chess.coordinate.Move;
import com.chess.enums.Color;
import com.chess.enums.GameStatus;

import java.util.List;

public class Game {
    private final List<GameStatusCheck> gameStatusChecks = List.of(new StaleMateChecker(), new MateChecker());
    private final Board board;
    private final BoardConsoleRenderer renderer = new BoardConsoleRenderer();
    private final InputCoordinates inputCoordinates = new InputCoordinates();

    public Game(Board board) {
        this.board = board;
    }


    public void gameLoop() {
        Color toMove = Color.WHITE;
        GameStatus gameStatus = defineGameStatus(board, toMove);
        while (gameStatus == GameStatus.ONGOING) {
            //render
            renderer.render(board);
            if (toMove == Color.WHITE) {
                System.out.println("White to move");
            } else {
                System.out.println("Black to move");
            }

            //input
            Move move = inputCoordinates.InputMove(board, toMove, renderer);
            //CHECK SHAH (from, to)
            //sout("your king is under Attack)

            //make move check Pawn in Metamorphose

            board.movePiece(move, true);
            //pass move
            toMove = toMove.change();
            gameStatus = defineGameStatus(board, toMove);

        }
        renderer.render(board);
        System.out.println("Game finished with status -  " + gameStatus);
    }

    private GameStatus defineGameStatus(Board board, Color color) {
        for (GameStatusCheck gameStatusCheck : gameStatusChecks) {
            GameStatus gameState = gameStatusCheck.check(board, color);
            if (gameState != GameStatus.ONGOING) {
                return gameState;
            }
        }
        return GameStatus.ONGOING;
    }
}
