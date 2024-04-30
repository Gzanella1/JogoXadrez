package boardGame;

public abstract class Piece {

    protected Position position;
    private Board board;

    public Piece(Board board) {
        this.board = board;
        position = null;
    }


    /**
     * Metodo abstrato utilizado para pegar o movimento possivel de todas as peças.
     * @return
     */
    public abstract boolean[][] possibleMoves();

    public Piece[][] possibleMove() {
        // TODO
        return null;
    }

    /**
     * Verifica o movimento possivel para a posição passada.
     * Esse metodo esta fazendo um "gancho" com a sua subcalsse atravez do meotdo abstrato.
     *
     * @param position .
     * @return boolean
     */
    public boolean possibleMove(Position position) {
        return possibleMoves()[position.getRow()][position.getColumn()];
    }

    /**
     * Verifica se a peça consegue fazer pelo menos um movimento, ajuda nos casos onde a peça está travada, ou seja, sem movimento.
     *
     * @return boolean
     */
    public boolean isThereAnyPossibleMove() {
        boolean[][] mat = possibleMoves();
        for (int i=0; i<mat.length; i++) {
            for (int j=0; j<mat.length; j++) {
                if (mat[i][j]) {
                    return true;
                }
            }
        }
        return false;
    }

    // ==================
    //   Getter e setter
    // ==================

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    // somente classes dentro do mesmo pacote
    // e subclasses podem acessar o tabuleiro de uma peça
    protected Board getBoard() {
        return board;
    }
}
