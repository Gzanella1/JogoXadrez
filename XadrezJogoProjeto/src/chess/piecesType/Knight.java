package chess.piecesType;

import boardGame.Board;
import boardGame.Position;
import chess.ChessPieces;
import chess.Color;

public class Knight extends ChessPieces {
    public Knight(Board board, Color color) {
        super(board, color);
    }

    @Override
    public String toString() {
        // Letra responstavel por representar a cavalo no tabuleiro
        return " C ";
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

        // Acima
        checkDirection(posicaoAux, mat, -2, 1);
        checkDirection(posicaoAux, mat, -2, -1);

        checkDirection(posicaoAux, mat, -1, -2);
        checkDirection(posicaoAux, mat, +1, -2);

        checkDirection(posicaoAux, mat, -1, +2);
        checkDirection(posicaoAux, mat, +1, +2);

        checkDirection(posicaoAux, mat, +2, 1);
        checkDirection(posicaoAux, mat, +2, -1);


        return mat;
    }

    private void checkDirection(Position posicaoAux, boolean[][] matrizAux, int linhaDirecao, int colunaDirecao) {
        posicaoAux.setValues(getPosition().getRow() + linhaDirecao, getPosition().getColumn() + colunaDirecao);

        if (getBoard().positionExists(posicaoAux) && (isThereOpponentPiece(posicaoAux) || !getBoard().thereIsAPiece(posicaoAux))) {
            matrizAux[posicaoAux.getRow()][posicaoAux.getColumn()] = true;
        }
    }


}


