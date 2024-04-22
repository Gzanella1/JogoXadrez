package application;

import chess.ChessPieces;

public class UI {

    public static void printBoard(ChessPieces[][] pieces) {
        // Imprime o tabuleiro no formato
        // Printa a linha
        for (int i = 0; i < pieces.length; i++) {
            System.out.print((8 - i) + " | ");
            //Print da coluna
            for (int j = 0; j < pieces[i].length; j++) {
                printPiece(pieces[i][j]);
            }
            System.out.println();
        }
        System.out.println("     a  b  c  d  e  f  g  h");
    }

    public static void printPiece(ChessPieces piece) {
        // imprime uma peça
        if (piece == null) {
            System.out.print(" - ");
        } else {
            System.out.print(piece);
        }
        System.out.print("");
    }
}
