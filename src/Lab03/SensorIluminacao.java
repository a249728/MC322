import java.util.ArrayList;

public class SensorIluminacao extends Sensor {
    public SensorIluminacao(double raio, int bat, Robo robo) {
        super(raio, bat, robo);
    }

    private float interseccaoRetaPlano(float x0, float x1, float xp){
        return ((xp-x0)/x1);
    }

    private boolean interseccaoRetaPlanoDelimitado(float x0, float x1, float y0, float y1, float z0, float z1, float xp, float yi, float yf, float zi, float zf){
        if(x1==0){
            return false;
        }
        float t=interseccaoRetaPlano(x0, x1, xp);
        float y=y0+t*y1;
        float z=z0+t*z1;
        if(yi<y && y<yf && zi<z && z<zf){
            return true;
        }
        return false;
    }

    private boolean interseccaoRetaObjeto(float x0, float x1, float y0, float y1, float z0, float z1, float xi, float xf, float yi, float yf, float zi, float zf){
        if(interseccaoRetaPlanoDelimitado(x0, x1, y0, y1, z0, z1, xi, yi, yf, zi, zf)){
            return true;
        }if(interseccaoRetaPlanoDelimitado(x0, x1, y0, y1, z0, z1, xf, yi, yf, zi, zf)){
            return true;
        }if(interseccaoRetaPlanoDelimitado(x0, x1, y0, y1, z0, z1, yi, xi, xf, zi, zf)){
            return true;
        }if(interseccaoRetaPlanoDelimitado(x0, x1, y0, y1, z0, z1, yf, xi, xf, zi, zf)){
            return true;
        }if(interseccaoRetaPlanoDelimitado(x0, x1, y0, y1, z0, z1, zi, yi, yf, xi, xf)){
            return true;
        }if(interseccaoRetaPlanoDelimitado(x0, x1, y0, y1, z0, z1, zf, yi, yf, xi, xf)){
            return true;
        }
        return false;
    }

    public String monitorarIluminacao(int x, int y, int z, Ambiente amb) {
        if (!this.monitorar(x, y, z)) {
            return "Nao foi possivel monitorar essa posicao";
        }

        ArrayList<Robo> robos = amb.retornarRobosAtivos();
        ArrayList<Obstaculo> obstaculos = amb.retornarObstaculos();
        String horario = amb.retornarHorario();

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
        return false;
    }
}