package chess;

import boardGame.Board;
import boardGame.Piece;
import boardGame.Position;
import chess.piecesType.*;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ChessMatch {

    private int turn;
    private Board board;
    private Color currentPlayer;

    private boolean check;
    private boolean checkMate;

    private ChessPieces enPassantVulnerable;
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
        placeNewPiece('a', 2, new Pawn(board, Color.BRANCO, this));
        placeNewPiece('b', 2, new Pawn(board, Color.BRANCO, this));
        placeNewPiece('c', 2, new Pawn(board, Color.BRANCO, this));
        placeNewPiece('d', 2, new Pawn(board, Color.BRANCO, this));
        placeNewPiece('e', 2, new Pawn(board, Color.BRANCO, this));
        placeNewPiece('f', 2, new Pawn(board, Color.BRANCO, this));
        placeNewPiece('g', 2, new Pawn(board, Color.BRANCO, this));
        placeNewPiece('h', 2, new Pawn(board, Color.BRANCO, this));

        placeNewPiece('a', 1, new Rook(board, Color.BRANCO));
        placeNewPiece('h', 1, new Rook(board, Color.BRANCO));
        placeNewPiece('b', 1, new Knight(board, Color.BRANCO));
        placeNewPiece('g', 1, new Knight(board, Color.BRANCO));
        placeNewPiece('c', 1, new Bishop(board, Color.BRANCO));
        placeNewPiece('f', 1, new Bishop(board, Color.BRANCO));
        placeNewPiece('d', 1, new Queen(board, Color.BRANCO));
        placeNewPiece('e', 1, new King(board, Color.BRANCO));


        placeNewPiece('a', 7, new Pawn(board, Color.PRETO, this));
        placeNewPiece('b', 7, new Pawn(board, Color.PRETO, this));
        placeNewPiece('c', 7, new Pawn(board, Color.PRETO, this));
        placeNewPiece('d', 7, new Pawn(board, Color.PRETO, this));
        placeNewPiece('e', 7, new Pawn(board, Color.PRETO, this));
        placeNewPiece('f', 7, new Pawn(board, Color.PRETO, this));
        placeNewPiece('g', 7, new Pawn(board, Color.PRETO, this));
        placeNewPiece('h', 7, new Pawn(board, Color.PRETO, this));

        placeNewPiece('a', 8, new Rook(board, Color.PRETO));
        placeNewPiece('h', 8, new Rook(board, Color.PRETO));
        placeNewPiece('b', 8, new Knight(board, Color.PRETO));
        placeNewPiece('g', 8, new Knight(board, Color.PRETO));
        placeNewPiece('c', 8, new Bishop(board, Color.PRETO));
        placeNewPiece('f', 8, new Bishop(board, Color.PRETO));
        placeNewPiece('d', 8, new Queen(board, Color.PRETO));
        placeNewPiece('e', 8, new King(board, Color.PRETO));

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

        ChessPieces pecaMovida = (ChessPieces) board.piece(target);

        // #specialmove promotion
        promoted = null;
        if (pecaMovida instanceof Pawn) {
            if ((pecaMovida.getColor() == Color.BRANCO && target.getRow() == 0) ||
                    (pecaMovida.getColor() == Color.PRETO && target.getRow() == 7)) {
                promoted = (ChessPieces) board.piece(target);
                promoted = replacePromotedPiece("Q");
            }
        }

        check = testCheck(opponent(currentPlayer));
        // se a jogada feita, deixou o meu oponente em checkMate, o jogo acaba
        if (testCheckMate(opponent(currentPlayer))) {
            checkMate = true;
        } else {
            nextTurn();
        }

        // Movimento Especial "en passant"
        if (pecaMovida instanceof Pawn && (target.getRow() == source.getRow() - 2 || target.getRow() == source.getRow() + 2)) {
            //significa que esse peão fez o primeiro movimento e esta vulneravel ao movimento especial
            enPassantVulnerable = pecaMovida;
        } else {
            enPassantVulnerable = null;
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

        ((ChessPieces) p).increaseMove();
        // tira uma POSSIVEL PESSA que está na posição de destino
        Piece capturedPiece = board.removePiece(target);
        // coloca na posição do destino a peça que esta na origem
        board.placePiece(p, target);

        if (capturedPiece != null) {
            piecesOnTheBoard.remove(capturedPiece);
            capturedPieces.add(capturedPiece);
        }

        // Movimento especial el pasant
        if (p instanceof Pawn) {
            if (source.getColumn() != target.getColumn() && capturedPiece == null) {
                Position pawnPosition;
                if (((Pawn) p).getColor() == Color.BRANCO) {
                    pawnPosition = new Position(target.getRow() + 1, target.getColumn());
                } else {
                    pawnPosition = new Position(target.getRow() - 1, target.getColumn());
                }
                capturedPiece = board.removePiece(pawnPosition);
                capturedPieces.add(capturedPiece);
                piecesOnTheBoard.remove(capturedPiece);
            }
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
        ((ChessPieces) peca).decreaseMove();
        // colocar essa peça na origem
        board.placePiece(peca, source);

        if (capturedPiece != null) {
            //colocar a peça na posição do tabuleiro
            board.placePiece(capturedPiece, target);
            piecesOnTheBoard.add(peca);
            capturedPieces.remove(peca);

        }


        // #specialmove en passant
        if (peca instanceof Pawn) {
            if (source.getColumn() != target.getColumn() && capturedPiece == enPassantVulnerable) {
                ChessPieces pawn = (ChessPieces) board.removePiece(target);
                Position pawnPosition;
                if (((Pawn) peca).getColor() == Color.BRANCO) {
                    pawnPosition = new Position(3, target.getColumn());
                } else {
                    pawnPosition = new Position(4, target.getColumn());
                }
                board.placePiece(pawn, pawnPosition);
            }
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
        Position kingPosition = king(cor).getChessPosition().toPosition();
        //
        List<Piece> opponentePieces = piecesOnTheBoard.stream().filter(
                piece -> ((ChessPieces) piece).getColor() == opponent(cor)).toList();

        for (Piece p : opponentePieces) {
            if (p.possibleMove(kingPosition)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Verifica se um jogador está em xeque-mate.
     *
     * @param color A cor do jogador a ser verificada.
     * @return true se o jogador estiver em xeque-mate, caso contrário, false.
     */
    private boolean testCheckMate(Color color) {
        // Verifica se o jogador está em xeque. Se não estiver, retorna false.
        if (!testCheck(color)) {
            return false;
        }

        // Cria uma lista com todas as peças do jogador da cor especificada.
        List<Piece> list = piecesOnTheBoard.stream()
                .filter(x -> ((ChessPieces) x).getColor() == color)
                .collect(Collectors.toList());

        // Itera sobre cada peça do jogador.
        for (Piece p : list) {
            // Obtém a matriz de possíveis movimentos da peça atual.
            boolean[][] mat = p.possibleMoves();

            // Percorre todas as posições do tabuleiro.
            for (int i = 0; i < board.getRows(); i++) {
                for (int j = 0; j < board.getColumns(); j++) {
                    // Se a peça pode se mover para a posição (i, j)
                    if (mat[i][j]) {
                        // Obtém a posição de origem da peça no formato de tabuleiro de xadrez.
                        Position source = ((ChessPieces) p).getChessPosition().toPosition();
                        // Cria um objeto de posição de destino.
                        Position target = new Position(i, j);
                        // Faz o movimento e captura a peça se houver alguma na posição de destino.
                        Piece capturedPiece = makeMove(source, target);
                        // Testa se o jogador ainda está em xeque após o movimento.
                        boolean testCheck = testCheck(color);
                        // Desfaz o movimento.
                        undoMove(source, target, capturedPiece);
                        // Se o movimento tirou o jogador do xeque, retorna false.
                        if (!testCheck) {
                            return false;
                        }
                    }
                }
            }
        }
        // Se nenhum movimento possível tira o jogador do xeque, retorna true (xeque-mate).
        return true;
    }


    /**
     * Este método substitui a peça promovida por uma nova peça do tipo especificado.
     *
     * @param type Tipo da peça promovida (B para Bispo, C para Cavalo, T para Torre, Q para Rainha).
     * @return A nova peça promovida.
     */
    public ChessPieces replacePromotedPiece(String type) {
        // Verifica se há uma peça a ser promovida.
        if (promoted == null) {
            // Se não houver peça a ser promovida, lança uma exceção.
            throw new IllegalStateException("There is no piece to be promoted");
        }
        // Verifica se o tipo fornecido é válido para promoção (B, C, T, Q).
        if (!type.equals("B") && !type.equals("C") && !type.equals("T") && !type.equals("Q")) {
            // Se o tipo for inválido, lança uma exceção de parâmetro inválido.
            throw new InvalidParameterException("Invalid type for promotion");
        }

        // Obtém a posição da peça promovida no tabuleiro de xadrez.
        Position pos = promoted.getChessPosition().toPosition();
        // Remove a peça atual da posição no tabuleiro.
        Piece p = board.removePiece(pos);
        // Remove a peça da lista de peças no tabuleiro.
        piecesOnTheBoard.remove(p);

        // Cria a nova peça do tipo especificado e com a mesma cor da peça promovida.
        ChessPieces newPiece = newPiece(type, promoted.getColor());
        // Coloca a nova peça na posição da peça promovida no tabuleiro.
        board.placePiece(newPiece, pos);
        // Adiciona a nova peça à lista de peças no tabuleiro.
        piecesOnTheBoard.add(newPiece);

        // Retorna a nova peça promovida.
        return newPiece;
    }

    /**
     * Este método cria uma nova peça do tipo e cor especificados.
     *
     * @param tipo Tipo da peça (B para Bispo, C para Cavalo, T para Torre, Q para Rainha).
     * @param cor  Cor da peça.
     * @return A nova peça criada.
     */
    private ChessPieces newPiece(String tipo, Color cor) {
        // Verifica o tipo e retorna a peça correspondente.
        if (tipo.equals("B")) return new Bishop(board, cor);
        if (tipo.equals("C")) return new Knight(board, cor);
        if (tipo.equals("Q")) return new Queen(board, cor);
        // Se não for Bispo, Cavalo ou Rainha, retorna uma Torre por padrão.
        return new Rook(board, cor);
    }


    public ChessPieces getEnPassantVulnerable() {
        return enPassantVulnerable;
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

    public void setEnPassantVulnearable(ChessPieces enPassantVulnearable) {
        this.enPassantVulnerable = enPassantVulnearable;
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