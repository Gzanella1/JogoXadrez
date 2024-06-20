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

Claro! Aqui está a seção melhorada com as instruções de como jogar, incluindo as imagens explicativas:

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

1. **Posição de Origem**: Ao iniciar, o jogo estará aguardando a seleção da peça que deseja mover.
2. **Movimentos Possíveis**: Após selecionar a peça, os possíveis movimentos serão destacados em azul.
3. **Destino**: Indique para onde deseja mover a peça selecionada.

![image](https://github.com/Gzanella1/JogoXadrez/assets/96748771/a703ff2e-4e9a-43c6-b8e2-bc862fc4be4d)
![image](https://github.com/Gzanella1/JogoXadrez/assets/96748771/27e27013-2bae-45d3-a8b2-a15e12061a82)

### Promoção de Peça

Quando um peão atinge a última fileira, ele pode ser promovido a uma peça mais poderosa (Rainha, Torre, Bispo ou Cavalo).

![image](https://github.com/Gzanella1/JogoXadrez/assets/96748771/35ab2d86-8022-4a22-bb5d-b0d2303ec16c)

### Xeque-Mate

Se um jogador estiver em xeque-mate, uma mensagem em vermelho informará que o jogador está em xeque.

![image](https://github.com/Gzanella1/JogoXadrez/assets/96748771/47520d0d-d047-4d54-87f7-7b8a8c85f1aa)

---

Se precisar de mais alguma ajuda ou tiver outras dúvidas, estou à disposição!

