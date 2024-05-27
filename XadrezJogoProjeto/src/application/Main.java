package application;

import chess.ChessException;
import chess.ChessMatch;
import chess.ChessPieces;
import chess.ChessPosition;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        ChessMatch chessMatch=new ChessMatch();
        List<ChessPieces> captured =new ArrayList<>();


        while (true){
            try {
                UI.clearScreen();
                System.out.flush();

                UI.printMatch(chessMatch, captured);
                System.out.println();
                System.out.print("Source: ");
                ChessPosition source = UI.readChessPosition(in);
                System.out.println();

                // possiveis movimentos
                boolean[][] possibleMoves = chessMatch.possibleMoves(source);
                UI.clearScreen();
                System.out.flush();

                UI.printBoard(chessMatch.getPieces(), possibleMoves);
                System.out.println();
                System.out.print("Target: ");
                ChessPosition target = UI.readChessPosition(in);

                ChessPieces capturedPiece = chessMatch.performChessMove(source, target);

                if(capturedPiece != null){
                    captured.add(capturedPiece);
                }

            }
            catch (ChessException | InputMismatchException e){
                System.out.println(e.getMessage());
                in.nextLine();
            }
        }


    }
}