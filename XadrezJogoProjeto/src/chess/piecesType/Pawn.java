package piecesType;

import boardGame.Board;
import boardGame.Position;
import chess.ChessPieces;
import chess.Color;

public class Pawn extends ChessPieces {

    @Override
    public String toString() {
        return " P ";
    }

    public Pawn(Board board, Color color) {
        super(board, color);
    }

    @Override
    public boolean[][] possibleMoves() {
        boolean[][] matrizAux = new boolean[getBoard().getRows()][getBoard().getColumns()];
        Position posicaoAux = new Position(0, 0);


        if (getColor() == Color.BRANCO) {

            // atualizando a posição acima
            posicaoAux.setValues(position.getRow() - 1, position.getColumn());
            // posição existe no tabuleiro e não existe peça do oponente
            if (getBoard().positionExists(posicaoAux) && !getBoard().thereIsAPiece(posicaoAux)) {
                matrizAux[posicaoAux.getRow()][posicaoAux.getColumn()] = true;
            }
            // atualizando duas posições acima,
            posicaoAux.setValues(position.getRow() - 2, position.getColumn());
            // Cria uma nova posição acima
            Position p2 = new Position(position.getRow() - 1, position.getColumn());
            // a posição do tabuleiro existe, e não tem uma peça no posição do tabule e
            if (getBoard().positionExists(posicaoAux) && !getBoard().thereIsAPiece(posicaoAux) &&
                    // a nova posição existe e não tem peça nessa posição e
                    getBoard().positionExists(p2) && !getBoard().thereIsAPiece(p2) && getMoveCount() == 0) {
                matrizAux[posicaoAux.getRow()][posicaoAux.getColumn()] = true;
            }
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



    private void checkDirection(Position posicaoAux, boolean[][] mat, int linhaDirecao, int colunaDirecao) {
        // atualizar a posição da peça linha/coluna
        posicaoAux.setValues(getPosition().getRow()+linhaDirecao,getPosition().getColumn()+colunaDirecao);

        if(getBoard().positionExists(posicaoAux) && !isThereOpponentPiece(posicaoAux)){
            mat[posicaoAux.getRow()][posicaoAux.getColumn()] = true;
        }else  if (getBoard().positionExists(posicaoAux) && isThereOpponentPiece(posicaoAux) &&  getMoveCount() == 0) {
            mat[posicaoAux.getRow()][posicaoAux.getColumn()] = true;
        }
    }
}


/*
        ChessPieces chessPieces;

        if (getColor() == Color.BRANCO) {
            if(getPosition().getRow() == 6){
                //para os caso onde não tem peça na diagonal
                checkDirection(posicaoAux,matrizAux,-2,0);
                checkDirection(posicaoAux,matrizAux,-1,0);

                //para o caso onde tenha peça na diagonal
                checkDirection(posicaoAux,matrizAux,-1,+1);
                checkDirection(posicaoAux,matrizAux,-1,-1);


            }
            else{
                checkDirection(posicaoAux,matrizAux,-1,0);
            }

        } else {

        }
*/