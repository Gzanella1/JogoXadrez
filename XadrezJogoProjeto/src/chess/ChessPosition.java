package chess;

import boardGame.Position;

public class ChessPosition {

    private char column;
    private int row;


    public ChessPosition(char column, int row) {
        if (column < 'a' || column > 'h' || row < 1 || row > 8){
            throw new ChessException("Erro ao instanciar o ChessPosition. Os valores validos são a1 até a8. ");
        }
        this.column = column;
        this.row = row;
    }

    /**
     * Converte a @{@link ChessPosition} para a @{@link Position}
     * como a possition é um array normal, precisamos converter ela para posições do xadrez
     * Exemplo: na ChessPosition A1 = 00 na classe Position
     * @return
     */
    protected Position toPosition(){
        // coluna A correspende a Coluna zero
        // pensando niso pegar o codigo unicode de 'a' - 'a'= 0
        return new Position(8-row,column -'a');
    }

    /**
     * Converte de @{@link Position } para @{@link ChessPosition}
     * Faz o papel inverso do @toPosition
     * @param posicao
     * @return
     */
    protected static ChessPosition fromPosition(Position posicao){
        return new ChessPosition((char) ('a'- posicao.getColumn()), 8-posicao.getRow());
    }

    @Override
    public String toString() {
        return "Posição: "+ column + row ;
    }

    // ======================
    //  GETTER E SETTER
    // ======================

    public char getColumn() {
        return column;
    }

    public int getRow() {
        return row;
    }

}
