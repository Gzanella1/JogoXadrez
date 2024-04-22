package chess;

import boardGame.Piece;
import boardGame.Position;

public abstract class ChessPieces extends Piece{
    private Color color;
    private int moveCount;

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

    // Getter e setter
    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public int getMoveCount() {
        return moveCount;
    }

    public void setMoveCount(int moveCount) {
        this.moveCount = moveCount;
    }
}
