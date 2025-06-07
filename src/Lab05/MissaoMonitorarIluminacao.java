public class MissaoMonitorarIluminacao implements Missao {
    private int x;
    private int y;
    private int z;

    public MissaoMonitorarIluminacao(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    @Override
    public String executar(Robo r, Ambiente a) throws RoboDesligadoException, ForaDosLimitesException, BateriaSensorException {
        a.dentroDosLimitesAereo(x, y, z);
        if (r.getEstado()) {
            if(r.getSensorIluminacao()!=null){
                if(r.getSensorIluminacao().monitorarIluminacao(x, y, z, a)=="Iluminado"){
                    Logger.log("Robo '" + r.getNome() + "' monitorou a iluminacao positiva em (" + x + "," + y + "," + z + ")");
                    return x + ", " + y + ", " + z + " está iluminado";
                }
                if(r.getSensorIluminacao().monitorarIluminacao(x, y, z, a)=="Sombra"){
                    Logger.log("Robo '" + r.getNome() + "' monitorou a iluminacao negativa em (" + x + "," + y + "," + z + ")");
                    return x + ", " + y + ", " + z + " não está iluminado";
                }
                return "O robô não consegue monitorar a posição " + x + ", " + y + ", " + z;
            }
            return "O robô não tem sensor de iluminação e não pode monitorar o lugar.";
        } else {
            throw new RoboDesligadoException("O robô está desligado e não pode monitorar o lugar.");
        }
    }
    
}