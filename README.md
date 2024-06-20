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
    git clone https://github.com/seu-usuario/projeto-xadrez.git
    ```

2. **Compile o projeto**:
    ```bash
    javac -d bin src/*.java
    ```

3. **Execute a aplicação**:
    ```bash
    java -cp bin Main
    ```


## Contribuições

Contribuições são bem-vindas! Se você deseja contribuir com este projeto, por favor, siga as diretrizes abaixo:

1. **Fork** o repositório.
2. Crie uma nova branch com suas alterações:
    ```bash
    git checkout -b minha-branch
    ```
3. Faça o commit de suas alterações:
    ```bash
    git commit -m "Minha contribuição"
    ```
4. Envie para o branch original:
    ```bash
    git push origin minha-branch
    ```
5. Abra um Pull Request.

## Licença

Este projeto está licenciado sob a Licença MIT. Veja o arquivo [LICENSE](LICENSE) para mais detalhes.

## Contato

Para mais informações, entre em contato através do email: [seu-email@example.com](mailto:seu-email@example.com).
