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
        int altura1 = 0;
        int altura2 = 0;
        for (Robo rob : robos) {
            if (rob.exibirPosicao()[0] == x && rob.exibirPosicao()[1] == y && rob != robo) {
                if (rob.getClass() == RoboAereo.class) {
                    // converter rob do tipo Robo para RoboAereo e adicionar a altura2 o valor de sua altura
                    RoboAereo roboAereo = (RoboAereo) rob;
                    altura1 = roboAereo.exibirAltura();
                }         
                if (robo.getClass() == RoboAereo.class) {
                    // converter robo do tipo Robo para RoboAereo e adicionar a altura1 o valor de sua altura
                    RoboAereo roboAereo = (RoboAereo) robo;
                    altura2 = roboAereo.exibirAltura();
                }
                if (altura1 == altura2) {
                    return "Ha possibilidade de colisao detectada com " + rob.retornarNome();
                }
            }
        }
        for (Obstaculo obstaculo : obstaculos) {
            int alturaObstaculo = obstaculo.getObstaculo().getAltura();
            int alturaRobo = 0;
            if (obstaculo.getPosicaoX() == x && y > obstaculo.getPosicaoY() && y < obstaculo.getPosicaoY() + obstaculo.getObstaculo().getLargura()) {
                if (robo.getClass() == RoboAereo.class) {
                    // converter robo do tipo Robo para RoboAereo e adicionar a alturaRobo o valor de sua altura
                    RoboAereo roboAereo = (RoboAereo) robo;
                    alturaRobo = roboAereo.exibirAltura();
                }
                if (alturaRobo < alturaObstaculo) {
                    return "Ha possibilidade de colisao detectada com um obstaculo";
                }
            }
        }
        return "Nao ha possibilidade de colisao";
    }
}

