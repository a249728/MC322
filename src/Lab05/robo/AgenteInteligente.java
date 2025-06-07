package robo;
public abstract class AgenteInteligente extends Robo {
    protected Missao missao;
    
    public AgenteInteligente(String n, int x, int y, String dir){
        super(n, x, y, dir);
    }

    public void definirMissao(Missao m) {
        this.missao = m;
    }

    public boolean temMissao() {
        return this.missao != null;
    }

    public abstract void executarMissao(Ambiente a) throws RoboDesligadoException, ForaDosLimitesException, BateriaSensorException;
}
