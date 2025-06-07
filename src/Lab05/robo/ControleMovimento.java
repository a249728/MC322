package robo;
public class ControleMovimento {
    private final Robo robo;

    public ControleMovimento(Robo robo) {
        this.robo = robo;
    }

    public boolean mover(int dx, int dy, Ambiente amb) throws RoboDesligadoException, ColisaoException, ForaDosLimitesException, VelocidadeMaximaException {
        return robo.mover(dx, dy, amb);
    }
}