package com.chess.piece;

import com.chess.BoardUtils;
import com.chess.board.Board;
import com.chess.entity.Coordinates;
import com.chess.entity.CoordinatesShift;
import com.chess.enums.Color;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Bishop extends Piece {
    public Bishop(Color color, Coordinates coordinates) {
        super(color, coordinates);
    }

    @Override
    protected boolean isSquareAvailableForMove(Coordinates coordinates, Board board) {
        boolean result=super.isSquareAvailableForMove(coordinates, board);
        if (result){
            //check pieces
            List<Coordinates> checkCoordinate=BoardUtils.getDiagonalPermittedCoordinates(this.coordinates,coordinates);
            for (Coordinates c:checkCoordinate){
                if (!board.isSquareEmpty(c)){
                    return false;
                }
            }
            return true;
        }else {
            return false;
        }


    }

    @Override
    protected Set<CoordinatesShift> getPieceMoves() {
        HashSet<CoordinatesShift> result = new HashSet<>();
        for (int i = -7; i <= 7; i++) {
            if (i == 0) continue;
            //bottom left -to top right
            result.add(new CoordinatesShift(i, i));
            //top left to bottom right
            result.add(new CoordinatesShift(i, -i));
        }

        return result;
    }
}
