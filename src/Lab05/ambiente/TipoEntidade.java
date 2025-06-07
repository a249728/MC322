package ambiente;

public enum TipoEntidade {
    // Define os tipos de entidades e suas dimensoes
    VAZIO ("O espaço nao esta ocupado por nenhum objeto"),
    ROBO ("O espaço esta ocupado por um robo"),
    OBSTACULO ("O espaço esta ocupado por um obstaculo"),
    DESCONHECIDO ("O espaço esta ocupado por um objeto desconhecido");

    private final String descricao;

    TipoEntidade(String d) {
        this.descricao = d;
    }

    public String getDescricao() {
        return descricao; 
    }
}