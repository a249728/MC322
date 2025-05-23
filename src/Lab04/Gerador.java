public interface Gerador {
    RoboTerrestre gerarRoboTerrestre(Ambiente amb, String n, int vmax);
    RoboAereo gerarRoboAereo(Ambiente amb, String n, int z, int zmax);
    RoboLaser gerarRoboLaser(Ambiente amb, String n, int vmax, int alc);
    RoboSubterraneo gerarRoboSubterraneo(Ambiente amb, String n, int z, int zmin);
    RoboGerador gerarRoboGerador(Ambiente amb, String n, int z, int zmax);
    RoboCorredor gerarRoboCorredor(Ambiente amb, String n, int vmax, int vmin);
}
