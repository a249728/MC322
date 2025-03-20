public class RoboAereo extends Robo {
    private int altitude;
    private int altitudeMaxima;

    public RoboAereo(String n, int x, int y, String dir, int z, int zmax) {
        super(n, x, y, dir);
        this.altitude = z;
        this.altitudeMaxima = zmax;
    }

    public void subir(int deltaZ) {
        if (altitude + deltaZ <= altitudeMaxima) {
            altitude += altitudeMaxima;
        }
    }

    public void descer(int deltaZ) {
        if (altitude - deltaZ >= 0) {
            altitude -= altitudeMaxima;
        }
    }

    public int exibirAltura() {
        return this.altitude;
    }
}
