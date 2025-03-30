public class RoboSubterraneo extends RoboAereo{
    
    private int altitudeMinima;

    public RoboSubterraneo(String n, int x, int y, String dir, int z, int zmin){
        // Metodo construtor da classe
        super(n, x, y, dir, z, 0);
        this.altitudeMinima=zmin;
    }

    public int retornarAltitudeMinima(){
        // Retorna a altitude minima do robo
        return this.altitudeMinima;
    }

    public void mudarAltitudeMinima(int zmin){
        // Muda a altitude minima do robo
        this.altitudeMinima = zmin;
    }

    @Override public boolean mover(int deltaX, int deltaY, int deltaZ, Ambiente amb) {
        if (exibirAltura() + deltaZ >= altitudeMinima && exibirAltura() + deltaZ <= 0) {
            if (super.mover(deltaX, deltaY, amb)) {
                if(deltaZ > 0){
                    subir(deltaZ);
                }
                else{
                    descer(deltaZ);
                }
                return true;
            }
            else {
                return false;
            }
        }
        return false;
    }

}