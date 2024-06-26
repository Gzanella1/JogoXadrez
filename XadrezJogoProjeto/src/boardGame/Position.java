package boardGame;

public class Position {
    private int row;
    private int column;

    public Position(int row, int column) {
        this.row = row;
        this.column = column;
    }

    /**
     * Método responsavel por atualizar os valores de uma posição.
     *
     * @param row
     * @param column
     */
    public void setValues(int row, int column) {
        this.row = row;
        this.column = column;
    }

    @Override
    public String toString() {
        //imprime a posição
        return "row=" + row + ", column=" + column;
    }

    // ======================
    //  GETTER E SETTER
    // ======================

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }
}
