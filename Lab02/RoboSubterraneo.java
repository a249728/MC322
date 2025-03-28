public class RoboSubterraneo extends RoboAereo{
    
    private int altitudeMinima;

    public RoboSubterraneo(String n, int x, int y, String dir, int z, int zmin){
        super(n, x, y, dir, z, 0);
        this.altitudeMinima=zmin;
    }

    public boolean mover(int deltaX, int deltaY, int deltaZ, Ambiente amb) {
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