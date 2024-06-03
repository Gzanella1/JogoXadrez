package piecesType;

import boardGame.Board;
import boardGame.Piece;
import boardGame.Position;
import chess.ChessPieces;
import chess.Color;

import java.util.List;

public class Knight extends ChessPieces {
    public Knight(Board board, Color color) {
        super(board, color);
    }

    @Override
    public boolean[][] possibleMoves() {
        return new boolean[0][];
    }

}
