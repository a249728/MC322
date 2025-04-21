import java.util.ArrayList;

public class SensorColisao extends Sensor {

    public SensorColisao(double raio, int bat, Robo robo) {
        super(raio, bat, robo);
    }

    public boolean monitorarColisao(int x, int y, int z, Ambiente ambiente) {
        // Verifica se o ponto está dentro do alcance do sensor
        if (!this.monitorar(x, y, z)) {
            return true; // Não foi possível monitorar o ponto
        }

        // Obtém os robôs ativos e os obstáculos do ambiente
        ArrayList<Robo> robosAtivos = ambiente.retornarRobosAtivos();
        ArrayList<Obstaculo> obstaculos = ambiente.retornarObstaculos();

        // Verifica colisão com outros robôs
        for (Robo roboAtivo : robosAtivos) {
            if (roboAtivo != robo && roboAtivo.exibirPosicao()[0] == x && roboAtivo.exibirPosicao()[1] == y) {
                int alturaRobo = (roboAtivo instanceof RoboAereo) ? ((RoboAereo) roboAtivo).exibirAltura() : 0;
                if (alturaRobo == z) {
                    return true; // Colisão detectada com outro robô
                }
            }
        }

        // Verifica colisão com obstáculos
        for (Obstaculo obstaculo : obstaculos) {
            int posX = obstaculo.getPosicaoX();
            int posY = obstaculo.getPosicaoY();
            int comprimento = obstaculo.getObstaculo().getComprimento();
            int largura = obstaculo.getObstaculo().getLargura();
            int alturaObstaculo = obstaculo.getObstaculo().getAltura();

            boolean dentroX = x > posX && x < posX + comprimento;
            boolean dentroY = y > posY && y < posY + largura;
            boolean dentroZ = Math.abs(z) < Math.abs(alturaObstaculo);

            if (dentroX && dentroY && dentroZ) {
                return true; // Colisão detectada com um obstáculo
            }
        }

        return false; // Não há possibilidade de colisão
    }
}
