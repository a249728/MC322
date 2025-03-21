public class RoboAereo extends Robo {
    // Declara as propriedades da classe RoboAereo
    private int altitude;
    private int altitudeMaxima;

    public RoboAereo(String n, int x, int y, String dir, int z, int zmax) {
        // Metodo construtor da classe
        super(n, x, y, dir);
        this.altitude = z;
        this.altitudeMaxima = zmax;
    }

    public void subir(int deltaZ) {
        // Atualiza a altitude do robo adicionando a variacao de posicao desejada
        this.altitude += deltaZ;
    }

    public void descer(int deltaZ) {
        // Atualiza a altitude do robo adicionando a variacao de posicao desejada
        this.altitude -= deltaZ;
    }

    public boolean moverAereo(int deltaX, int deltaY, int deltaZ, Ambiente amb) {
        // Checa se o movimento nao ultrapassa a altitude maxima e retorna true ou false dependendo se o movimento foi bem sucedido ou nao
        if (this.altitude + deltaZ <= altitudeMaxima) {
            if (super.mover(deltaX, deltaY, amb)) {
                this.altitude += deltaZ;
                return true;
            }
            else {
                return false;
            }
        }
        return false;
    }

    public int exibirAltura() {
        // Retorna posicao z
        return this.altitude;
    }
}
