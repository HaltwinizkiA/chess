package com.chess.enums;

public enum Horizontal {
    A, B, C, D, E, F, G, H;

    public static Horizontal fromChar(char c) {
        try {
            return Horizontal.valueOf(String.valueOf(c).toUpperCase());
        } catch (IllegalArgumentException e) {
            return null;
        }
    }
}
