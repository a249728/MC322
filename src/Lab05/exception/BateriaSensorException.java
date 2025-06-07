package exception;
public class BateriaSensorException extends Exception {
    // Excecao que indica que a bateria do sensor esta baixa
    public BateriaSensorException(String msg) {
        super(msg);
    }
}