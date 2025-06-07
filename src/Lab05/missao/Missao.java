package missao;
import robo.*;
import exception.*;
import ambiente.*;
public interface Missao {
    String executar(Robo r, Ambiente a) throws RoboDesligadoException, ForaDosLimitesException, BateriaSensorException;
}