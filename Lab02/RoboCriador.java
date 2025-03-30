public class RoboCriador extends RoboAereo{

    private int filhos;

    public RoboCriador(String n, int x, int y, String dir, int z, int zmax){
        // Metodo construtor da classe
        super(n, x, y, dir, z, zmax);
        this.filhos=0;
    }

    public Robo criarRobo(Ambiente amb, String n, String dir){
        this.filhos++;
        Robo robo = new Robo(n, exibirPosicao()[0], exibirPosicao()[1], dir);
        amb.adicionarRobo(robo);
        return robo;
    }

    public RoboTerrestre criarRoboTerrestre(Ambiente amb, String n, String dir, int vmax){
        this.filhos++;
        RoboTerrestre robo = new RoboTerrestre(n, exibirPosicao()[0], exibirPosicao()[1], dir, vmax);
        amb.adicionarRobo(robo);
        return robo;
    }

    public RoboAereo criarRoboAereo(Ambiente amb, String n, String dir, int z, int zmax){
        this.filhos++;
        RoboAereo robo = new RoboAereo(n, exibirPosicao()[0], exibirPosicao()[1], dir, z, zmax);
        amb.adicionarRobo(robo);
        return robo;
    }

    public RoboLaser criarRoboLaser(Ambiente amb, String n, String dir, int vmax, int alc){
        this.filhos++;
        RoboLaser robo = new RoboLaser(n, exibirPosicao()[0], exibirPosicao()[1], dir, vmax, alc);
        amb.adicionarRobo(robo);
        return robo;
    }

    public RoboSubterraneo criarRoboSubterraneo(Ambiente amb, String n, String dir, int z, int zmin){
        this.filhos++;
        RoboSubterraneo robo = new RoboSubterraneo(n, exibirPosicao()[0], exibirPosicao()[1], dir, z, zmin);
        amb.adicionarRobo(robo);
        return robo;
    }

    public RoboCriador criarRoboCriador(Ambiente amb, String n, String dir, int z, int zmax){
        this.filhos++;
        RoboCriador robo = new RoboCriador(n, exibirPosicao()[0], exibirPosicao()[1], dir, z, zmax);
        amb.adicionarRobo(robo);
        return robo;
    }

    public RoboCorredor criarRoboCorredor(Ambiente amb, String n, String dir, int vmax, int vmin){
        this.filhos++;
        RoboCorredor robo = new RoboCorredor(n, exibirPosicao()[0], exibirPosicao()[1], dir, vmax, vmin);
        amb.adicionarRobo(robo);
        return robo;
    }

    public int retornarFilhos(){
        return this.filhos;
    }

}
