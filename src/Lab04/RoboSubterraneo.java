public class RoboSubterraneo extends RoboAereo {

    private int altitudeMinima;

    public RoboSubterraneo(String n, int x, int y, String dir, int z, int zmin) {
        // Metodo construtor da classe
        super(n, x, y, dir, z, 0);
        this.altitudeMinima = zmin;
    }

    public int retornarAltitudeMinima() {
        // Retorna a altitude minima do robo
        return this.altitudeMinima;
    }

    public void mudarAltitudeMinima(int zmin) {
        // Muda a altitude minima do robo
        this.altitudeMinima = zmin;
    }

    @Override
    public boolean mover(int deltaX, int deltaY, int deltaZ, Ambiente amb) throws RoboDesligadoException {
        // Sobrescreve o mover do aereo para incluir altura minima e considerar maxima como 0
        // RoboSubterraneo sempre vai estar fora do ambiente
        if (exibirAltura() + deltaZ >= altitudeMinima && exibirAltura() + deltaZ <= 0 && !identificarObstaculo(deltaX, deltaY, deltaZ, amb)) {
            if (this.mover(deltaX, deltaY, amb)) {
                if (deltaZ > 0) {
                    subir(deltaZ);
                } else {
                    descer(-deltaZ);
                }
                return true;
            } else {
                return false;
            }
        }
        return false;
    }

    @Override
    public char getRepresentacao() {
        // Retorna o caractere que representa a entidade visualmente
        return 'S';
    }
}