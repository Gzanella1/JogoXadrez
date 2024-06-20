package boardGame;

public class Board {
    private int rows;
    private int columns;
    private Piece[][] pieces;


    public Board(int rows, int columns) {
        // Verifica se o número de linhas e colunas é válido (deve ser pelo menos 1)
        if (rows < 1 || columns < 1) {
            throw new BoardException("Erro ao criar o tabuleiro: é necessário no mínimo uma linha e uma coluna.");
        }
        // Inicializa as variáveis de instância
        this.rows = rows;
        this.columns = columns;
        // Cria uma matriz de peças com o tamanho especificado
        pieces = new Piece[rows][columns];
    }

    /**
     * Retorna uma peça de uma determinada linha e coluna passada por parâmetro.
     *
     * @param row Linha da peça desejada
     * @param column Coluna da peça desejada
     * @return A peça na posição especificada
     * @throws BoardException se a posição não existir no tabuleiro
     */
    public Piece piece(int row, int column) {
        // Verifica se a posição especificada existe no tabuleiro
        if (!positionExists(row, column)) {
            throw new BoardException("Essa posição não existe no tabuleiro.");
        }
        // Retorna a peça na posição especificada
        return pieces[row][column];
    }

    /**
     * Retorna a peça na posição passada por parâmetro.
     *
     * @param position A posição da peça desejada
     * @return A peça na posição especificada
     * @throws BoardException se a posição não existir no tabuleiro
     */
    public Piece piece(Position position) {
        // Verifica se a posição especificada existe no tabuleiro
        if (!positionExists(position)) {
            throw new BoardException("Essa posição não existe no tabuleiro.");
        }
        // Retorna a peça na posição especificada
        return pieces[position.getRow()][position.getColumn()];
    }

    /**
     * Coloca uma peça na posição especificada no tabuleiro.
     *
     * @param piece A peça a ser colocada no tabuleiro
     * @param position A posição onde a peça será colocada
     * @throws BoardException se já houver uma peça na posição especificada
     */
    public void placePiece(Piece piece, Position position) {
        // Verifica se já existe uma peça na posição especificada
        if (thereIsAPiece(position)) {
            throw new BoardException("Já existe uma peça nessa posição " +
                    "[" + position.getRow() + ", " + position.getColumn() + "] do tabuleiro.");
        }
        // Coloca a peça na posição especificada no tabuleiro
        pieces[position.getRow()][position.getColumn()] = piece;
        // Atualiza a posição da peça
        piece.position = position;
    }

    /**
     * Remove uma peça da posição especificada no tabuleiro.
     *
     * @param position A posição de onde a peça será removida
     * @return A peça removida, ou null se não houver peça na posição
     * @throws BoardException se a posição não existir no tabuleiro
     */
    public Piece removePiece(Position position) {
        // Verifica se a posição especificada existe no tabuleiro
        if (!positionExists(position)) {
            throw new BoardException("Essa posição não existe no tabuleiro.");
        }
        // Verifica se há uma peça na posição especificada
        if (piece(position) == null) {
            return null;
        }
        // Remove a peça da posição especificada
        Piece auxiliar = piece(position);
        auxiliar.position = null;
        pieces[position.getRow()][position.getColumn()] = null;
        return auxiliar;
    }

    /**
     * Verifica se a posição especificada existe no tabuleiro.
     *
     * @param position A posição a ser verificada
     * @return true se a posição existir, false caso contrário
     */
    public boolean positionExists(Position position) {
        // Verifica se a posição existe usando os valores de linha e coluna
        return positionExists(position.getRow(), position.getColumn());
    }

    /**
     * Verifica se a posição especificada por linha e coluna existe no tabuleiro.
     *
     * @param row A linha da posição
     * @param column A coluna da posição
     * @return true se a posição existir, false caso contrário
     */
    private boolean positionExists(int row, int column) {
        // Verifica se a linha e a coluna estão dentro dos limites do tabuleiro
        return row >= 0 && row < rows && column >= 0 && column < columns;
    }

    /**
     * Verifica se há uma peça na posição especificada.
     *
     * @param position A posição a ser verificada
     * @return true se houver uma peça na posição, false caso contrário
     * @throws BoardException se a posição não existir no tabuleiro
     */
    public boolean thereIsAPiece(Position position) {
        // Verifica se a posição especificada existe no tabuleiro
        if (!positionExists(position)) {
            throw new BoardException("Essa posição não existe no tabuleiro.");
        }
        // Retorna true se houver uma peça na posição, false caso contrário
        return piece(position) != null;
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