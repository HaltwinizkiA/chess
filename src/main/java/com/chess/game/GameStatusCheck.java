package com.chess.game;

import com.chess.board.Board;
import com.chess.enums.Color;
import com.chess.enums.GameStatus;

public abstract class GameStatusCheck {
    public abstract GameStatus check(Board board, Color color);
}
