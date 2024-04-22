package chess;

import boardGame.Board;
import boardGame.Piece;
import boardGame.Position;

public  class ChessPieces extends Piece{
    private Color color;
    private int moveCount;

    public ChessPieces(Board board, Color color, int moveCount) {
        super(board);
        this.color = color;
        this.moveCount = moveCount;
    }

    public ChessPosition getChessPosition(){
        // TODO
        return null;
    }
    protected boolean isThereOpponnentPiece(Position position){
        // TODO existe uma peça do oponente oponente
        return false;
    }

    protected void increaseMove(){
        // TODO aumentar movimento
    }

    protected void decreaseMove(){
        // TODO diminuir Mover
    }
    // ==================
    //   Getter e setter
    // ==================
    public Color getColor() {
        return color;
    }

    public int getMoveCount() {
        return moveCount;
    }

    public void setMoveCount(int moveCount) {
        this.moveCount = moveCount;
    }
}
