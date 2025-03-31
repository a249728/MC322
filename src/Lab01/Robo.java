public class Robo {
    // Declara as propriedades da classe Robo
    private String nome;
    private int posicaoX;
    private int posicaoY;

    public Robo(String n, int x, int y) {
        // Metodo construtor da classe
        this.nome = n;
        this.posicaoX = x;
        this.posicaoY = y;
    }

    public void mover(int deltaX, int deltaY) {
        // Atualiza as coordenadas do robo adicionando a variacao de posicao desejada
        this.posicaoX += deltaX;
        this.posicaoY += deltaY;
    }

    public int[] exibirPosicao() {
        // Retorna o vetor de coordenadas do robo
        return new int[] { this.posicaoX, this.posicaoY };
    }

    public String retornarNome() {
        // Retorna o nome do robo
        return this.nome;
    }
}
