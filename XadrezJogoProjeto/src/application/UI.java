package application;

import chess.ChessPieces;
import chess.ChessPosition;
import chess.Color;

import java.awt.*;
import java.util.InputMismatchException;
import java.util.Scanner;

public class UI {

    // https://stackoverflow.com/questions/5762491/how-to-print-color-in-console-using-system-out-println

    // Cores dos texto
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";

    // Cores do fundo
    public static final String ANSI_BLACK_BACKGROUND = "\u001B[40m";
    public static final String ANSI_RED_BACKGROUND = "\u001B[41m";
    public static final String ANSI_GREEN_BACKGROUND = "\u001B[42m";
    public static final String ANSI_YELLOW_BACKGROUND = "\u001B[43m";
    public static final String ANSI_BLUE_BACKGROUND = "\u001B[44m";
    public static final String ANSI_PURPLE_BACKGROUND = "\u001B[45m";
    public static final String ANSI_CYAN_BACKGROUND = "\u001B[46m";
    public static final String ANSI_WHITE_BACKGROUND = "\u001B[47m";

    /**
     * Imprime o tabuleiro de xadrez com as peças fornecidas.
     * Cada peça é representada por um objeto ChessPieces.
     *
     * @param pieces Um array bidimensional de objetos ChessPieces representando o estado do tabuleiro.
     */
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

    /**
     * Responsavel por imprimir uma peça.
     *
     * @param piece
     */
    private static void printPiece(ChessPieces piece) {
        if (piece == null) {
            System.out.print(" - ");
        } else {
            if (piece.getColor() == Color.BRANCO) {
                System.out.print(ANSI_CYAN + piece + ANSI_RESET);
            } else {
                System.out.print(ANSI_YELLOW + piece + ANSI_RESET);
            }
        }
        System.out.print("");
    }

    /**
     * Metodo responsavel por ler uma posição do tipo @{@link ChessPosition}
     *
     * @param in
     * @return
     */
    public static ChessPosition readChessPosition(Scanner in) {
        // vamos ler a posição do tipo do xadrez exemplo: A1
        // ler a posição e separar a Linha que seria o 1 da coluna que seria "A"
        try {
            String s = in.nextLine();
            char column = s.charAt(0);
            int row = Integer.parseInt(s.substring(1));
            return new ChessPosition(column, row);
        } catch (RuntimeException e) {
            throw new InputMismatchException("Erro ao ler a ChessPosition. Valores validos de A1 até H8");
        }
    }

    // https://stackoverflow.com/questions/2979383/java-clear-the-console
    public static void clearScreen() {
        try {
            final String os = System.getProperty("os.name");
            if (os.contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                Runtime.getRuntime().exec("clear");
            }
        } catch (final Exception e) {
            System.out.println("Erro ao limpar o console: " + e.getMessage());
        }
    }
}


































/*
*
* import java.io.PrintStream;
import java.io.UnsupportedEncodingException;

public class ChessSymbols {
public static void main (String [ ] args)throws
UnsupportedEncodingException {
        String unicodeMessage =
                        "\u2654 " + // white king
                        "\u2655 " + // white queen
                        "\u2656 " + // white rook
                        "\u2657 " + // white bishop
                        "\u2658 " + // white knight
                        "\u2659 " + // white pawn
                        "\n" +
                        "\u265A " + // black king
                        "\u265B " + // black queen
                        "\u265C " + // black rook
                        "\u265D " + // black bishop
                        "\u265E " + // black knight
                        "\u265F " + // black pawn
                        "\n" ;
        PrintStream out = new PrintStream (System.out, true , "UTF8" );
        out.println(unicodeMessage);
}
}
* */