package chess;

import boardGame.Board;
import boardGame.Piece;
import boardGame.Position;
import chess.piecesType.Rook;
import chess.piecesType.King;


public class ChessMatch {

    private int turn;
    private Color currentPlayer;
    private boolean check;
    private boolean checkMate;
    private ChessPieces enPassantVulnearable;
    private ChessPieces promoted;

    private Board board;

    public ChessMatch() {
        this.board = new Board(8, 8);
        initialSetup();
    }


    public boolean[][] possibleMoves(ChessPosition sourcePosition) {
        // TODO
        return null;
    }


    public ChessPieces replacePromotedPiece(String type) {
        // TODO
        return null;
    }


    /**
     * Vai retornar uma matriz de peças de xadrez, corespondente a partida.
     * Vamos retornar um chessPeace, pois o programa so enchergará a chesspeace
     * ele não encherga a peace.
     *
     * @return ChessPieces[][]
     */
    public ChessPieces[][] getPieces() {
        ChessPieces[][] matriz = new ChessPieces[board.getRows()][board.getColumns()];
        // Percorer a matriz de peça do tabuleiro,que é o board
        // e para cada peça, fazer um donCAstin para chesspieces
        for (int i = 0; i < board.getRows(); i++) {
            for (int j = 0; j < board.getColumns(); j++) {
                matriz[i][j] = (ChessPieces) board.piece(i, j);
            }
        }
        return matriz;
    }


    /**
     * Executar movimento de xadrez
     *
     * @param sourcePosition (posição de origem)
     * @param targetPosition (posição destinho)
     * @return ChessPieces
     */
    public ChessPieces performChessMove(ChessPosition sourcePosition, ChessPosition targetPosition) {
        Position source = sourcePosition.toPosition();
        Position target = targetPosition.toPosition();
        validateSourcePosition(source);
        validateTargetPosition(source, target);
        Piece capturedPiece = makeMove(source, target);
        return (ChessPieces) capturedPiece;
    }


    /**
     * A responsavel direta por fazer o movimento, onde possui a lógica de movimento
     *
     * @param source
     * @param target
     * @return
     */
    private Piece makeMove(Position source, Position target) {
        Piece p = board.removePiece(source);
        Piece capturedPiece = board.removePiece(target);
        board.placePiece(p, target);
        return capturedPiece;
    }

    /**
     * Valida a posição de destino da peça.
     *
     * @param source
     * @param target
     */
    private void validateTargetPosition(Position source, Position target) {
        if (!board.piece(source).possibleMove(target)) {
            throw new ChessException("Não é possivel fazer o movimento.");
        }
    }

    /**
     * Validar a posição de origem.
     *
     * @param position
     */
    private void validateSourcePosition(Position position) {
        if (!board.thereIsAPiece(position)) {
            throw new ChessException("Não existe peça na posição de origem.");
        }
        if (!board.piece(position).isThereAnyPossibleMove()) {
            throw new ChessException("Não existe movimentos possiveis para a peça.");
        }
    }

    /**
     * Coloque uma peça
     * Vai receber as coordenadas do xadrez, converter em coordenada de matriz
     *
     * @param column
     * @param row
     * @param piece
     */
    private void placeNewPiece(char column, int row, ChessPieces piece) {
        board.placePiece(piece, new ChessPosition(column, row).toPosition());
    }


    /**
     * Responsavel por iniciar a partida de xadrez, colocando as peças no tabuleiro;
     * <br>
     * <br>// Explicação: codigo comentado coloca a peça conforme cordenada do array,
     * ja o codigo que não está coloca na coordenada de xadrez
     * <br>// OBS: rock recebe um tabulerio, para saber onde a peça var ficar e uma cor;
     * <br>// O codigo comentado abaixo está com position (0,0) ao rodar o programa ele vai colocar uma peça na posição A8,
     * o que está errado no xadrez, seria na A1 por isso crimamos o placeNewPiece
     */
    public void initialSetup() {
        // board.placePiece(new king(board,Color.PRETO), new Position(0,0));

        placeNewPiece('c', 1, new Rook(board, Color.BRANCO));
        placeNewPiece('c', 2, new Rook(board, Color.BRANCO));
        placeNewPiece('d', 2, new Rook(board, Color.BRANCO));
        placeNewPiece('e', 2, new Rook(board, Color.BRANCO));
        placeNewPiece('e', 1, new Rook(board, Color.BRANCO));
        placeNewPiece('d', 1, new King(board, Color.BRANCO));

        placeNewPiece('c', 7, new Rook(board, Color.PRETO));
        placeNewPiece('c', 8, new Rook(board, Color.PRETO));
        placeNewPiece('d', 7, new Rook(board, Color.PRETO));
        placeNewPiece('e', 7, new Rook(board, Color.PRETO));
        placeNewPiece('e', 8, new Rook(board, Color.PRETO));
        placeNewPiece('d', 8, new King(board, Color.PRETO));
    }


    // ==================
    //  Getter e setter
    // ==================

    public int getTurn() {
        return turn;
    }

    public void setTurn(int turn) {
        this.turn = turn;
    }

    public Color getCurrentPlayer() {
        return currentPlayer;
    }

    public void setCurrentPlayer(Color currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    public boolean isCheck() {
        return check;
    }

    public void setCheck(boolean check) {
        this.check = check;
    }

    public boolean isCheckMate() {
        return checkMate;
    }

    public void setCheckMate(boolean checkMate) {
        this.checkMate = checkMate;
    }

    public ChessPieces getEnPassantVulnearable() {
        return enPassantVulnearable;
    }

    public void setEnPassantVulnearable(ChessPieces enPassantVulnearable) {
        this.enPassantVulnearable = enPassantVulnearable;
    }

    public ChessPieces getPromoted() {
        return promoted;
    }

    public void setPromoted(ChessPieces promoted) {
        this.promoted = promoted;
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }
}