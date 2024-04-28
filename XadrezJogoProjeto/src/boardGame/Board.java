package boardGame;

public class Board {
    private int rows;
    private int columns;
    private Piece[][] pieces;

    public Board(int rows, int columns) {
        if (rows < 1 || columns < 1) {
            throw new BoardException("Erro ao criar o tabuleiro: é necessario no mnimo uma linha e uma coluna");
        }
        this.rows = rows;
        this.columns = columns;
        pieces = new Piece[rows][columns];
    }

    /**
     * Retorna uma peça de uma determinada linha e coluna passada por parametro.
     * @param row
     * @param columns
     * @return vai retornar a peça dada a uma linha/coluna
     */
    public Piece piece(int row, int columns) {
        if (!positionExists(row, columns)) {
            throw new BoardException("Essa posição não existe no tabuleiro.");
        }
        return pieces[row][columns];
    }

    /**
     * Pega a peça na posição passada por parametro.
     *
     * @param position
     * @return Piece
     */
    public Piece piece(Position position) {
        if (!positionExists(position)) {
            throw new BoardException("Essa posição não existe no tabuleiro.");
        }
        return pieces[position.getRow()][position.getColumn()];
    }

    /**
     * Metodo responsavel por colocar a peça na possição do tabuleiro
     *
     * @param piece
     * @param position
     */
    public void placePiece(Piece piece, Position position) {
        if (thereIsAPiece(position)) {
            throw new BoardException("Já existe uma peça nessa posição " +
                    "[" + position.getRow() + ", " + position.getColumn() + "] do tabuleiro.");
        }
        // Array de peças, na posição linha/coluna recebe a peça do parametro
        pieces[position.getRow()][position.getColumn()] = piece;
        // Essa peça não está mais na posição nula, agora ela vai esta na posição
        // que foi passada no parametro.
        piece.position = position;

    }

    /**
     * Vai remover uma peça na posição
     * @param position
     * @return
     */
    public Piece removePiece(Position position) {
        if (!positionExists(position)) {
            throw new BoardException("Essa posição não existe no tabuleiro.");
        }
        if(piece(position) == null){
            return null;
        }
        Piece auxiliar= piece(position);
        auxiliar.position=null;
        pieces[position.getRow()][position.getColumn()]=null;
        return auxiliar;
    }

    /**
     * Metodo que recebe uma posição e retorna true se essa posição existe.
     * @param position
     * @return boolean
     */
    public boolean positionExists(Position position) {
        return positionExists(position.getRow(), position.getColumn());
    }

    /**
     * Metodo criado como uma segunda alternativa para verificar se existe uma posição.
     * Em determinado momento será mais facil fazer a verificação pela linha e pela coluna.
     * @param row
     * @param column
     * @return boolean
     */
    private boolean positionExists(int row, int column) {
        //condição completa para ver se existe uma posição
        if (row >= 0 && row <= rows && column >= 0 && column < columns) {
            return true;
        }
        return false;
    }

    /**
     * Verifica se existe uma peça, na posição passada no parametro.
     *
     * @param position
     * @return boolean
     */
    public boolean thereIsAPiece(Position position) {
        if (!positionExists(position)) {
            throw new BoardException("Essa posição não existe no tabuleiro.");
        } else {
            if (piece(position) != null) {
                return true;
            }
            return false;
        }
    }

    // =========================================================================================
    //  GETTERs - não há set, pois não posso mudar o número de linhas/coluna durante a execução
    // =========================================================================================

    public int getRows() {
        return rows;
    }

    public int getColumns() {
        return columns;
    }

}
