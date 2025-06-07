package robo;
import ambiente.*;
import exception.*;
public interface Laser {
    void mudarAlcanceLaser(int alc);
    int dispararLaser(Ambiente amb) throws RoboDesligadoException, ColisaoException, ForaDosLimitesException;
}
