package chess.piecesType;

import boardGame.Board;
import chess.ChessPieces;
import chess.Color;

public class King extends ChessPieces {
    public King(Board board, Color color ) {
        super(board, color);
    }

    @Override
    public String toString() {
        return " K ";
    }

    @Override
    public boolean[][] possibleMoves() {
        boolean[][] matrizAuxiliar = new boolean[getBoard().getRows()][getBoard().getColumns()];
        return matrizAuxiliar;
    }
}
