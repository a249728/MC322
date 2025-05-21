public class RoboGerador extends RoboAereo{
    
    private int filhos;

    public RoboGerador(String n, int x, int y, String dir, int z, int zmax){
        // Metodo construtor da classe
        super(n, x, y, dir, z, zmax);
        this.filhos=0;
    }

    public RoboTerrestre gerarRoboTerrestre(Ambiente amb, String n, int vmax){
        // Cria um filho RoboTerrestre
        this.filhos++;
        RoboTerrestre robo = new RoboTerrestre(n, exibirPosicao()[0], exibirPosicao()[1], this.retornarDirecao(), vmax);
        amb.adicionarRobo(robo);
        return robo;
    }

    public RoboAereo gerarRoboAereo(Ambiente amb, String n, int z, int zmax){
        // Cria um filho RoboAereo
        this.filhos++;
        RoboAereo robo = new RoboAereo(n, exibirPosicao()[0], exibirPosicao()[1], this.retornarDirecao(), z, zmax);
        amb.adicionarRobo(robo);
        return robo;
    }

    public RoboLaser gerarRoboLaser(Ambiente amb, String n, int vmax, int alc){
        // Cria um filho RoboLaser
        this.filhos++;
        RoboLaser robo = new RoboLaser(n, exibirPosicao()[0], exibirPosicao()[1], this.retornarDirecao(), vmax, alc);
        amb.adicionarRobo(robo);
        return robo;
    }

    public RoboSubterraneo gerarRoboSubterraneo(Ambiente amb, String n, int z, int zmin){
        // Cria um filho RoboSubterraneo
        this.filhos++;
        RoboSubterraneo robo = new RoboSubterraneo(n, exibirPosicao()[0], exibirPosicao()[1], this.retornarDirecao(), z, zmin);
        amb.adicionarRobo(robo);
        return robo;
    }

    public RoboGerador gerarRoboGerador(Ambiente amb, String n, int z, int zmax){
        // Cria um filho RoboGerador
        this.filhos++;
        RoboGerador robo = new RoboGerador(n, exibirPosicao()[0], exibirPosicao()[1], this.retornarDirecao(), z, zmax);
        amb.adicionarRobo(robo);
        return robo;
    }

    public RoboCorredor gerarRoboCorredor(Ambiente amb, String n, int vmax, int vmin){
        // Cria um filho RoboCorredor
        this.filhos++;
        RoboCorredor robo = new RoboCorredor(n, exibirPosicao()[0], exibirPosicao()[1], this.retornarDirecao(), vmax, vmin);
        amb.adicionarRobo(robo);
        return robo;
    }

    public int retornarFilhos(){
        // Retorna a quantidade de filhos do robo
        return this.filhos;
    }

    @Override
    public char getRepresentacao() {
        // Retorna o caractere que representa a entidade visualmente
        return 'G';
    }

}
