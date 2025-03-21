public class Ambiente {
    // Declara as propriedades da classe Ambiente
    private int comprimento;
    private int largura;
    private int altura;
    public Robo[] robosAtivos = new Robo[0];

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
        if (x < this.comprimento && y < this.largura && z < this.altura) {
            return true;
        }
        return false;
    }

    public void adicionarRobo(Robo arr[], Robo robo) {
        // Adiciona um Robo ativo ao array (o qual pode servir de obstaculo para o movimento de outros robos)
        Robo novoArr[] = new Robo[arr.length + 1];

        for (int i = 0; i < arr.length; i++) {
            novoArr[i] = arr[i];
        }

        novoArr[arr.length] = robo;

        robosAtivos = novoArr;
    }
}
