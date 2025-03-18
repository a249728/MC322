public class Ambiente {
    // Declara as propriedades da classe Ambiente
    private int largura;
    private int altura;

    public Ambiente(int l, int a) {
        // Metodo construtor da classe
        this.largura = l;
        this.altura = a;
    }

    public boolean dentroDosLimites(int x, int y) {
        // Retorna um valor booleano correspondente a se as coordenadas fornecidas estao ou nao incluidas nos limites do ambiente
        if (x < this.largura && y < this.altura) {
            return true;
        }
        return false;
    }
}
