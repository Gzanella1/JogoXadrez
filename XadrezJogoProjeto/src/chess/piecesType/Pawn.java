package chess.piecesType;

import boardGame.Board;
import boardGame.Position;
import chess.ChessPieces;
import chess.Color;

public class Pawn extends ChessPieces {


    @Override
    public String toString() {
        return " P ";
    }

    public Pawn(Board board, Color color) {
        super(board, color);
    }

    @Override
    public boolean[][] possibleMoves() {
        boolean[][] mat = new boolean[getBoard().getRows()][getBoard().getColumns()];
        Position posicaoAux = new Position(0, 0);

        if (getColor() == Color.BRANCO) {
            checkMove(posicaoAux, mat, -1, 0);

            checkDoubleMove(mat, posicaoAux, -2, 0, -1, 0);

            checkOponentMove(posicaoAux, mat, -1, -1);
            checkOponentMove(posicaoAux, mat, -1, +1);
        } else {
            checkMove(posicaoAux, mat, 1, 0);
            checkDoubleMove(mat, posicaoAux, 2, 0, 1, 0);
            checkOponentMove(posicaoAux, mat, 1, -1);
            checkOponentMove(posicaoAux, mat, 1, 1);
        }

        return mat;
    }

    /**
     *  Verifica se um movimento simples (para frente) é possível.
     * @param matrizAux
     * @param posicaoAux
     * @param linha
     * @param coluna
     */
    private void checkMove(Position posicaoAux,boolean[][] matrizAux, int linha, int coluna) {
        posicaoAux.setValues(position.getRow() + linha, position.getColumn() + coluna);
        if (getBoard().positionExists(posicaoAux) && !getBoard().thereIsAPiece(posicaoAux)) {
            matrizAux[posicaoAux.getRow()][posicaoAux.getColumn()] = true;
        }
    }

    /**
     *  Verifica se um movimento duplo (para frente no primeiro turno) é possível.
     * @param matrizAux
     * @param posicaoAux
     * @param linha
     * @param coluna
     * @param auxlinha
     * @param auxcoluna
     * @return
     */
    private boolean checkDoubleMove(boolean[][] matrizAux, Position posicaoAux, int linha, int coluna, int auxlinha, int auxcoluna) {
        Position p2 = new Position(position.getRow() + auxlinha, position.getColumn() + auxcoluna);
        posicaoAux.setValues(position.getRow() + linha, position.getColumn() + coluna);

        if (getBoard().positionExists(posicaoAux) && !getBoard().thereIsAPiece(posicaoAux) &&
                getBoard().positionExists(p2) && !getBoard().thereIsAPiece(p2) &&
                getMoveCount() == 0) {
            matrizAux[posicaoAux.getRow()][posicaoAux.getColumn()] = true;
        }
        return false;
    }

    /**
     * Verifica se uma captura (diagonal) é possível.
     *
     * Verifica se há oponente na posição esquerda ou direita, se sim pode mover para
     * posição onde tem oponente.
     *
     * @param posicaoAux
     * @param mat
     * @param linhaDirecao
     * @param colunaDirecao
     */
    public boolean checkOponentMove(Position posicaoAux, boolean[][] mat, int linhaDirecao, int colunaDirecao) {
        posicaoAux.setValues(getPosition().getRow() + linhaDirecao, getPosition().getColumn() + colunaDirecao);
        if (getBoard().positionExists(posicaoAux) && isThereOpponentPiece(posicaoAux)) {
            return mat[posicaoAux.getRow()][posicaoAux.getColumn()] = true;
        }
        return false;
    }
}