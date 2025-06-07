public class Obstaculo implements Entidade {
    private TipoObstaculo obstaculo;
    private final int posicaoX;
    private final int posicaoY;

    public Obstaculo(TipoObstaculo tipo, int x, int y) {
        this.obstaculo = tipo;
        this.posicaoX = x;
        this.posicaoY = y;
    }

    public void removerObstaculo(Obstaculo obs, Ambiente amb) {
        // Remove o obstaculo
        amb.destruirObstaculo(obs);
    }

    public TipoObstaculo getObstaculo() {
        // Retorna o tipo do obstaculo
        return obstaculo;
    }

    @Override
    public int getX() {
        return posicaoX;
    }

    @Override
    public int getY() {
        return posicaoY;
    }

    @Override
    public int getZ() {
        return 0;
    }

    @Override
    public TipoEntidade getTipo() {
        return TipoEntidade.OBSTACULO;
    }

    @Override
    public String getDescricao() {
        return TipoEntidade.OBSTACULO.getDescricao();
    }

    @Override
    public char getRepresentacao() {
        // Retorna o caractere que representa a entidade visualmente
        switch (this.obstaculo) {
            case PEDRA: return 'P';
            case ARVORE: return 'V';
            case BURACO: return 'B';
            case LAGO: return '~';
            default: return '?';
        }
    }
}

