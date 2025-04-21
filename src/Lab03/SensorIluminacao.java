import java.util.ArrayList;

public class SensorIluminacao extends Sensor {
    public SensorIluminacao(double raio, int bat, Robo robo) {
        super(raio, bat, robo);
    }

    public String monitorarIluminacao(int x, int y, Ambiente amb) {
        if (!this.monitorar(x, y)) {
            return "Nao foi possivel monitorar essa posicao";
        }
        ArrayList<Robo> robos = amb.retornarRobosAtivos();
        ArrayList<Obstaculo> obstaculos = amb.retornarObstaculos();
        String dir = amb.retornarPosSol();
        if (dir.equals("Leste")) {
            for (Robo robo : robos) {
                // Checa se existe um robo na coordenada x+1, y
                if (robo.exibirPosicao()[0] == x + 1 && robo.exibirPosicao()[1] == y) {
                    return "Sombra";
                }
            }
            for (Obstaculo obstaculo : obstaculos) {
                // Checa se existe um obstaculo na coordenada x+1, y
                if (obstaculo.getPosicaoX() == x + 1 && y > obstaculo.getPosicaoY() && y < obstaculo.getPosicaoY() + obstaculo.getObstaculo().getLargura()) {
                    return "Sombra";
                }
            }
            return "Iluminado";
        }
        else if (dir.equals("Oeste")) {
            for (Robo robo : robos) {
                // Checa se existe um robo na coordenada x-1, y
                if (robo.exibirPosicao()[0] == x - 1 && robo.exibirPosicao()[1] == y) {
                    return "Sombra";
                }
                return "Iluminado";
            }
            for (Obstaculo obstaculo : obstaculos) {
                // Checa se existe um obstaculo na coordenada x+1, y
                if (x > obstaculo.getPosicaoX() && x < obstaculo.getPosicaoX() + obstaculo.getObstaculo().getLargura() && y > obstaculo.getPosicaoY() && y < obstaculo.getPosicaoY() + obstaculo.getObstaculo().getLargura()) {
                    return "Sombra";
                }
            }
            return "Iluminado";
        }
        return "A direcao indicada do sol e invalida";
    }
}