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
        return " T ";
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

    /**
     * Verifica uma direção específica no tabuleiro para movimentos possíveis.
     *
     * @param posicaoAux    Posição auxiliar para verificar as direções.
     * @param mat           Matriz de movimentos possíveis.
     * @param linhaDirecao  Direção da linha a ser verificada.
     * @param colunaDirecao Direção da coluna a ser verificada.
     */
    private void checkDirection(Position posicaoAux, boolean[][] mat, int linhaDirecao, int colunaDirecao) {
        // Define a posição auxiliar com base na direção dada.
        posicaoAux.setValues(position.getRow() + linhaDirecao, position.getColumn() + colunaDirecao);

        // Continua verificando enquanto a posição existir no tabuleiro e não houver peça nela.
        while (getBoard().positionExists(posicaoAux) && !getBoard().thereIsAPiece(posicaoAux)) {
            // Marca a posição como um movimento possível.
            mat[posicaoAux.getRow()][posicaoAux.getColumn()] = true;
            // Atualiza a posição auxiliar para a próxima célula na direção dada.
            posicaoAux.setValues(posicaoAux.getRow() + linhaDirecao, posicaoAux.getColumn() + colunaDirecao);
        }

        // Se a posição existe no tabuleiro e há uma peça do oponente nela, marca como movimento possível.
        if (getBoard().positionExists(posicaoAux) && isThereOpponentPiece(posicaoAux)) {
            mat[posicaoAux.getRow()][posicaoAux.getColumn()] = true;
        }
    }


}
