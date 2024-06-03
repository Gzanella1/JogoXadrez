package piecesType;

import boardGame.Board;
import boardGame.Piece;
import boardGame.Position;
import chess.ChessPieces;
import chess.Color;

import java.util.List;

public class Knight extends ChessPieces {
    public Knight(Board board, Color color) {
        super(board, color);
    }

    @Override
    public boolean[][] possibleMoves() {
        return new boolean[0][];
    }

}


//
//@Override
//public boolean[][] possibleMoves() {
//    boolean[][] matrizAux = new boolean[getBoard().getRows()][getBoard().getColumns()];
//    Position posicaoAux = new Position(0, 0);
//
//    if (getColor() == Color.BRANCO) {
//        // Mover para cima
//        checkMove(position.getRow() - 1, position.getColumn(), matrizAux);
//        // Mover duas casas acima
//        checkDoubleMove(position.getRow() - 2, position.getColumn(), matrizAux);
//        // Capturar à esquerda
//        checkCaptureMove(posicaoAux, matrizAux, -1, -1);
//        // Capturar à direita
//        checkCaptureMove(posicaoAux, matrizAux, -1, +1);
//    }else{
//        // Mover para baixo
//        checkMove(position.getRow() + 1, position.getColumn(), matrizAux);
//        // Mover duas casas abaixo
//        checkDoubleMove(position.getRow() + 2, position.getColumn(), matrizAux);
//        // Capturar à esquerda
//        checkCaptureMove(posicaoAux, matrizAux, +1, -1);
//        // Capturar à direita
//        checkCaptureMove(posicaoAux, matrizAux, +1, +1);
//
//    }
//    return matrizAux;
//}
//
//
//private void checkCaptureMove(Position posicaoAux, boolean[][] matrizAux,  int row, int column) {
//    posicaoAux.setValues(position.getRow() + row, position.getColumn() + column);
//
//    if (getBoard().positionExists(posicaoAux) && isThereOpponentPiece(posicaoAux)) {
//        matrizAux[row][column] = true;
//    }
//}
//
//
//private void checkMove(int row, int column, boolean[][] matrizAux) {
//    Position posicaoAux = new Position(row, column);
//    if (getBoard().positionExists(posicaoAux) && !getBoard().thereIsAPiece(posicaoAux)) {
//        matrizAux[row][column] = true;
//    }
//}
//
//private void checkDoubleMove(int row, int column, boolean[][] matrizAux) {
//    Position posicaoAux = new Position(row, column);
//    Position p2 = new Position(position.getRow() - 1, position.getColumn());
//    if (getBoard().positionExists(posicaoAux) && !getBoard().thereIsAPiece(posicaoAux) &&
//            getBoard().positionExists(p2) && !getBoard().thereIsAPiece(p2) && getMoveCount() == 0) {
//        matrizAux[row][column] = true;
//    }
//}
