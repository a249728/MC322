import java.util.ArrayList;

public class Ambiente {
    // Declara as propriedades da classe Ambiente
    private int comprimento;
    private int largura;
    private int altura;
    private String horario;
    private ArrayList<Robo> robosAtivos = new ArrayList<>();
    private ArrayList<Obstaculo> obstaculos = new ArrayList<>();

    public Ambiente(int c, int l, int a, String h) {
        // Metodo construtor da classe
        this.comprimento = c;
        this.largura = l;
        this.altura = a;
        this.horario = h;
    }

    public boolean dentroDosLimites(int x, int y) {
        // Retorna um valor booleano correspondente a se as coordenadas fornecidas estao ou nao incluidas nos limites do ambiente
        return x < this.comprimento && y < this.largura;
    }

    public boolean dentroDosLimitesAereo(int x, int y, int z) {
        // Retorna um valor booleano correspondente a se as coordenadas fornecidas estao ou nao incluidas nos limites do ambiente (considerando eixo z)
        return x >= 0 && x < this.comprimento && y >= 0 && y < this.largura && z >= 0 && z < this.altura;
    }


public Obstaculo criarObstaculo(TipoObstaculo tipo, int x, int y) {
    // checa se ja nao existe um obstaculo ou um robo naquela posicao
    ArrayList<Robo> robos = retornarRobosAtivos();
    ArrayList<Obstaculo> obstaculos = retornarObstaculos();
    for (Robo robo : robos) {
        if (robo.exibirPosicao()[0] >= x && robo.exibirPosicao()[0] < x + tipo.getComprimento() && robo.exibirPosicao()[1] >= y && robo.exibirPosicao()[1] < y + tipo.getLargura()) {
            return null;
        }
    }
    for (Obstaculo obs : obstaculos) {
        if (obs.getPosicaoX() >= x && obs.getPosicaoX() < x + tipo.getComprimento() && obs.getPosicaoY() >= y && obs.getPosicaoY() < y + tipo.getLargura()) {
            return null;
        }
    }
    Obstaculo obs = new Obstaculo(tipo, x, y);
    adicionarObstaculo(obs);
    return obs;
}

    public void adicionarRobo(Robo robo) {
        // Adiciona um Robo ativo ao ArrayList
        robosAtivos.add(robo);
    }

    public void adicionarObstaculo(Obstaculo obstaculo) {
        // Adiciona um Obstaculo ao ArrayList
        obstaculos.add(obstaculo);
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

    public ArrayList<Obstaculo> retornarObstaculos() {
        return this.obstaculos;
    }

    public String retornarHorario() {
        // Retorna a posicao do sol
        return this.horario;
    }

    public void destruirRobo(Robo robo) {
        // Remove o Robo do ArrayList
        robosAtivos.remove(robo);
    }

    public void destruirObstaculo(Obstaculo obstaculo) {
        // Remove o Obstaculo do ArrayList
        obstaculos.remove(obstaculo);
    }

}
