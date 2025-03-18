public class RoboTerrestre extends Robo{
    private int velocidadeMaxima;

    public RoboTerrestre(String n, int x, int y, String dir, int vmax) {
        super(n, x, y, dir);
        this.velocidadeMaxima=vmax;
    }

    public void mover(int deltaX, int deltaY, int v){
        if(v <= velocidadeMaxima){
            super.mover(deltaX, deltaY);
        }
    }
}
