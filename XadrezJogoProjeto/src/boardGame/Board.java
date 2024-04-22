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

    public Piece piece(int row, int columns) {
        // vai retornar a peça dada a uma linha/coluna
        return pieces[row][columns];
    }

    public Piece piece(Position position) {
        // vai retornar a peça pela posição
        return pieces[position.getRow()][position.getColumn()];
    }

    public void placePiece(Piece piece, Position position) {
        // TODO
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
