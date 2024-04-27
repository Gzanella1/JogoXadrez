package chess.piecesType;

import boardGame.Board;
import chess.ChessPieces;
import chess.Color;

public class king extends ChessPieces {
    public king(Board board, Color color ) {
        super(board, color);
    }

    @Override
    public String toString() {
        return " ♟️ ";
    }


}
