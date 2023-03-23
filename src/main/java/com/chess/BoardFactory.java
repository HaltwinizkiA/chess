package com.chess;

import com.chess.board.Board;
import com.chess.entity.Coordinates;
import com.chess.enums.Color;
import com.chess.enums.Horizontal;
import com.chess.piece.Pawn;

public class BoardFactory {
    private PieceFactory pieceFactory=new PieceFactory();
    public Board fromFen(String fen) {
        // standart FEN - rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1;
        //random FEN - rnbqkbnr/p2p1ppp/8/p2p2pN/2R1P1P1/1P2Q3/P2PPP1P/R1B1KBN1 w Qkq - 0 1
        Board board=new Board();
        String[] parts = fen.split(" ");
        String piecePosition=parts[0];
        String[] rows=piecePosition.split("/");
        for(int i=0;i<rows.length;i++){
            int vertical=8-i;
            String row=rows[i];
            int verticalIndex=0;
            for (int j=0;j<row.length();j++){
                char fenChar=row.charAt(j);
                if (Character.isLetter(fenChar)){
                    Horizontal  horizontal=Horizontal.values()[verticalIndex];
                    Coordinates coordinates=new Coordinates(horizontal,vertical);
                    board.setPieces(pieceFactory.fromFenChar(fenChar,coordinates), coordinates);
                    verticalIndex++;

                }else {
                    verticalIndex+=Character.getNumericValue(fenChar);
                }

            }
        }
        return board;
    }

}
