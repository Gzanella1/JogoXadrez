package chess.piecesType;

import boardGame.Board;
import boardGame.Position;
import chess.ChessMatch;
import chess.ChessPieces;
import chess.Color;

public class Pawn extends ChessPieces {

    private ChessMatch chessMatch;

    public Pawn(Board board, Color color, ChessMatch chessMatch) {
        super(board, color);
        this.chessMatch = chessMatch;
    }

    @Override
    public String toString() {
        return " P ";
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

            // movimento especial en passant branco
            enPassant(mat, 3, -1);
        } else {
            checkMove(posicaoAux, mat, 1, 0);
            checkDoubleMove(mat, posicaoAux, 2, 0, 1, 0);
            checkOponentMove(posicaoAux, mat, 1, -1);
            checkOponentMove(posicaoAux, mat, 1, 1);

            // movimento especial en passant branco
            enPassant(mat, 4, 1);
        }
        return mat;
    }

    /**
     * Verifica e marca possíveis movimentos de en passant na matriz de posições.
     *
     * @param matrizAux Matriz de booleanos que indica as posições válidas.
     * @param linha     Linha específica do tabuleiro que precisa ser verificada.
     * @param auxLinha  Ajuste de linha necessário para a captura en passant.
     */
    public void enPassant(boolean[][] matrizAux, int linha, int auxLinha) {
        // Verifica se a peça está na linha especificada
        if (position.getRow() == linha) {
            // Cria uma posição à esquerda da peça atual
            Position left = new Position(position.getRow(), position.getColumn() - 1);

            // Verifica se a posição à esquerda existe, se há uma peça do oponente nela,
            // e se essa peça é vulnerável ao en passant
            if (getBoard().positionExists(left) && isThereOpponentPiece(left) && getBoard().piece(left) == chessMatch.getEnPassantVulnerable()) {
                // Marca a posição ajustada como válida para o en passant
                matrizAux[left.getRow() + auxLinha][left.getColumn()] = true;
            }

            // Cria uma posição à direita da peça atual
            Position right = new Position(position.getRow(), position.getColumn() + 1);

            // Verifica se a posição à direita existe, se há uma peça do oponente nela,
            // e se essa peça é vulnerável ao en passant
            if (getBoard().positionExists(right) && isThereOpponentPiece(right) && getBoard().piece(right) == chessMatch.getEnPassantVulnerable()) {
                // Marca a posição ajustada como válida para o en passant
                matrizAux[right.getRow() + auxLinha][right.getColumn()] = true;
            }
        }
    }

    /**
     * Verifica se um movimento simples (para frente) é possível.
     *
     * @param matrizAux
     * @param posicaoAux
     * @param linha
     * @param coluna
     */
    private void checkMove(Position posicaoAux, boolean[][] matrizAux, int linha, int coluna) {
        posicaoAux.setValues(position.getRow() + linha, position.getColumn() + coluna);
        if (getBoard().positionExists(posicaoAux) && !getBoard().thereIsAPiece(posicaoAux)) {
            matrizAux[posicaoAux.getRow()][posicaoAux.getColumn()] = true;
        }
    }

    /**
     * Verifica se um movimento duplo (para frente na primeira jogada da peça) é possível.
     *
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

        if (getBoard().positionExists(posicaoAux) && !getBoard().thereIsAPiece(posicaoAux) && getBoard().positionExists(p2) && !getBoard().thereIsAPiece(p2) && getMoveCount() == 0) {
            matrizAux[posicaoAux.getRow()][posicaoAux.getColumn()] = true;
        }
        return false;
    }

    /**
     * Verifica se uma captura (diagonal) é possível.
     * <p>
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