package chess.piecesType;

import boardGame.Board;
import boardGame.Position;
import chess.ChessPieces;
import chess.Color;

public class Bishop extends ChessPieces {

    public Bishop(Board board, Color color) {
        super(board, color);
    }

    @Override
    public String toString() {
        return " B ";
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
        Position posicaoAux = new Position(0, 0);

        checkDirection(posicaoAux, mat, -1, -1);

        checkDirection(posicaoAux, mat, -1, +1);

        checkDirection(posicaoAux, mat, +1, -1);

        checkDirection(posicaoAux, mat, +1, +1);

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
