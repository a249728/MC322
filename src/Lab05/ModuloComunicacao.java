public class ModuloComunicacao {
    private final Robo robo;

    public ModuloComunicacao(Robo robo) {
        this.robo = robo;
    }

    public void enviarMensagem(String mensagem) {
        // Exemplo: apenas loga a mensagem
        Logger.log("Robo '" + robo.getNome() + "' enviou mensagem: " + mensagem);
    }
}