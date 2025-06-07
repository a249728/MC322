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
                return a.acharRobo(x, y, z).getNome() + " encontrado na posição " + x + ", " + y + ", " + z;
            }
            if(a.acharObstaculo(x, y, z)!=null){
                return (a.acharObstaculo(x, y, z).getTipo()).toString() + " encontrado na posição " + x + ", " + y + ", " + z;
            }
            return "Nada encontrado na posição " + x + ", " + y + ", " + z;
            
        } else {
            throw new RoboDesligadoException("O robô está desligado e não pode monitorar o lugar.");
        }
    }
    
}
