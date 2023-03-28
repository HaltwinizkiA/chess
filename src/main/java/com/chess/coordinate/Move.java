package com.chess.coordinate;

import com.chess.piece.Coordinates;

public class Move {
    public final Coordinates from;
    public final Coordinates to;

    public Move(Coordinates from, Coordinates to) {
        this.from = from;
        this.to = to;
    }
}
