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
        return " \u2654 ";
    }
}
