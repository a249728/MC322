class Robo {
    private String nome;
    private int posicaoX;
    private int posicaoY;

    public Robo(String n, int x, int y) {
        nome = n;
        posicaoX = x;
        posicaoY = y;
    }

    public void mover(int x, int y) {
        posicaoX = x;
        posicaoY = y;
    }

    public void exibirPosicao() {
        System.out.println("O robô " + nome + " está na posição (" + posicaoX + ", " + posicaoY + ")");
    }
}
