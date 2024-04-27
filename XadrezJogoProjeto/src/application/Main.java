package application;

import chess.ChessMatch;

public class Main {
    public static void main(String[] args) {
        ChessMatch chessMatch=new ChessMatch();
        UI.printBoard(chessMatch.getPieces());
        System.out.println();
    }
}