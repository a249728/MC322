import java.util.ArrayList;

public class SensorColisao extends Sensor {
    public SensorColisao(double raio, int bat, Robo robo) {
        super(raio, bat, robo);
    }

    public String monitorarColisao(int x, int y, Ambiente amb) {
        ArrayList<Robo> obstaculos = amb.retornarRobosAtivos();
        int coord[] = robo.exibirPosicao();
        int deltaX = x - coord[0];
        int deltaY = y - coord[1];
        for (Robo obstaculo : obstaculos) {
            if (obstaculo.exibirPosicao()[0] == coord[0] + deltaX && obstaculo.exibirPosicao()[1] == coord[1] + deltaY && obstaculo != robo) {
                return "Ha possibilidade de colisao detectada com " + obstaculo.retornarNome();
            }
        }
        return "Nao ha possibilidade de colisao";
    }
}

