package chess;

import boardGame.Board;

public class ChessMatch {

    private int turn;
    private Color currentPlayer;
    private boolean check;
    private boolean checkMate;
    private ChessPieces enPassantVulnearable;
    private ChessPieces promoted;

    private Board board;

    public ChessMatch() {
        this.board = new Board(8,8);
    }

    public ChessPieces[][] getPieces(){
        // Vai retornar uma matriz de peças de xadrez, corespondente a partida.
        // Vamos retornar um chessPeace pq o programa so encherga a chesspeace ele não encherga a peace
        ChessPieces[][] matriz=new ChessPieces[board.getRows()][board.getColumns()];
        // Percorer a matriz de peça do tabuleiro,que é o board
        // e para cada peça, fazer um donCAstin para chesspieces
        for (int i = 0; i < board.getRows(); i++) {
            for (int j = 0; j < board.getColumns(); j++) {
                matriz[i][j] = (ChessPieces) board.piece(i,j);
            }
        }
        return matriz;
    }

    public boolean[][] possibleMoves(ChessPosition sourcePosition){
        // TODO
        return null;
    }
    public ChessPieces performChessMove(ChessPosition sourcePosition, ChessPosition targetPosition){
        // TODO
        return null;
    }

    public ChessPieces replacePromotedPiece(String type){
        // TODO
        return null;
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