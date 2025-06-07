public interface Comunicavel {
    void enviarMensagem(Comunicavel destinatario, String mensagem) throws ErroComunicacaoException, RoboDesligadoException;
    String receberMensagem(String mensagem);
}
