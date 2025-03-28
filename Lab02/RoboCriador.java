public class RoboCriador extends RoboAereo{

    private int filhos;

    public RoboCriador(String n, int x, int y, String dir, int z, int zmax){
        super(n, x, y, dir, z, zmax);
        this.filhos=0;
    }

    public void criarRobo(Ambiente amb, String n, String dir){
        this.filhos++;
        Robo robo = new Robo(n, exibirPosicao()[0], exibirPosicao()[1], dir);
        amb.adicionarRobo(robo);
    }

    public void criarRoboTerrestre(Ambiente amb, String n, String dir, int vmax){
        this.filhos++;
        RoboTerrestre robo = new RoboTerrestre(n, exibirPosicao()[0], exibirPosicao()[1], dir, vmax);
        amb.adicionarRobo(robo);
    }

    public void criarRoboAereo(Ambiente amb, String n, String dir, int z, int zmax){
        this.filhos++;
        RoboAereo robo = new RoboAereo(n, exibirPosicao()[0], exibirPosicao()[1], dir, z, zmax);
        amb.adicionarRobo(robo);
    }

    public void criarRoboLaser(Ambiente amb, String n, String dir, int vmax, int alc){
        this.filhos++;
        RoboLaser robo = new RoboLaser(n, exibirPosicao()[0], exibirPosicao()[1], dir, vmax, alc);
        amb.adicionarRobo(robo);
    }

    public void criarRoboSubterraneo(Ambiente amb, String n, String dir, int z, int zmin){
        this.filhos++;
        RoboSubterraneo robo = new RoboSubterraneo(n, exibirPosicao()[0], exibirPosicao()[1], dir, z, zmin);
        amb.adicionarRobo(robo);
    }

    public void criarRoboCriador(Ambiente amb, String n, String dir, int z, int zmax){
        this.filhos++;
        RoboCriador robo = new RoboCriador(n, exibirPosicao()[0], exibirPosicao()[1], dir, z, zmax);
        amb.adicionarRobo(robo);
    }

    public int exibirFilhos(){
        return this.filhos;
    }

}
