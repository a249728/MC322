package sensores;
import ambiente.*;
import exception.*;

public interface Sensoreavel {
    String usarSensorIluminacao(int x, int y, int z, Ambiente amb) throws RoboDesligadoException, BateriaSensorException;
    String usarSensorPressao(int x, int y, int z, Ambiente amb) throws RoboDesligadoException, BateriaSensorException;
}
