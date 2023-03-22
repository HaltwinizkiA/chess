package com.chess.entity;

import com.chess.entity.piece.CoordinatesShift;

public class Coordinates {
    public final Horizontal horizontal;
    public final Integer vertical;

    public Coordinates(Horizontal horizontal, Integer vertical) {
        this.horizontal = horizontal;
        this.vertical = vertical;
    }

    public Coordinates shift(CoordinatesShift coordinatesShift) {
        return new Coordinates(Horizontal.values()[this.horizontal.ordinal() + coordinatesShift.horizontalShift], this.vertical + coordinatesShift.verticalShift);
    }

    public boolean canShift(CoordinatesShift coordinatesShift) {
        int h = horizontal.ordinal() + coordinatesShift.horizontalShift;
        int v = vertical + coordinatesShift.verticalShift;
        if ((v < 0) || (v > 8)) return false;
        return (h >= 0) && (h <= 8);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Coordinates)) return false;

        Coordinates that = (Coordinates) o;

        if (horizontal != that.horizontal) return false;
        return vertical.equals(that.vertical);
    }

    @Override
    public int hashCode() {
        int result = horizontal.hashCode();
        result = 31 * result + vertical.hashCode();
        return result;
    }
}
