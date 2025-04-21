import java.util.ArrayList;

public class SensorIluminacao extends Sensor {
    public SensorIluminacao(double raio, int bat, Robo robo) {
        super(raio, bat, robo);
    }

    public String monitorarIluminacao(int x, int y, int z, Ambiente amb) {
        if (!this.monitorar(x, y, z)) {
            return "Nao foi possivel monitorar essa posicao";
        }

        ArrayList<Robo> robos = amb.retornarRobosAtivos();
        ArrayList<Obstaculo> obstaculos = amb.retornarObstaculos();
        String direcaoSol = amb.retornarPosSol();

        int deslocamentoX = 0;
        if (direcaoSol.equals("Leste")) {
            deslocamentoX = 1;
        } else if (direcaoSol.equals("Oeste")) {
            deslocamentoX = -1;
        } else {
            return "A direcao indicada do sol e invalida";
        }

        if (haSombraPorRobo(x, y, z, deslocamentoX, robos) || haSombraPorObstaculo(x, y, z, deslocamentoX, obstaculos)) {
            return "Sombra";
        }

        return "Iluminado";
    }

    private boolean haSombraPorRobo(int x, int y, int z, int deslocamentoX, ArrayList<Robo> robos) {
        for (Robo rob : robos) {
            int[] posicaoRobo = rob.exibirPosicao();
            int altura = 0;
            if (rob.getClass() == RoboAereo.class) {
                RoboAereo roboAereo = (RoboAereo) rob;
                altura = roboAereo.exibirAltura();
            }
            if (posicaoRobo[0] == x + deslocamentoX && posicaoRobo[1] == y && altura == z) {
                return true;
            }
        }
        return false;
    }

    private boolean haSombraPorObstaculo(int x, int y, int z, int deslocamentoX, ArrayList<Obstaculo> obstaculos) {
        for (Obstaculo obstaculo : obstaculos) {
            boolean aoLadoX = x + deslocamentoX > obstaculo.getPosicaoX() 
                    && x + deslocamentoX < obstaculo.getPosicaoX() + obstaculo.getObstaculo().getLargura();
            boolean dentroY = y > obstaculo.getPosicaoY() 
                    && y < obstaculo.getPosicaoY() + obstaculo.getObstaculo().getLargura();
            boolean abaixoZ = z < obstaculo.getObstaculo().getAltura() && obstaculo.getObstaculo().getAltura() > 0;

            if (aoLadoX && dentroY && abaixoZ) {
                return true;
            }
                return true;
            }
        }
        return false;
    }
}