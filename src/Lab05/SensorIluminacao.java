import java.util.ArrayList;

public class SensorIluminacao extends Sensor {
    public SensorIluminacao(double raio, int bat, Robo robo) {
        super(raio, bat, robo);
    }

    private double interseccaoRetaPlano(double x0, double x1, double xp){
        //encontra o t da parametrizacao x0+t*x1=xp
        return ((xp-x0)/x1);
    }

    private boolean interseccaoRetaPlanoDelimitado(double[] reta, int[] obj, int coord, int lado){
        //encontra a interseccao de uma reta de parametrizacao (x0+t*x1, y0+t*y1, z0+t*z1) a um plano(um dos lados do objeto)
        if(reta[2*coord+1]==0){
            //se a reta for paralela ao plano
            return false;
        }
        int coord1=(coord+1)%3;
        int coord2=(coord+2)%3;
        //coordenadas que não são a perpendicular ao plano
        double t=interseccaoRetaPlano(reta[2*coord], reta[2*coord+1], obj[2*coord+lado]);
        //encontra o t da parametrização 
        if(t<0){
            //se a intercecao não estiver na semireta
            return false;
        }
        double xyz1=reta[2*coord1]+t*reta[2*coord1+1];
        double xyz2=reta[2*coord2]+t*reta[2*coord2+1];
        //as outras coordenadas da interseccao
        if(obj[2*coord1] <= xyz1 && xyz1 <= obj[2*coord1+1] && obj[2*coord2] <= xyz2 && xyz2 <= obj[2*coord2+1]){
            //se estiver dentro dos limites do plano
            return true;
        }
        return false;
    }

    private boolean interseccaoRetaObjeto(double[] reta, int[] obj){
        //para cada um dos 6 planos do objeto, verifica se a reta intercecta com o plano delimitado
        for(int coord=0; coord<3; coord++){
            for(int lado=0; lado<2; lado++){
                if(interseccaoRetaPlanoDelimitado(reta, obj, coord, lado)){
                    return true;
                }
            }
        }
        return false;
    }

    public String monitorarIluminacao(int x, int y, int z, Ambiente amb) throws BateriaSensorException {
        //verifica se a posicao esta iluminada ou na sombra
        if (!this.monitorar(x, y, z)) {
            //nao consegue monitorar
            return "Nao foi possivel monitorar essa posicao";
        }

        if(z<0){
            //posicao abaixo da terra
            return "Posição monitorada abaixo da terra";
        }

        ArrayList<Obstaculo> obstaculos = amb.retornarObstaculos();
        String[] horaMinuto = amb.retornarHorario().split(":");
        double horario=Double.parseDouble(horaMinuto[0])+(Double.parseDouble(horaMinuto[1])/60);
        double theta=((horario-6)/12)*((double)Math.PI); //encontra que o eixo x faz com o sol baseado no horario
        double[] reta = {x, (double)Math.cos((double)theta), y, 0, z, (double)Math.sin((double)theta)}; //parametrizacao da reta

        if (sombraPorObstaculo(reta, obstaculos)) {
            return "Sombra";
        }

        return "Iluminado";
    }

    private boolean sombraPorObstaculo(double[] reta, ArrayList<Obstaculo> obstaculos) {
        //ve em todos os obstaculos se eles intercectam com a semirreta de luz
        for (Obstaculo obs : obstaculos) {
            int x = obs.getX();
            int y = obs.getY();
            int z = 0;
            TipoObstaculo tobs = obs.getObstaculo();
            int deltax = tobs.getComprimento();
            int deltay = tobs.getLargura();
            int deltaz = tobs.getAltura();
            int[] obj={x, x+deltax, y, y+deltay, z, z+deltaz};//cria objeto baseado nas dimensoes do objeto
            if (interseccaoRetaObjeto(reta, obj)) {//ve se o objeto intercecta a semireta de raio de luz
                return true;
            }
        }
        return false;
    }
}