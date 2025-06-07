package ambiente;
import robo.*;
import exception.*;
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
                for(int z=0; z<altura; z++){
                    mapa[x][y][z]=TipoEntidade.VAZIO;
                }
            }
        }
    }

    public boolean dentroDosLimites(int x, int y) throws ForaDosLimitesException {
        // Retorna um valor booleano correspondente a se as coordenadas fornecidas estao ou nao incluidas nos limites do ambiente
        if (x < 0 || x >= this.comprimento || y < 0 || y >= this.largura) {
            throw new ForaDosLimitesException("Coordenadas (" + x + ", " + y + ") fora dos limites do ambiente.");
        }
        return true;
    }

    public boolean dentroDosLimitesAereo(int x, int y, int z) throws ForaDosLimitesException {
        // Retorna um valor booleano correspondente a se as coordenadas fornecidas estao ou nao incluidas nos limites do ambiente (considerando eixo z)
        if (x < 0 || x >= this.comprimento || y < 0 || y >= this.largura || z < 0 || z >= this.altura) {
            throw new ForaDosLimitesException("Coordenadas (" + x + ", " + y + ", " + z + ") fora dos limites do ambiente.");
        }
        return true;
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

    public void adicionarRoboMapa(Robo robo) throws ForaDosLimitesException {
        if(dentroDosLimitesAereo(robo.getX(), robo.getY(), robo.getZ())){
            mapa[robo.getX()][robo.getY()][robo.getZ()] = TipoEntidade.ROBO;
        }
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

    public void adicionarRobo(Robo robo) throws ForaDosLimitesException {
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

    public Robo acharRobo(int x, int y, int z) {
        // Verifica se existe um robo na posicao (x,y,z) e retorna o robo
        for (Robo robo : robosAtivos) {
            if (robo.getX() == x && robo.getY() == y && robo.getZ() == z) {
                return robo;
            }
        }
        return null;
    }

    public Obstaculo acharObstaculo(int x, int y, int z) {
        // Verifica se existe um obstaculo na posicao (x,y,z) e retorna o obstaculo
        for (Obstaculo obs : obstaculos) {
            if (obs.getX() <= x && obs.getX() + obs.getObstaculo().getComprimento() > x && obs.getY() <= y && obs.getY() + obs.getObstaculo().getLargura() > y && obs.getZ() <= z && obs.getZ() + obs.getObstaculo().getAltura() > z) {
                return obs;
            }
        }
        return null;
    }

    public int getComprimento() {
        // Retorna o comprimento do ambiente
        return this.comprimento;
    }

    public int getLargura() {
        // Retorna a largura do ambiente
        return this.largura;
    }

    public int getAltura() {
        // Retorna a altura do ambiente
        return this.altura;
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

    public void removerRoboMapa(Robo robo) throws ForaDosLimitesException {
        if(dentroDosLimitesAereo(robo.getX(), robo.getY(), robo.getZ())){
            mapa[robo.getX()][robo.getY()][robo.getZ()] = TipoEntidade.VAZIO;
        }
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

    public void destruirRobo(Robo robo) throws ForaDosLimitesException {
        // Remove o Robo do ArrayList
        robosAtivos.remove(robo);
        removerRoboMapa(robo);
    }

    public void destruirObstaculo(Obstaculo obstaculo) {
        // Remove o Obstaculo do ArrayList
        obstaculos.remove(obstaculo);
        removerObstaculoMapa(obstaculo);
    }

    public void moverRoboMapa(Robo robo, int x, int y, int z) throws ForaDosLimitesException {
        adicionarRoboMapa(robo);
        mapa[robo.getX()-x][robo.getY()-y][robo.getZ()-z] = TipoEntidade.VAZIO;
    }

    public boolean estaOcupado(int x, int y, int z){
        if(mapa[x][y][z] == TipoEntidade.VAZIO){
            return false;
        }
        return true;
    }

    public char[][] visualizarAmbiente(){
        char[][] mapaXY = new char[comprimento][largura];
        for(int x=0; x<comprimento; x++){
            for(int y=0; y<largura; y++){
                mapaXY[x][y]='.';
                for(int z=altura-1; z>=0; z--){
                    if(mapa[x][y][z]!=TipoEntidade.VAZIO){
                        if(mapa[x][y][z]==TipoEntidade.OBSTACULO){
                            mapaXY[x][y]=acharObstaculo(x, y, z).getRepresentacao();
                        }
                        else if(mapa[x][y][z]==TipoEntidade.ROBO){
                            mapaXY[x][y]=acharRobo(x, y, z).getRepresentacao();
                        }
                        break;
                    }
                }
            }
        }
        return mapaXY;
    }
}