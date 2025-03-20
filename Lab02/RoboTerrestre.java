public class RoboTerrestre extends Robo {
    private int velocidadeMaxima;

    public RoboTerrestre(String n, int x, int y, String dir, int vmax) {
        super(n, x, y, dir);
        this.velocidadeMaxima = vmax;
    }

    public boolean moverTerrestre(int deltaX, int deltaY, int v) {
        if (v <= this.velocidadeMaxima) {
            if (super.mover(deltaX, deltaY)) {
                return true;
            }
            return false;
        }
        else {
            return false;
        }
    }
}
