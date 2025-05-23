public class ForaDosLimitesException extends Exception {
    // Excecao que indica que o robo esta fora dos limites do ambiente
    public ForaDosLimitesException(String msg){
        super(msg);
    }
}
