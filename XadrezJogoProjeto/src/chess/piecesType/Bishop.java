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

    /**
     * Verifica a direção especificada no tabuleiro, marcando as posições válidas.
     *
     * @param posicaoAux    Objeto Position auxiliar que será usado para verificar posições.
     * @param mat           Matriz de booleanos que indica as posições válidas.
     * @param linhaDirecao  Direção da linha a ser verificada (pode ser -1, 0, ou 1).
     * @param colunaDirecao Direção da coluna a ser verificada (pode ser -1, 0, ou 1).
     */
    private void checkDirection(Position posicaoAux, boolean[][] mat, int linhaDirecao, int colunaDirecao) {
        // Ajusta posicaoAux para a nova posição baseada na direção dada
        posicaoAux.setValues(position.getRow() + linhaDirecao, position.getColumn() + colunaDirecao);

        // Continua verificando enquanto a posição existir no tabuleiro e não houver uma peça na posição
        while (getBoard().positionExists(posicaoAux) && !getBoard().thereIsAPiece(posicaoAux)) {
            // Marca a posição como válida na matriz
            mat[posicaoAux.getRow()][posicaoAux.getColumn()] = true;
            // Ajusta posicaoAux para a próxima posição na direção dada
            posicaoAux.setValues(posicaoAux.getRow() + linhaDirecao, posicaoAux.getColumn() + colunaDirecao);
        }

        // Se a posição existe no tabuleiro e há uma peça do oponente, marca como válida
        if (getBoard().positionExists(posicaoAux) && isThereOpponentPiece(posicaoAux)) {
            mat[posicaoAux.getRow()][posicaoAux.getColumn()] = true;
        }
    }


}
