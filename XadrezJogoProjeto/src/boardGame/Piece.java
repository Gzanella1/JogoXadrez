package boardGame;

public class Piece {

    protected Position position;
    private Board board;

    public Piece(Board board) {
        this.board = board;
        position=null;
    }

    public Piece[][] possibleMoves(){
        // TODO
        return null;
    }
    public boolean possibleMove(Position position){
        // TODO
        return false;
    }
    public boolean isThereAnyPossibleMove(){
        // TODO
        return false;
    }

    // GETTER E SETTER

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    // somente classes dentro do mesmo pacote
    // e sub classes podem acessar o tabuleiro de uma peça
    protected Board getBoard() {
        return board;
    }

}



