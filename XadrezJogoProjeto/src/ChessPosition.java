public class ChessPosition {

    private char column;
    private int row;

    // outros Metodos
    protected Position toPosition(){
        // TODO
        return null;
    }
    protected ChessPosition fromPosition(Position posicao){
        // TODO
        return null;
    }

    // Getter e Setter
    public char getColumn() {
        return column;
    }

    public void setColumn(char column) {
        this.column = column;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }
}
