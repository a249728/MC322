public interface Laser {
    void mudarAlcanceLaser(int alc);
    int dispararLaser(Ambiente amb) throws RoboDesligadoException;
}
