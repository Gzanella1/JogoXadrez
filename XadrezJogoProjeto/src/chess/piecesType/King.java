package chess.piecesType;

import boardGame.Board;
import boardGame.Position;
import chess.ChessPieces;
import chess.Color;

public class King extends ChessPieces {
    public King(Board board, Color color) {
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

        //Acima
        checkDirection(posicaoAux,matrizAuxiliar,-1,0);
        //abaixo
        checkDirection(posicaoAux,matrizAuxiliar,+1,0);

        // Diagonal superior esquerda
        checkDirection(posicaoAux,matrizAuxiliar,-1,-1);
        // Diagonar superior direita
        checkDirection(posicaoAux,matrizAuxiliar,-1,+1);

        //diagonal inferior equerda
        checkDirection(posicaoAux,matrizAuxiliar,+1,-1);
        //diagonal inferior direita
        checkDirection(posicaoAux,matrizAuxiliar,+1,+1);

        //esquerda
        checkDirection(posicaoAux,matrizAuxiliar,0,-1);
        //direita
        checkDirection(posicaoAux,matrizAuxiliar,0,+1);

        return matrizAuxiliar;
    }
    private void checkDirection(Position posicaoAux, boolean[][] matrizAuxiliar, int linhaDirecao, int colunaDirecao) {
        posicaoAux.setValues(getPosition().getRow()+linhaDirecao, getPosition().getColumn()+colunaDirecao);
        if(getBoard().positionExists(posicaoAux) && podeMover(posicaoAux)){
            matrizAuxiliar[posicaoAux.getRow()][posicaoAux.getColumn()] = true;
        }
    }

    /**
     * Verifica se rei pode mover para uma determinada posição.
     *
     * @return boolean
     */
    private boolean podeMover(Position position) {
        ChessPieces p = (ChessPieces) getBoard().piece(position);
        return p == null || p.getColor() != getColor();
    }

}
