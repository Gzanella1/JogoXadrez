package boardGame;

public class Board {
    private int rows;
    private int columns;
    private Piece[][] pieces;

    public Board(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
        pieces= new Piece[rows][columns];

    }

    /**
     * Retorna uma peça de uma determinada linha e coluna passada por parametro.
     * @param row
     * @param columns
     * @return vai retornar a peça dada a uma linha/coluna
     */
    public Piece piece(int row, int columns) {
        return pieces[row][columns];
    }

    /**
     *
     * @param position
     * @return a peça na determinada posição
     */
    public Piece piece(Position position) {
        return pieces[position.getRow()][position.getColumn()];
    }

    /**
     * Metodo responsavel por colocar a peça na possição do tabuleiro
     * @param piece
     * @param position
     */
    public void placePiece(Piece piece, Position position) {
        // Array de peças, na posição linha/coluna recebe a peça do parametro
        pieces[position.getRow()][position.getColumn()]= piece;
        // Essa peça não está mais na posição nula, agora ela vai esta na posição
        // que foi passada no parametro.
        piece.position=position;

    }

    public Piece removePiece(Position position) {
        // TODO
        return null;
    }

    public boolean positionExists(Position position) {
        // TODO
        return false;
    }

    public boolean thereIsAPiece(Position position) {
        // TODO
        return false;
    }


    // ==================
    //  GETTER E SETTERS
    // ==================

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public int getColumns() {
        return columns;
    }

    public void setColumns(int columns) {
        this.columns = columns;
    }
}
