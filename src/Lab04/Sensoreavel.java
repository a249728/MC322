public interface Sensoreavel {
    String usarSensorIluminacao(int x, int y, int z, Ambiente amb) throws RoboDesligadoException;
    String usarSensorPressao(int x, int y, int z, Ambiente amb) throws RoboDesligadoException;
}
