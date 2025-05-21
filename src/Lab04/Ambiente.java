import java.util.ArrayList;

public class Ambiente {
    // Declara as propriedades da classe Ambiente
    private int comprimento;
    private int largura;
    private int altura;
    private String horario;
    private ArrayList<Robo> robosAtivos = new ArrayList<>();
    private ArrayList<Obstaculo> obstaculos = new ArrayList<>();
    private ArrayList<Entidade> entidades = new ArrayList<>();
    private TipoEntidade[][][] mapa;


    public Ambiente(int c, int l, int a, String h) {
        // Metodo construtor da classe
        this.comprimento = c;
        this.largura = l;
        this.altura = a;
        this.horario = h;
        this.mapa = new TipoEntidade[c][l][a];
        inicializarMapa();
    }

    private void inicializarMapa(){
        for(int x=0; x<comprimento; x++){
            for(int y=0; y<largura; y++){
                for(int z=0; z<largura; z++){
                    mapa[x][y][z]=TipoEntidade.VAZIO;
                }
            }
        }
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
        if (obs.getX() >= x && obs.getX() < x + tipo.getComprimento() && obs.getY() >= y && obs.getY() < y + tipo.getLargura()) {
            return null;
        }
    }
    Obstaculo obs = new Obstaculo(tipo, x, y);
    adicionarObstaculo(obs);
    return obs;
    }

    public void adicionarEntidade(Entidade e){
        // Adiciona uma Entidade ao ArrayList
        entidades.add(e);
    }

    public void adicionarRoboMapa(Robo robo){
        mapa[robo.getX()][robo.getY()][robo.getZ()] = TipoEntidade.ROBO;
    }

    public void adicionarObstaculoMapa(Obstaculo obstaculo){
        for(int x=obstaculo.getX(); x<obstaculo.getX() + obstaculo.getObstaculo().getComprimento(); x++){
            for(int y=obstaculo.getY(); y<obstaculo.getY() + obstaculo.getObstaculo().getLargura(); y++){
                for(int z=obstaculo.getZ(); z<obstaculo.getZ() + obstaculo.getObstaculo().getAltura(); z++){
                    mapa[x][y][z] = TipoEntidade.OBSTACULO;
                }
            }
        }
    }

    public void adicionarRobo(Robo robo) {
        // Adiciona um Robo ativo ao ArrayList
        robosAtivos.add(robo);
        adicionarEntidade(robo);
        adicionarRoboMapa(robo);
    }

    public void adicionarObstaculo(Obstaculo obstaculo) {
        // Adiciona um Obstaculo ao ArrayList
        obstaculos.add(obstaculo);
        adicionarEntidade(obstaculo);
        adicionarObstaculoMapa(obstaculo);
    }

    public Robo acharRobo(int x, int y) {
        // Verifica se existe um robo na posicao (x,y) e retorna o robo
        for (Robo robo : robosAtivos) {
            if (robo.exibirPosicao()[0] == x && robo.exibirPosicao()[1] == y) {
                return robo;
            }
        }
        return null;
    }

    public ArrayList<Robo> retornarRobosAtivos() {
        // Retorna o ArrayList de Robos ativos
        return this.robosAtivos;
    }

    public ArrayList<Obstaculo> retornarObstaculos() {
        // Retorna o ArrayList de Obstaculos
        return this.obstaculos;
    }

    public String retornarHorario() {
        // Retorna a posicao do sol
        return this.horario;
    }

    public void removerRoboMapa(Robo robo){
        mapa[robo.getX()][robo.getY()][robo.getZ()] = TipoEntidade.VAZIO;
    }

    public void removerObstaculoMapa(Obstaculo obstaculo){
        for(int x=obstaculo.getX(); x<obstaculo.getX() + obstaculo.getObstaculo().getComprimento(); x++){
            for(int y=obstaculo.getY(); y<obstaculo.getY() + obstaculo.getObstaculo().getLargura(); y++){
                for(int z=obstaculo.getZ(); z<obstaculo.getZ() + obstaculo.getObstaculo().getAltura(); z++){
                    mapa[x][y][z] = TipoEntidade.VAZIO;
                }
            }
        }
    }

    public void destruirRobo(Robo robo) {
        // Remove o Robo do ArrayList
        robosAtivos.remove(robo);
        removerRoboMapa(robo);
    }

    public void destruirObstaculo(Obstaculo obstaculo) {
        // Remove o Obstaculo do ArrayList
        obstaculos.remove(obstaculo);
        removerObstaculoMapa(obstaculo);
    }

}
