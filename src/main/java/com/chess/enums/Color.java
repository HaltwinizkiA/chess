package com.chess.enums;

public enum Color {
    WHITE,
    BlACK;

    public Color change() {

        return this == WHITE ? BlACK : WHITE;
    }
}
