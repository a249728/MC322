public class Obstaculo {
    private TipoObstaculo obstaculo;
    private final int posicaoX;
    private final int posicaoY;

    public Obstaculo(String tipo, int x, int y) {
        this.obstaculo = TipoObstaculo.valueOf(tipo);
        this.posicaoX = x;
        this.posicaoY = y;
    }

    public Obstaculo criarObstaculo(Ambiente amb, String tipo, int x, int y) {
        Obstaculo obs = new Obstaculo(tipo, x, y);
        amb.adicionarObstaculo(obs);
        return obs;
    }

    public Obstaculo removerObstaculo(Ambiente amb, int x, int y) {
        for (Obstaculo obstaculo : amb.retornarObstaculos()) {
            if (obstaculo.posicaoX == x && obstaculo.posicaoY == y) {
                amb.retornarObstaculos().remove(obstaculo);
                return obstaculo;
            }
        }
        return null;
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

