package piecesType;

import boardGame.Board;
import boardGame.Position;
import chess.ChessPieces;
import chess.Color;

public class Pawn extends ChessPieces {

    public Pawn(Board board, Color color) {
        super(board, color);
    }

    @Override
    public String toString() {
        return " P ";
    }

    @Override
    public boolean[][] possibleMoves() {
        boolean[][] matrizAux = new boolean[getBoard().getRows()][getBoard().getColumns()];
        Position posicaoAux = new Position(0, 0);


        if (getColor() == Color.BRANCO) {


            posicaoAux.setValues(position.getRow() - 1, position.getColumn() - 1);
            if (getBoard().positionExists(posicaoAux) && isThereOpponentPiece(posicaoAux)) {
                matrizAux[posicaoAux.getRow()][posicaoAux.getColumn()] = true;
            }
            posicaoAux.setValues(position.getRow() - 1, position.getColumn() + 1);
            if (getBoard().positionExists(posicaoAux) && isThereOpponentPiece(posicaoAux)) {
                matrizAux[posicaoAux.getRow()][posicaoAux.getColumn()] = true;
            }
        }
        return matrizAux;
    }


    private void chceckMove(Position posicaoAux, boolean[][] mat, int linhaDirecao, int colunaDirecao) {
        //atualizar posição
        posicaoAux.setValues(getPosition().getRow() + linhaDirecao, getPosition().getColumn() + colunaDirecao);

        // para ir para cima/posição autalizasda, verificar se a posição existe e se não tem oponente
        if (getBoard().positionExists(posicaoAux) && !getBoard().thereIsAPiece(posicaoAux)) {
            mat[posicaoAux.getRow()][posicaoAux.getColumn()] = true;

        }
    }

    private void checkDupleMove(Position posicaoAux, boolean[][] matrizAux, int linhaDirecao, int colunaDirecao) {
        posicaoAux.setValues(position.getRow() + linhaDirecao, position.getColumn());
        Position p2 = new Position(position.getRow() + linhaDirecao, position.getColumn());

        // existe a posição
        boolean condicao1 = getBoard().positionExists(posicaoAux);
        // não existe peça na posição
        boolean condicao2 = !getBoard().thereIsAPiece(posicaoAux);
        // existe a posição pd
        boolean condicao3 = getBoard().positionExists(p2);

        boolean condicao4 = !getBoard().thereIsAPiece(p2);
        boolean condicao5 = getMoveCount() == 0;

        if (condicao1 && condicao2 && condicao3 && condicao4 && condicao5) {
            matrizAux[posicaoAux.getRow()][posicaoAux.getColumn()] = true;
        }
    }


    private void checkEnPassant(Position posicaoAux, boolean[][] mat, int linhaDirecao, int colunaDirecao) {
        //atualizar posição
        posicaoAux.setValues(getPosition().getRow() + linhaDirecao, getPosition().getColumn() + colunaDirecao);
    }

}

