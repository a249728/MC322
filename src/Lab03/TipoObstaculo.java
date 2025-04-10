public enum TipoObstaculo {
    PEDRA (3, 3, 3),
    ARVORE (1, 1, 10),
    BURACO (5, 5, -5),
    LAGO (21, 21, -8);

    private final int comprimento;  
    private final int largura;
    private final int altura;

    public TipoObstaculo(int c, int l, int a) {
        this.comprimento = c;
        this.largura = l;
        this.altura = a;
    }

    public int getComprimento() {
        return comprimento; 
    }

    public int getLargura() {
        return largura; 
    }

    public int getAltura() {
        return altura; 
    }
}