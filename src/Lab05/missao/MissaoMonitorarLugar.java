package missao;
public class MissaoMonitorarLugar implements Missao {
    private int x;
    private int y;
    private int z;

    public MissaoMonitorarLugar(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    @Override
    public String executar(Robo r, Ambiente a) throws RoboDesligadoException, ForaDosLimitesException {
        a.dentroDosLimitesAereo(x, y, z);
        if (r.getEstado()) {
            if(a.acharRobo(x, y, z)!=null){
                Logger.log("Robo '" + r.getNome() + "' encontrou robo em (" + x + "," + y + "," + z + ")");
                return a.acharRobo(x, y, z).getNome() + " encontrado na posição " + x + ", " + y + ", " + z;
            }
            if(a.acharObstaculo(x, y, z)!=null){
                Logger.log("Robo '" + r.getNome() + "' encontrou obstáculo em (" + x + "," + y + "," + z + ")");
                return (a.acharObstaculo(x, y, z).getTipo()).toString() + " encontrado na posição " + x + ", " + y + ", " + z;
            }
            Logger.log("Robo '" + r.getNome() + "' não encontrou nada em (" + x + "," + y + "," + z + ")");
            return "Nada encontrado na posição " + x + ", " + y + ", " + z;
            
        } else {
            throw new RoboDesligadoException("O robô está desligado e não pode monitorar o lugar.");
        }
    }
    
}
