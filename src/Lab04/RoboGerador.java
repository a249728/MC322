public class RoboGerador extends RoboAereo implements Gerador, Comunicavel {
    
    private int filhos;

    public RoboGerador(String n, int x, int y, String dir, int z, int zmax){
        super(n, x, y, dir, z, zmax);
        this.filhos=0;
    }

    public RoboTerrestre gerarRoboTerrestre(Ambiente amb, String n, int vmax) throws RoboDesligadoException {
        if (!this.getEstado()) throw new RoboDesligadoException("Robo desligado: não é possível gerar RoboTerrestre.");
        this.filhos++;
        RoboTerrestre robo = new RoboTerrestre(n, exibirPosicao()[0], exibirPosicao()[1], this.retornarDirecao(), vmax);
        amb.adicionarRobo(robo);
        return robo;
    }

    public RoboAereo gerarRoboAereo(Ambiente amb, String n, int z, int zmax) throws RoboDesligadoException {
        if (!this.getEstado()) throw new RoboDesligadoException("Robo desligado: não é possível gerar RoboAereo.");
        this.filhos++;
        RoboAereo robo = new RoboAereo(n, exibirPosicao()[0], exibirPosicao()[1], this.retornarDirecao(), z, zmax);
        amb.adicionarRobo(robo);
        return robo;
    }

    public RoboLaser gerarRoboLaser(Ambiente amb, String n, int vmax, int alc) throws RoboDesligadoException {
        if (!this.getEstado()) throw new RoboDesligadoException("Robo desligado: não é possível gerar RoboLaser.");
        this.filhos++;
        RoboLaser robo = new RoboLaser(n, exibirPosicao()[0], exibirPosicao()[1], this.retornarDirecao(), vmax, alc);
        amb.adicionarRobo(robo);
        return robo;
    }

    public RoboSubterraneo gerarRoboSubterraneo(Ambiente amb, String n, int z, int zmin) throws RoboDesligadoException {
        if (!this.getEstado()) throw new RoboDesligadoException("Robo desligado: não é possível gerar RoboSubterraneo.");
        this.filhos++;
        RoboSubterraneo robo = new RoboSubterraneo(n, exibirPosicao()[0], exibirPosicao()[1], this.retornarDirecao(), z, zmin);
        amb.adicionarRobo(robo);
        return robo;
    }

    public RoboGerador gerarRoboGerador(Ambiente amb, String n, int z, int zmax) throws RoboDesligadoException {
        if (!this.getEstado()) throw new RoboDesligadoException("Robo desligado: não é possível gerar RoboGerador.");
        this.filhos++;
        RoboGerador robo = new RoboGerador(n, exibirPosicao()[0], exibirPosicao()[1], this.retornarDirecao(), z, zmax);
        amb.adicionarRobo(robo);
        return robo;
    }

    public RoboCorredor gerarRoboCorredor(Ambiente amb, String n, int vmax, int vmin) throws RoboDesligadoException {
        if (!this.getEstado()) throw new RoboDesligadoException("Robo desligado: não é possível gerar RoboCorredor.");
        this.filhos++;
        RoboCorredor robo = new RoboCorredor(n, exibirPosicao()[0], exibirPosicao()[1], this.retornarDirecao(), vmax, vmin);
        amb.adicionarRobo(robo);
        return robo;
    }

    public int retornarFilhos(){
        return this.filhos;
    }

    public void enviarMensagem(Comunicavel destinatario, String mensagem) throws ErroComunicacaoException, RoboDesligadoException {
        if (!this.getEstado()) {
            throw new RoboDesligadoException("Robo desligado: não é possível enviar mensagem");
        }
        if (destinatario instanceof RoboGerador) {
            RoboGerador rg = (RoboGerador) destinatario;
            if (!rg.getEstado()) {
                throw new RoboDesligadoException("Destinatário desligado: não é possível receber mensagem");
            }
            rg.receberMensagem(mensagem);
        } else {
            throw new ErroComunicacaoException("Erro de comunicação: destinatário não é um robo comunicador");
        }
    }

    public String receberMensagem(String mensagem) {
        String resultado = "Mensagem recebida: " + mensagem;
        CentralComunicacao.registrarMensagem(mensagem);
        return resultado;
    }

    @Override
    public char getRepresentacao() {
        return 'G';
    }
}