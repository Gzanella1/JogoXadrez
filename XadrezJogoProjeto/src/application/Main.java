package application;

import chess.ChessException;
import chess.ChessMatch;
import chess.ChessPieces;
import chess.ChessPosition;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        ChessMatch chessMatch=new ChessMatch();

        while (true){
            try {

                UI.printBoard(chessMatch.getPieces());
                System.out.println();
                System.out.print("Source: ");
                ChessPosition source = UI.readChessPosition(in);

                // possiveis movimentos
                boolean[][] possibleMoves = chessMatch.possibleMoves(source);
                //UI.clearScreen();
                UI.printBoard(chessMatch.getPieces(), possibleMoves);

                System.out.println();
                System.out.print("Target: ");
                ChessPosition target = UI.readChessPosition(in);

                ChessPieces capturedPiece = chessMatch.performChessMove(source, target);

            }
            catch (ChessException | InputMismatchException e){
                System.out.println(e.getMessage());
                in.nextLine();
            }
        }


    }
}