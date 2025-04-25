import java.util.ArrayList;

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
        // checa se ja nao existe um obstaculo ou um robo naquela posicao
        ArrayList<Robo> robos = amb.retornarRobosAtivos();
        ArrayList<Obstaculo> obstaculos = amb.retornarObstaculos();
        for (Robo robo : robos) {
            if (robo.exibirPosicao()[0] >= x && robo.exibirPosicao()[0] < x + obstaculo.getComprimento() && robo.exibirPosicao()[1] >= y && robo.exibirPosicao()[1] < y + obstaculo.getLargura()) {
                return null;
            }
        }
        for (Obstaculo obs : obstaculos) {
            if (obs.getPosicaoX() >= x && obs.getPosicaoX() < x + obstaculo.getComprimento() && obs.getPosicaoY() >= y && obs.getPosicaoY() < y + obstaculo.getLargura()) {
                return null;
            }
        }
        Obstaculo obs = new Obstaculo(tipo, x, y);
        amb.adicionarObstaculo(obs);
        return obs;
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

