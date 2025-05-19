public class Obstaculo {
    private TipoObstaculo obstaculo;
    private final int posicaoX;
    private final int posicaoY;

    public Obstaculo(TipoObstaculo tipo, int x, int y) {
        this.obstaculo = tipo;
        this.posicaoX = x;
        this.posicaoY = y;
    }

    public void removerObstaculo(Obstaculo obs, Ambiente amb) {
        // Remove o obstaculo
        amb.destruirObstaculo(obs);
    }

    public TipoObstaculo getObstaculo() {
        // Retorna o tipo do obstaculo
        return obstaculo;
    }

    public int getPosicaoX() {
        // Retorna a posicao x do obstaculo
        return posicaoX;
    }

    public int getPosicaoY() {
        // Retorna a posicao y do abstaculo
        return posicaoY;
    }
}

