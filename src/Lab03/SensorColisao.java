import java.util.ArrayList;

public class SensorColisao extends Sensor {
    public SensorColisao(double raio, int bat, Robo robo) {
        super(raio, bat, robo);
    }

    public boolean monitorarColisao(int x, int y, int z, Ambiente amb) {
        if (!this.monitorar(x, y, z)) {
            return true; // Não foi possível monitorar o ponto
        }
        ArrayList<Robo> robos = amb.retornarRobosAtivos();
        ArrayList<Obstaculo> obstaculos = amb.retornarObstaculos();
        int altura1 = 0;
        int altura2 = 0;
        for (Robo rob : robos) {
            if (rob.exibirPosicao()[0] == x && rob.exibirPosicao()[1] == y && rob != robo) {
                if (rob.getClass() == RoboAereo.class) {
                    RoboAereo roboAereo = (RoboAereo) rob;
                    altura1 = roboAereo.exibirAltura();
                }         
                if (robo.getClass() == RoboAereo.class) {
                    RoboAereo roboAereo = (RoboAereo) robo;
                    altura2 = roboAereo.exibirAltura();
                }
                if (altura1 == altura2) {
                    return true; // Possibilidade de colisão detectada com outro robô
                }
            }
        }
        for (Obstaculo obstaculo : obstaculos) {
            int alturaObstaculo = obstaculo.getObstaculo().getAltura();
            int alturaRobo = 0;
            if (x > obstaculo.getPosicaoX() && x < obstaculo.getPosicaoX() + obstaculo.getObstaculo().getComprimento() && y > obstaculo.getPosicaoY() && y < obstaculo.getPosicaoY() + obstaculo.getObstaculo().getLargura()) {
                if (robo.getClass() == RoboAereo.class) {
                    RoboAereo roboAereo = (RoboAereo) robo;
                    alturaRobo = roboAereo.exibirAltura();
                }
                if (Math.abs(alturaRobo) < Math.abs(alturaObstaculo)) {
                    return true; // Possibilidade de colisão detectada com um obstáculo
                }
            }
        }
        return false; // Não há possibilidade de colisão
    }
}

