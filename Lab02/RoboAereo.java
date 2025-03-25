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

    public boolean subir(int delta) {
        // Atualiza a altitude do robo adicionando a variacao de posicao desejada
        if (this.altitude + delta <= altitudeMaxima) {
            this.altitude += delta;
            return true;
        }
        return false;
    }

    public boolean descer(int delta) {
        // Atualiza a altitude do robo adicionando a variacao de posicao desejada
        if (this.altitude - delta >= 0) {
            this.altitude -= delta;
            return true;
        }
        return false;
    }

    /*

    public boolean identificarObstaculoAereo(int delta, String operacao, Ambiente amb) {
        Robo obstaculos[] = amb.robosAtivos;
        int coord[] = this.exibirPosicao();
        if (operacao == "Subir") {
            for (int i = 0; i < obstaculos.length; i++) {
                int coord_obs[] = obstaculos[i].exibirPosicao(); 
                if (coord_obs[0] == coord[0] && coord_obs[1] == coord[1] && obstaculos[i].altitude) {
                    return true;
                }
            }
        }
        
        return false;
    }
    */

    public int exibirAltura() {
        // Retorna posicao z
        return this.altitude;
    }
}
