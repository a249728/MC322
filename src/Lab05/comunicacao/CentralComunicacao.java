package comunicacao;

import java.util.ArrayList;

public class CentralComunicacao {
    private static ArrayList<String> mensagens;

    public CentralComunicacao() {
        mensagens = new ArrayList<>();
    }

    public static void registrarMensagem(String mensagem) {
        mensagens.add(mensagem);
    }

    public static ArrayList<String> getMensagens() {
        return mensagens;
    }
}
