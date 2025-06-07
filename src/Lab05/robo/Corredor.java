package robo;
import ambiente.*;
import exception.*;
public interface Corredor {
    boolean correr(int delta, Ambiente amb) throws RoboDesligadoException, ColisaoException, VelocidadeMinimaException, VelocidadeMaximaException, ForaDosLimitesException;
}
