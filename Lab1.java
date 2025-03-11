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

class Ambiente {
    private int altura;
    private int largura;

    public Ambiente(int x, int y) {
        altura = x;
        largura = y;
    }

    public void dentroDosLimites(int x, int y) {
        if (x < 0 || x >= largura || y < 0 || y >= altura) {
            System.out.println("O robô ultrapassou os limites do ambiente");
        }
    }
}

