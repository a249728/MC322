public interface Corredor {
    boolean correr(int delta, Ambiente amb) throws RoboDesligadoException, ColisaoException;
}
