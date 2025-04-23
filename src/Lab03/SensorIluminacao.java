import java.util.ArrayList;

public class SensorIluminacao extends Sensor {
    public SensorIluminacao(double raio, int bat, Robo robo) {
        super(raio, bat, robo);
    }

    private float interseccaoRetaPlano(float x0, float x1, float xp){
        return ((xp-x0)/x1);
    }

    private boolean interseccaoRetaPlanoDelimitado(float[] reta, int[] obj, int coord, int lado){
        if(reta[2*coord+1]==0){
            return false;
        }
        int coord1=(coord+1)%3;
        int coord2=(coord+2)%3;
        float t=interseccaoRetaPlano(reta[2*coord], reta[2*coord+1], obj[2*coord+lado]);
        if(t<0){
            return false;
        }
        float xyz1=reta[2*coord1]+t*reta[2*coord1+1];
        float xyz2=reta[2*coord2]+t*reta[2*coord2+1];
        if(obj[2*coord1]<xyz1 && xyz1<obj[2*coord1+1] && obj[2*coord2]<xyz2 && xyz2<obj[2*coord2+1]){
            return true;
        }
        return false;
    }

    private boolean interseccaoRetaObjeto(float[] reta, int[] obj){
        for(int coord=0; coord<3; coord++){
            for(int lado=0; lado<2; lado++){
                if(interseccaoRetaPlanoDelimitado(reta, obj, coord, lado)){
                    return true;
                }
            }
        }
        return false;
    }

    public String monitorarIluminacao(int x, int y, int z, Ambiente amb) {
        if (!this.monitorar(x, y, z)) {
            return "Nao foi possivel monitorar essa posicao";
        }

        ArrayList<Robo> robos = amb.retornarRobosAtivos();
        ArrayList<Obstaculo> obstaculos = amb.retornarObstaculos();
        String[] horaMinuto = amb.retornarHorario().split(":");
        float horario=Float.parseFloat(horaMinuto[0])+(Float.parseFloat(horaMinuto[1])/60);
        float theta=((horario-6)/12)*((float)Math.PI);
        float[] reta = {x, (float)Math.cos((float)theta), y, 0, z, (float)Math.sin((float)theta)};

        if (sombraPorRobo(reta, robos) || haSombraPorObstaculo(x, y, z, deslocamentoX, obstaculos)) {
            return "Sombra";
        }

        return "Iluminado";
    }

    private boolean sombraPorRobo(float[] reta, ArrayList<Robo> robos) {
        for (Robo robo : robos) {
            int[] pos = robo.exibirPosicao();
            int z=0;
            if(robo instanceof RoboAereo){
                z=((RoboAereo)robo).exibirAltura();
            }
            int[] obj={pos[0], pos[0]+1, pos[1], pos[1]+1, z, z+1}
            if (interseccaoRetaObjeto(reta, obj)) {
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
        return false;
    }
}