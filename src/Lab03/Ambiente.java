public class Ambiente {
    // Declara as propriedades da classe Ambiente
    private int comprimento;
    private int largura;
    private int altura;
    private Robo[] robosAtivos = new Robo[0];

    public Ambiente(int c, int l, int a) {
        // Metodo construtor da classe
        this.comprimento = c;
        this.largura = l;
        this.altura = a;
    }

    public boolean dentroDosLimites(int x, int y) {
        // Retorna um valor booleano correspondente a se as coordenadas fornecidas estao ou nao incluidas nos limites do ambiente
        if (x < this.comprimento && y < this.largura) {
            return true;
        }
        return false;
    }

    public boolean dentroDosLimitesAereo(int x, int y, int z) {
        // Retorna um valor booleano correspondente a se as coordenadas fornecidas estao ou nao incluidas nos limites do ambiente (considerando eixo z)
        if (x >= 0 && x < this.comprimento && y >= 0 && y < this.largura && z >= 0 && z < this.altura) {
            return true;
        }
        return false;
    }

    public void adicionarRobo(Robo robo) {
        // Adiciona um Robo ativo ao array (o qual pode servir de obstaculo para o movimento de outros robos)
        Robo arr[] = this.robosAtivos;

        Robo novoArr[] = new Robo[arr.length + 1];

        for (int i = 0; i < arr.length; i++) {
            novoArr[i] = arr[i];
        }

        novoArr[arr.length] = robo;

        robosAtivos = novoArr;
    }

    public Robo acharRobo(int x, int y){
        Robo obstaculos[] = this.robosAtivos;
        for (int i = 0; i < obstaculos.length; i++) {
            if (obstaculos[i].exibirPosicao()[0] == x && obstaculos[i].exibirPosicao()[1] == y) {
                return obstaculos[i];
            }
        }
        return null;
    }

    public Robo[] retornarRobosAtivos() {
        return this.robosAtivos;
    }


    public void destruirRobo(Robo robo) {
        // Adiciona um Robo ativo ao array (o qual pode servir de obstaculo para o movimento de outros robos)
        Robo arr[] = this.robosAtivos;
        Robo novoArr[] = new Robo[arr.length - 1];

        for (int i = 0; i < arr.length - 1; i++) {
            if(robo == arr[i]){
                novoArr[i] = arr[arr.length - 1];
            }
            else{
               novoArr[i] = arr[i]; 
            }
            
        }

        robosAtivos = novoArr;
    }

    public boolean getIluminacao(Robo robo, int i, int j, String momento) {

    }

}
