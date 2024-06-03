package chess;

import application.UI;
import boardGame.Board;
import boardGame.Piece;
import boardGame.Position;
import chess.piecesType.Rook;
import chess.piecesType.King;
import piecesType.Pawn;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ChessMatch {

    private int turn;
    private Board board;
    private Color currentPlayer;

    private boolean check;
    private boolean checkMate;

    private ChessPieces enPassantVulnearable;
    private ChessPieces promoted;

    private List<Piece> piecesOnTheBoard = new ArrayList<>();
    private List<Piece> capturedPieces = new ArrayList<>();


    public ChessMatch() {
        this.board = new Board(8, 8);
        turn = 1;
        currentPlayer = Color.BRANCO;
        initialSetup();
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

        placeNewPiece('h', 7, new Rook(board, Color.BRANCO));
        placeNewPiece('d', 1, new Rook(board, Color.BRANCO));
        placeNewPiece('e', 1, new King(board, Color.BRANCO));

        placeNewPiece('a' ,2, new Pawn(board, Color.BRANCO));
        placeNewPiece('a' ,3, new Pawn(board, Color.PRETO));

        placeNewPiece('b' ,2, new Pawn(board, Color.BRANCO));
        placeNewPiece('c' ,2, new Pawn(board, Color.BRANCO));
        placeNewPiece('d' ,2, new Pawn(board, Color.BRANCO));

        placeNewPiece('b', 8, new Rook(board, Color.PRETO));
        placeNewPiece('a', 8, new King(board, Color.PRETO));

    }

    /**
     * Executar movimento de xadrez, recece as posições de origem e destino, chama os metodo que faz
     * as verificações das posições.
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

        // se o mov que o usuario fez o colocou em check
        // desfazer agente desfaz o movimento
        if (testCheck(currentPlayer)) {
            undoMove(source, target, capturedPiece);
            throw new ChessException("Você não pode se colocar em check");
        }
        check = testCheck(opponent(currentPlayer));

        // se a jogada feita, deixou o meu oponente em checkMate, o jogo acaba
        if (testCheckMate(opponent(currentPlayer))) {
            checkMate = true;
        } else {
            nextTurn();
        }

        return (ChessPieces) capturedPiece;
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
     * Essa operação retorna a matriz contendo true, onde e permitido fazer o movimento
     * Ultilizamos para colorir o fundo das posições possiveis.
     *
     * @param sourcePosition
     * @return
     */
    public boolean[][] possibleMoves(ChessPosition sourcePosition) {
        Position position = sourcePosition.toPosition();
        validateSourcePosition(position);
        return board.piece(position).possibleMoves();
    }

    /**
     * Coloque uma peça no tabuleiro
     * Vai receber as coordenadas do xadrez, converter em coordenada de matriz
     *
     * @param column
     * @param row
     * @param piece
     */
    private void placeNewPiece(char column, int row, ChessPieces piece) {
        board.placePiece(piece, new ChessPosition(column, row).toPosition());
        piecesOnTheBoard.add(piece);
    }


    /**
     * A responsavel direta por fazer o movimento, onde possui a lógica de movimento, e captura a peça do tabuleiro
     * <p>
     * removemos a peça "p" na posição source
     * caso tenha alguma peça na posição target, fazemos a captura da peça
     * colocamos a peça "p" na posição target, e retornamos caso tenha alguma peça que foi capturada
     *
     * @param source
     * @param target
     * @return capturedPiece
     */
    private Piece makeMove(Position source, Position target) {
        //tira a peça de origem do tabuleiro
        Piece p = board.removePiece(source);

        ((ChessPieces)p).increaseMove();
        // tira uma POSSIVEL PESSA que está na posição de destino
        Piece capturedPiece = board.removePiece(target);
        // coloca na posição do destino a peça que esta na origem
        board.placePiece(p, target);

        if (capturedPiece != null) {
            piecesOnTheBoard.remove(capturedPiece);
            capturedPieces.add(capturedPiece);
        }
        return capturedPiece;
    }

    /**
     * Metodo responsavel por desfazer o movimento que o metodo makeMove faz.
     *
     * @param source
     * @param target
     * @param capturedPiece
     */
    private void undoMove(Position source, Position target, Piece capturedPiece) {
        // tirar a peça do destino
        Piece peca = board.removePiece(target);
        ((ChessPieces)peca).decreaseMove();
        // colocar essa peça na origem
        board.placePiece(peca, source);

        if (capturedPiece != null) {
            //colocar a peça na posição do tabuleiro
            board.placePiece(capturedPiece, target);
            piecesOnTheBoard.add(peca);
            capturedPieces.remove(peca);

        }
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
        //verifica se a cor da peça é diferente da cor do usuario atual
        if (getCurrentPlayer() != ((ChessPieces) getBoard().piece(position)).getColor()) {
            throw new ChessException("A peça escolhida não e sua.");
        }
        if (!board.piece(position).isThereAnyPossibleMove()) {
            throw new ChessException("Não existe movimentos possiveis para a peça.");
        }
    }

    /**
     * Responsavel por trocar o turno das jogadas.
     * ex: o jogador das peças branco jogou agora é a vez das peças pretas.
     */
    private void nextTurn() {
        turn++;
        currentPlayer = (currentPlayer == Color.BRANCO) ? Color.PRETO : Color.BRANCO;
    }


    public ChessPieces replacePromotedPiece(String type) {
        // TODO
        return null;
    }

    /**
     * Retorna qual é oponente de uma determinada peça.
     *
     * @param color
     * @return
     */
    private Color opponent(Color color) {
        return (color == Color.BRANCO) ? Color.PRETO : Color.BRANCO;
    }

    /**
     * Ele localiza onde esta o rei de uma determinada cor.
     *
     * @param cor
     * @return
     */
    private ChessPieces king(Color cor) {
        //procurar na lista de peças em jogo qual é o rei da cor
        List<Piece> list = piecesOnTheBoard.stream().filter(piece -> ((ChessPieces) piece).getColor() == cor).toList();
        for (Piece p : list) {
            if (p instanceof King) {
                return (ChessPieces) p;
            }
        }
        throw new IllegalStateException("não existe o king da cor " + cor);
    }

    /**
     * Método responsavel por verificar se um jogador está em check.
     * OBS: check é diferente de checkMate
     *
     * @param cor
     * @return
     */
    private boolean testCheck(Color cor) {
        //pega a posição do rei em formato de matriz
        //Obter a posição do rei
        //Obter peças do oponente
        //Verificar ameaças ao rei
        Position kingPosition = king(cor).getChessPosition().toPosition();
        List<Piece> opponentePieces = piecesOnTheBoard.stream().filter(
                piece -> ((ChessPieces) piece).getColor() == opponent(cor)).toList();

        for (Piece p : opponentePieces) {
//            boolean[][] matComMovPossiveis= p.possibleMoves();
//             if(matComMovPossiveis[kingPosition.getRow()][kingPosition.getColumn()]){
//                return  true;
//             }
            //se a peça pode mover para a posição onde o rei esta então check
            if (p.possibleMove(kingPosition)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Verifica se a partida estiver em checkMate.
     * <p>
     * OBS: checkMate é diferente de check.
     * CheckMate é quando o rei estiver em check e não
     * haver mais movimentos possiveis tanto para o rei quanto para as outras peças.
     *
     * @param cor
     * @return
     */
    private boolean testCheckMate(Color cor) {
        if (!testCheck(cor)) {
            return false;
        }
        // Lista de peça do meu oponente
        // Se todas as peças da cor, não estiverem movimentos possiveis que tire do check então está em checkMate
        List<Piece> list = piecesOnTheBoard.stream().filter(peca -> ((ChessPieces) peca).getColor() == opponent(cor)).toList();
        for (Piece peca : list) {
            // matriz que guarda as posições na qual a peça atual pode se mover
            boolean[][] matriz = peca.possibleMoves();

            // não posso percorer a matriz dessa forma "matriz.length", pois ela não é o meu tabuleiro
            for (int i = 0; i < getBoard().getRows(); i++) {
                for (int j = 0; j < getBoard().getColumns(); j++) {
                    if (matriz[i][j]) {
                        // a origem é a posição da peca atual
                        Position source = ((ChessPieces) peca).getChessPosition().toPosition();
                        Position targuet = new Position(i, j);

                        // realiza o movimento APENAS PARA TESTAR se o rei do oponente estiver em check
                        Piece capturedPiece = makeMove(source, targuet);

                        // Testa se a peça rei do meu oponente estiver em check
                        boolean testCheck = testCheck(cor);

                        // DESFAZER O MOVIMENTO FEITO PARA TEXTE ANTERIORMENTE PARA TESTE
                        undoMove(source, targuet, capturedPiece);
                        // Se não estava em check, significa que esse movimento tirou rei do check
                        if (!testCheck) {
                            return false;
                        }
                    }
                }
            }
        }
        return false;
    }


    // ==================
    //  Getter e setter
    // ==================

    public int getTurn() {
        return turn;
    }

    public Color getCurrentPlayer() {
        return currentPlayer;
    }

    public boolean getCheck() {
        return check;
    }

    public void setCheck(boolean check) {
        this.check = check;
    }

    public boolean getCheckMate() {
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