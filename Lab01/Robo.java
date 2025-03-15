public class Robo {
    private String nome;
    private int posicaoX;
    private int posicaoY;

    public Robo(String n, int x, int y) {
        this.nome = n;
        this.posicaoX = x;
        this.posicaoY = y;
    }

    public void mover(int deltaX, int deltaY) {
        this.posicaoX += deltaX;
        this.posicaoY += deltaY;
    }

    public int[] exibirPosicao() {
        return new int[] { this.posicaoX, this.posicaoY };
    }

    public String retornarNome() {
        return this.nome;
    }
}
