import java.util.ArrayList;

public class SensorColisao extends Sensor {
    public SensorColisao(double raio, int bat, Robo robo) {
        super(raio, bat, robo);
    }

    public String monitorarColisao(int x, int y, Ambiente amb) {
        if (!this.monitorar(x, y)) {
            return "Nao foi possivel monitorar essa posicao";
        }
        ArrayList<Robo> robos = amb.retornarRobosAtivos();
        ArrayList<Obstaculo> obstaculos = amb.retornarObstaculos();
        int coord[] = robo.exibirPosicao();
        int deltaX = x - coord[0];
        int deltaY = y - coord[1];
        for (Robo robo : robos) {
            if (robo.exibirPosicao()[0] == coord[0] + deltaX && robo.exibirPosicao()[1] == coord[1] + deltaY && robo != this.robo) {
                return "Ha possibilidade de colisao detectada com " + robo.retornarNome();
            }
        }
        for (Obstaculo obstaculo : obstaculos) {
            if (obstaculo.getPosicaoX() == coord[0] && y > obstaculo.getPosicaoY() && y < obstaculo.getPosicaoY() + obstaculo.getObstaculo().getLargura()) {
                return "Ha possibilidade de colisao detectada com obstaculo";
            }
        }
        return "Nao ha possibilidade de colisao";
    }
}

