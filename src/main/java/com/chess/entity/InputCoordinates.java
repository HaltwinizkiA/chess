package com.chess.entity;

import com.chess.board.Board;
import com.chess.enums.Color;
import com.chess.enums.Horizontal;
import com.chess.piece.Coordinates;
import com.chess.piece.Piece;

import java.util.Scanner;
import java.util.Set;

public class InputCoordinates {
    Scanner scanner = new Scanner(System.in);

    public Coordinates input() {
        while (true) {
            System.out.println("Please enter coordinates example( a1 )");
            String line = scanner.nextLine();

            if (line.length() != 2) {
                System.out.println("Invalid format");
                continue;
            }
            char horizontalChar = line.charAt(0);
            char verticalChar = line.charAt(1);
            if (!Character.isLetter(horizontalChar) && !Character.isDigit(verticalChar)) {
                System.out.println("Invalid format");
                continue;
            }
            Horizontal horizontal = Horizontal.fromChar(horizontalChar);
            int v = Character.getNumericValue(verticalChar);
            if (horizontal == null && v < 0 || v > 8) {
                System.out.println("Invalid format");
                continue;
            }
            return new Coordinates(horizontal, v);

        }
    }

    public Coordinates inputAvailableSquare(Set<Coordinates> coordinates) {
        while (true) {
            System.out.println(" enter Place");
            Coordinates input = input();
            if (!coordinates.contains(input)) {
                System.out.println("Wrong place to move");
            }
            return input;
        }


    }

    public Coordinates inputPieceCoordinatesForColor(Color color, Board board) {
        while (true) {
            System.out.println("Entry coordinates for a piece to move");
            Coordinates coordinates = input();
            if (board.isSquareEmpty(coordinates)) {
                System.out.println("Invalid format");
                continue;

            }
            Piece piece = board.getPiece(coordinates);
            if (piece.color != color) {
                System.out.println("Wrong piece(color)");
                continue;
            }
            Set<Coordinates> coordinatesSet = piece.getGetAvailableMove(board);
            if (coordinatesSet.size() == 0) {
                System.out.println("This Piece can't move-because it block");
            }
            return coordinates;

        }
    }

}
