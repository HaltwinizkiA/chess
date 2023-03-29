package com.chess.piece;

import com.chess.enums.Color;

import java.util.Scanner;

public class PieceFactory {
    public Piece fromFenChar(char fenChar, Coordinates coordinates) {
        switch (fenChar) {

            case 'p':
                return new Pawn(Color.BlACK, coordinates, "p");
            case 'P':
                return new Pawn(Color.WHITE, coordinates, "P");

            case 'r':
                return new Rook(Color.BlACK, coordinates, "r");
            case 'R':
                return new Rook(Color.WHITE, coordinates, "R");

            case 'n':
                return new Knight(Color.BlACK, coordinates, "n");
            case 'N':
                return new Knight(Color.WHITE, coordinates, "N");

            case 'b':
                return new Bishop(Color.BlACK, coordinates, "b");
            case 'B':
                return new Bishop(Color.WHITE, coordinates, "B");

            case 'q':
                return new Queen(Color.BlACK, coordinates, "q");
            case 'Q':
                return new Queen(Color.WHITE, coordinates, "Q");

            case 'k':
                return new King(Color.BlACK, coordinates, "k");
            case 'K':
                return new King(Color.WHITE, coordinates, "K");
            default:
                throw new RuntimeException("Unknown FEN char");
        }
    }

    public Piece pawnMetamorphose(Color color, Coordinates coordinates) {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Pawn in Metamorphose" + "\nvalid characters according to FEN" + "\n p,q,b,n,r, ");


        while (true) {
            String input = scanner.nextLine();
            if (input.equals("K") || input.equals("k")) {
                System.out.println("can only One King exist");
                continue;
            }
            if (color == Color.WHITE) {
                input = input.toUpperCase();
            } else input = input.toLowerCase();

            try {
                return fromFenChar(input.charAt(0), coordinates);
            } catch (RuntimeException e) {
                System.out.println("Wrong input");
            }
        }

    }


}

