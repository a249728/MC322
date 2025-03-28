public class RoboTerrestre extends Robo {
    private int velocidadeMaxima;

    public RoboTerrestre(String n, int x, int y, String dir, int vmax) {
        // Metodo construtor da classe
        super(n, x, y, dir);
        this.velocidadeMaxima = vmax;
    }

    public boolean mover(int deltaX, int deltaY, int v, Ambiente amb) {
        // Checa se o movimento nao ultrapassa a velocidade maxima e retorna true ou false dependendo se o movimento foi bem sucedido ou nao
        if (v <= this.velocidadeMaxima) {
            if (super.mover(deltaX, deltaY, amb)) {
                return true;
            }
            return false;
        }
        return false;
    }
}
