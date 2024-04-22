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

    public ChessPieces[][] getPieces(){
        // TODO
        return null;
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


    // GETTER E SETTER
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