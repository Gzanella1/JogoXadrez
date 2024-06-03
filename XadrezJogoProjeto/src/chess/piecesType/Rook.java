package chess.piecesType;

import boardGame.Board;
import boardGame.Position;
import chess.ChessPieces;
import chess.Color;

public class Rook extends ChessPieces {
    public Rook(Board board, Color color) {
        super(board, color);
    }

    @Override
    public String toString() {
        // Letra responstavel por representar a TORRE no tabuleiro
        return " R ";
    }

    /**
     * Metodo sobreecrito para verificar os possiveis movimento da torre.
     *
     * @return boolean[][]
     */
    @Override
    public boolean[][] possibleMoves() {
        // Matriz está com todas as posições contendo falso
        boolean[][] mat = new boolean[getBoard().getRows()][getBoard().getColumns()];

        // Posição auxiliar, para ter um valor inicial, por onde vai começar no caso
        Position posicaoAux = new Position(0, 0);

        // verificar ACIMA da peça (linha)
        checkDirection(posicaoAux, mat, -1, 0);

        // verifica o lado da peça (esquerda)
        checkDirection(posicaoAux, mat, 0, -1);

        // verifica o lado da peça (direita)
        checkDirection(posicaoAux, mat, 0, 1);

        // verificar ABAIXO da peça (linha)
        checkDirection(posicaoAux, mat, 1, 0);

        return mat;
    }

    private void checkDirection(Position posicaoAux, boolean[][] mat, int linhaDirecao, int colunaDirecao) {
        // p.setValues(position.getRow() - 1, position.getColumn());
        posicaoAux.setValues(position.getRow() + linhaDirecao, position.getColumn() + colunaDirecao);

        while (getBoard().positionExists(posicaoAux) && !getBoard().thereIsAPiece(posicaoAux)) {
            mat[posicaoAux.getRow()][posicaoAux.getColumn()] = true;
            // p.setRow(p.getRow() - 1);
            posicaoAux.setValues(posicaoAux.getRow() + linhaDirecao, posicaoAux.getColumn() + colunaDirecao);
        }
        if (getBoard().positionExists(posicaoAux) && isThereOpponentPiece(posicaoAux)) {
            mat[posicaoAux.getRow()][posicaoAux.getColumn()] = true;
        }
    }

}
