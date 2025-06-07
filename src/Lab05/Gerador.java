public interface Gerador {
    RoboTerrestre gerarRoboTerrestre(Ambiente amb, String n, int vmax) throws RoboDesligadoException, ForaDosLimitesException;
    RoboAereo gerarRoboAereo(Ambiente amb, String n, int z, int zmax) throws RoboDesligadoException, ForaDosLimitesException;
    RoboLaser gerarRoboLaser(Ambiente amb, String n, int vmax, int alc) throws RoboDesligadoException, ForaDosLimitesException;
    RoboSubterraneo gerarRoboSubterraneo(Ambiente amb, String n, int z, int zmin) throws RoboDesligadoException, ForaDosLimitesException;
    RoboGerador gerarRoboGerador(Ambiente amb, String n, int z, int zmax) throws RoboDesligadoException, ForaDosLimitesException;
    RoboCorredor gerarRoboCorredor(Ambiente amb, String n, int vmax, int vmin) throws RoboDesligadoException, ForaDosLimitesException;
}
