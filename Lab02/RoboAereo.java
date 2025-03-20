public class RoboAereo extends Robo {
    private int altitude;
    private int altitudeMaxima;

    public RoboAereo(String n, int x, int y, String dir, int z, int zmax) {
        super(n, x, y, dir);
        this.altitude = z;
        this.altitudeMaxima = zmax;
    }

    public void subir(int deltaZ) {
        this.altitude += deltaZ;
    }

    public void descer(int deltaZ) {
        this.altitude -= deltaZ;
    }

    public boolean moverAereo(int deltaX, int deltaY, int deltaZ) {
        if (this.altitude + deltaZ <= altitudeMaxima) {
            if (super.mover(deltaX, deltaY)) {
                this.altitude += deltaZ;
                return true;
            }
            else {
                return false;
            }
        }
        else {
            return false;
        }
    }

    public int exibirAltura() {
        return this.altitude;
    }
}
