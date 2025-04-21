public class Obstaculo {
    private TipoObstaculo obstaculo;
    private final int posicaoX;
    private final int posicaoY;

    public Obstaculo(String tipo, int x, int y) {
        this.obstaculo = TipoObstaculo.valueOf(tipo);
        this.posicaoX = x;
        this.posicaoY = y;
    }

    public TipoObstaculo getObstaculo() {
        return obstaculo;
    }

    public int posicaoX() {
        return posicaoX;
    }

    public int posicaoY() {
        return posicaoY;
    }
}

