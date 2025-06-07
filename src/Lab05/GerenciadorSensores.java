public class GerenciadorSensores {
    private final Robo robo;

    public GerenciadorSensores(Robo robo) {
        this.robo = robo;
    }

    public String usarSensorIluminacao(int x, int y, int z, Ambiente amb) throws RoboDesligadoException, BateriaSensorException {
        return robo.usarSensorIluminacao(x, y, z, amb);
    }

    public String usarSensorPressao(int x, int y, int z, Ambiente amb) throws RoboDesligadoException, BateriaSensorException {
        return robo.usarSensorPressao(x, y, z, amb);
    }
}