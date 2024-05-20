package chess.piecesType;

import boardGame.Board;
import boardGame.Position;
import chess.ChessPieces;
import chess.Color;

public class King extends ChessPieces {
    public King(Board board, Color color ) {
        super(board, color);
    }

    @Override
    public String toString() {
        return " K ";
    }

    @Override
    public boolean[][] possibleMoves() {
        boolean[][] matrizAuxiliar = new boolean[getBoard().getRows()][getBoard().getColumns()];
        Position posicaoAux= new Position(0,0);

        return matrizAuxiliar;
    }
    private void checkDirection(Position posicaoAux, boolean[][] matrizAuxiliar, int linhaDirecao, int colunaDirecao) {
        // como essa posição axiliar está indice [0][0] da matriz vamos somaar com linha da direção e coluna da direção
        // para avançando na matriz auxiliar
        posicaoAux.setValues(getPosition().getRow() + linhaDirecao,getPosition().getColumn() + colunaDirecao);

        while (getBoard().positionExists(posicaoAux) && !getBoard().thereIsAPiece(posicaoAux)){
          matrizAuxiliar[getBoard().getRows()][getBoard().getColumns()]=true;
        }
    }

        /**
         * Verifica se rei pode mover para uma determinada posição.
         * @return boolean
         */
    private boolean podeMover(Position position){
        ChessPieces p= (ChessPieces) getBoard().piece(position);
        return  p != null && p.getColor() != getColor();

    }

}
