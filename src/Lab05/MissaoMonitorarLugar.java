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
    public void executar(Robo r, Ambiente a) throws RoboDesligadoException {
        if (r.getEstado()) {
            System.out.println("Monitorando o lugar: " + x + ", " + y + ", " + z);

            
        } else {
            throw new RoboDesligadoException("O robô está desligado e não pode monitorar o lugar.");
        }
    }
    
}
