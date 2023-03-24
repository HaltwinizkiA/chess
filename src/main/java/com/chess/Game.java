package com.chess;

import com.chess.board.Board;
import com.chess.board.BoardConsoleRenderer;
import com.chess.entity.InputCoordinates;
import com.chess.enums.Color;
import com.chess.piece.Coordinates;
import com.chess.piece.Piece;

import java.util.Set;

public class Game {
    private final Board board;
    private final BoardConsoleRenderer renderer = new BoardConsoleRenderer();
    private final InputCoordinates inputCoordinates = new InputCoordinates();

    public Game(Board board) {
        this.board = board;
    }


    public void gameLoop() {
        boolean isWhiteToMove = true;
        while (true) {

            if (isWhiteToMove){
                System.out.println("White to move");
            }else {System.out.println("Black to move");}
            //render
            renderer.render(board);
            //input
            Coordinates sourceCoordinates=inputCoordinates.inputPieceCoordinatesForColor(isWhiteToMove ? Color.WHITE:Color.BlACK,board);
            Piece piece=board.getPiece(sourceCoordinates);
            Set<Coordinates> availableMove=piece.getGetAvailableMove(board);
            renderer.render(board,piece);

            Coordinates target=inputCoordinates.inputAvailableSquare(availableMove);

            //make move
            board.movePiece(sourceCoordinates,target);
            //pass move
            isWhiteToMove = !isWhiteToMove;
        }
    }
}
