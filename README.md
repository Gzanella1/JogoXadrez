# Projeto de Xadrez

## Descrição

Este projeto é uma implementação de um jogo de xadrez em Java. Ele simula um tabuleiro de xadrez com todas as regras do jogo, permitindo que dois jogadores joguem entre si. O projeto foi desenvolvido com o objetivo de praticar conceitos de programação orientada a objetos, como encapsulamento, herança e polimorfismo.

## Funcionalidades

- **Tabuleiro de Xadrez**: Representação do tabuleiro de xadrez com peças posicionadas corretamente.
- **Movimentos das Peças**: Implementação dos movimentos permitidos para cada tipo de peça.
- **Captura de Peças**: Suporte para a captura de peças adversárias.
- **Xeque e Xeque-Mate**: Verificação das condições de xeque e xeque-mate.
- **Promoção de Peão**: Suporte para a promoção de peões.
- **En Passant**: Implementação do movimento especial de captura en passant.

## Estrutura do Projeto

O projeto está organizado nas seguintes classes principais:

- **Board**: Representa o tabuleiro de xadrez.
- **Piece**: Classe abstrata que representa uma peça de xadrez.
- **Position**: Representa uma posição no tabuleiro.
- **ChessMatch**: Gerencia uma partida de xadrez, controlando o estado do jogo.
- **ChessPiece**: Extende a classe `Piece`, adicionando características específicas do xadrez.

## Como Executar

Para executar o projeto, siga as instruções abaixo:

1. **Clone o repositório**:
    ```bash
    git clone https://github.com/Gzanella1/JogoXadrez.git
    ```

2. **Compile e execute o projeto**:

Claro! Aqui está a seção melhorada do README com imagens explicativas de como jogar:

---

## Como Jogar

Abaixo temos a primeira visualização do jogo. As peças brancas estão na cor azul e as pretas na cor amarela. 

![image](https://github.com/Gzanella1/JogoXadrez/assets/96748771/4579cb63-51a1-4c8d-a160-bae537d11af9)

### Interface do Jogo

- **Turno Atual**: Indica de quem é a vez de jogar.
- **Peças Capturadas**: Lista de todas as peças capturadas durante o jogo.

### Legenda das Peças

- **P**: Peão
- **T**: Torre
- **C**: Cavalo
- **B**: Bispo
- **Q**: Rainha
- **K**: Rei

### Início do Jogo

1. **Posição de Origem**: O jogador deve selecionar a peça que deseja mover.
2. **Destino**: Após selecionar a peça, o jogador deve indicar para onde deseja movê-la.

Ao iniciar, o jogo estará aguardando a seleção da posição de origem do jogador. Em seguida, será solicitada a posição de destino.

---

Se precisar de mais alguma ajuda ou tiver outras dúvidas, estou à disposição!
