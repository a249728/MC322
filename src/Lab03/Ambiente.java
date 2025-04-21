import java.util.ArrayList;

public class Ambiente {
    // Declara as propriedades da classe Ambiente
    private int comprimento;
    private int largura;
    private int altura;
    private final String posSol;
    private ArrayList<Robo> robosAtivos = new ArrayList<>();

    public Ambiente(int c, int l, int a, String p) {
        // Metodo construtor da classe
        this.comprimento = c;
        this.largura = l;
        this.altura = a;
        this.posSol = p;
    }

    public boolean dentroDosLimites(int x, int y) {
        // Retorna um valor booleano correspondente a se as coordenadas fornecidas estao ou nao incluidas nos limites do ambiente
        return x < this.comprimento && y < this.largura;
    }

    public boolean dentroDosLimitesAereo(int x, int y, int z) {
        // Retorna um valor booleano correspondente a se as coordenadas fornecidas estao ou nao incluidas nos limites do ambiente (considerando eixo z)
        return x >= 0 && x < this.comprimento && y >= 0 && y < this.largura && z >= 0 && z < this.altura;
    }

    public void adicionarRobo(Robo robo) {
        // Adiciona um Robo ativo ao ArrayList
        robosAtivos.add(robo);
    }

    public Robo acharRobo(int x, int y) {
        for (Robo robo : robosAtivos) {
            if (robo.exibirPosicao()[0] == x && robo.exibirPosicao()[1] == y) {
                return robo;
            }
        }
        return null;
    }

    public ArrayList<Robo> retornarRobosAtivos() {
        return this.robosAtivos;
    }

    public String retornarPosSol() {
        // Retorna a posicao do sol
        return this.posSol;
    }

    public void destruirRobo(Robo robo) {
        // Remove o Robo do ArrayList
        robosAtivos.remove(robo);
    }

}
