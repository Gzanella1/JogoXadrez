package chess.piecesType;

import boardGame.Board;
import boardGame.Position;
import chess.ChessPieces;
import chess.Color;

public class Rook extends ChessPieces {
    public Rook(Board board, Color color ) {
        super(board, color );
    }

    @Override
    public String toString() {
        // Letra responstavel por representar a TORRE no tabuleiro
        return " R ";
    }

    /**
     * Metodo sobreecrito para verificar os possiveis movimento da torre.
     * @return boolean[][]
     */
    @Override
    public boolean[][] possibleMoves() {

        boolean[][] mat = new boolean[getBoard().getRows()][getBoard().getColumns()];

        Position p = new Position(0, 0);

        // verificar ACIMA da peça (linha) ----------------
        p.setValues(position.getRow() - 1, position.getColumn());
        while (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {
            mat[p.getRow()][p.getColumn()] = true;
            p.setRow(p.getRow() - 1);
        }
        if (getBoard().positionExists(p) && isThereOpponentPiece(p)) {
            mat[p.getRow()][p.getColumn()] = true;
        }

        // verifica o lado da peça (esquerda) ----------------
        p.setValues(position.getRow(), position.getColumn() - 1);
        while (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {
            mat[p.getRow()][p.getColumn()] = true;
            p.setColumn(p.getColumn() - 1);
        }
        if (getBoard().positionExists(p) && isThereOpponentPiece(p)) {
            mat[p.getRow()][p.getColumn()] = true;
        }

        // verifica o lado da peça (direita) ----------------
        p.setValues(position.getRow(), position.getColumn() + 1);
        while (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {
            mat[p.getRow()][p.getColumn()] = true;
            p.setColumn(p.getColumn() + 1);
        }
        if (getBoard().positionExists(p) && isThereOpponentPiece(p)) {
            mat[p.getRow()][p.getColumn()] = true;
        }

        // verificar ABAIXO da peça (linha) ----------------
        p.setValues(position.getRow() + 1, position.getColumn());
        while (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {
            mat[p.getRow()][p.getColumn()] = true;
            p.setRow(p.getRow() + 1);
        }
        if (getBoard().positionExists(p) && isThereOpponentPiece(p)) {
            mat[p.getRow()][p.getColumn()] = true;
        }


        return mat;
    }
}



//    @Override
//    public boolean[][] possibleMoves() {
//        // Matriz está com todas as posições contendo falso
//        //
//        boolean[][] mat = new boolean[getBoard().getRows()][getBoard().getColumns()];
//        // Posição auxiliar, para ter um valor inicial
//        Position posicaoAux = new Position(0, 0);
//
//        // verificar ACIMA da peça (linha)
//        checkDirection(posicaoAux, mat, -1, 0);
//
//        // verifica o lado da peça (esquerda)
//        checkDirection(posicaoAux, mat, 0, -1);
//
//        // verifica o lado da peça (direita)
//        checkDirection(posicaoAux, mat, 0, 1);
//
//        // verificar ABAIXO da peça (linha)
//        checkDirection(posicaoAux, mat, 1, 0);
//
//        return mat;
//    }
//
//    private void checkDirection(Position posicaoAux, boolean[][] mat, int rowDirection, int columnDirection) {
//        // p.setValues(position.getRow() - 1, position.getColumn());
//        posicaoAux.setValues(position.getRow() + rowDirection, position.getColumn() + columnDirection);
//
//        while (getBoard().positionExists(posicaoAux) && !getBoard().thereIsAPiece(posicaoAux)) {
//            mat[posicaoAux.getRow()][posicaoAux.getColumn()] = true;
//            // p.setRow(p.getRow() - 1);
//            posicaoAux.setValues(posicaoAux.getRow() + rowDirection, posicaoAux.getColumn() + columnDirection);
//        }
//        if (getBoard().positionExists(posicaoAux) && isThereOpponentPiece(posicaoAux)) {
//            mat[posicaoAux.getRow()][posicaoAux.getColumn()] = true;
//        }
//    }
