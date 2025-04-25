import java.util.ArrayList;

public class SensorIluminacao extends Sensor {
    public SensorIluminacao(double raio, int bat, Robo robo) {
        super(raio, bat, robo);
    }

    private double interseccaoRetaPlano(double x0, double x1, double xp){
        return ((xp-x0)/x1);
    }

    private boolean interseccaoRetaPlanoDelimitado(double[] reta, int[] obj, int coord, int lado){
        if(reta[2*coord+1]==0){
            return false;
        }
        int coord1=(coord+1)%3;
        int coord2=(coord+2)%3;
        double t=interseccaoRetaPlano(reta[2*coord], reta[2*coord+1], obj[2*coord+lado]);
        if(t<0){
            return false;
        }
        double xyz1=reta[2*coord1]+t*reta[2*coord1+1];
        double xyz2=reta[2*coord2]+t*reta[2*coord2+1];
        if(obj[2*coord1]<xyz1 && xyz1<obj[2*coord1+1] && obj[2*coord2]<xyz2 && xyz2<obj[2*coord2+1]){
            return true;
        }
        return false;
    }

    private boolean interseccaoRetaObjeto(double[] reta, int[] obj){
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

        if(z<0){
            return "Posição monitorada abaixo da terra";
        }

        //ArrayList<Robo> robos = amb.retornarRobosAtivos();
        ArrayList<Obstaculo> obstaculos = amb.retornarObstaculos();
        String[] horaMinuto = amb.retornarHorario().split(":");
        double horario=Double.parseDouble(horaMinuto[0])+(Double.parseDouble(horaMinuto[1])/60);
        double theta=((horario-6)/12)*((double)Math.PI);
        double[] reta = {x, (double)Math.cos((double)theta), y, 0, z, (double)Math.sin((double)theta)};

        if (sombraPorObstaculo(reta, obstaculos)) {
            return "Sombra";
        }

        return "Iluminado";
    }

    /*private boolean sombraPorRobo(double[] reta, ArrayList<Robo> robos) {
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
    }*/


    private boolean sombraPorObstaculo(double[] reta, ArrayList<Obstaculo> obstaculos) {
        for (Obstaculo obs : obstaculos) {
            int x = obs.getPosicaoX();
            int y = obs.getPosicaoY();
            int z = 0;
            TipoObstaculo tobs = obs.getObstaculo();
            int deltax = tobs.getComprimento();
            int deltay = tobs.getLargura();
            int deltaz = tobs.getAltura();
            int[] obj={x, x+deltax, y, y+deltay, z, z+deltaz};
            if (interseccaoRetaObjeto(reta, obj)) {
                return true;
            }
        }
        return false;
    }
}