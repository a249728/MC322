public interface Gerador {
    RoboTerrestre gerarRoboTerrestre(Ambiente amb, String n, int vmax) throws RoboDesligadoException;
    RoboAereo gerarRoboAereo(Ambiente amb, String n, int z, int zmax) throws RoboDesligadoException;
    RoboLaser gerarRoboLaser(Ambiente amb, String n, int vmax, int alc) throws RoboDesligadoException;
    RoboSubterraneo gerarRoboSubterraneo(Ambiente amb, String n, int z, int zmin) throws RoboDesligadoException;
    RoboGerador gerarRoboGerador(Ambiente amb, String n, int z, int zmax) throws RoboDesligadoException;
    RoboCorredor gerarRoboCorredor(Ambiente amb, String n, int vmax, int vmin) throws RoboDesligadoException;
}
