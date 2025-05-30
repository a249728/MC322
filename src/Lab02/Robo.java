public class Robo {
    // Declara as propriedades da classe Robo
    private String nome;
    private int posicaoX;
    private int posicaoY;
    private String direcao;

    public Robo(String n, int x, int y, String dir) {
        // Metodo construtor da classe
        this.nome = n;
        this.posicaoX = x;
        this.posicaoY = y;
        this.direcao = dir;
    }

    public boolean mover(int deltaX, int deltaY, Ambiente amb) {
        // Checa se o movimento nao ultrapassa limites ou é bloqueado por obstaculos, movimenta e retorna true ou false dependendo se o movimento foi bem sucedido ou nao
        if (this.posicaoX + deltaX >= 0 && this.posicaoY + deltaY >= 0 && !identificarObstaculo(deltaX, deltaY, amb)) {
            this.posicaoX += deltaX;
            this.posicaoY += deltaY;
            return true;
        }
        return false;
    }

    public void mudarDirecao(String dir){
        // Muda a direcao do robo
        this.direcao=dir;
    }

    public String retornarDirecao() {
        // Retorna a direcao do robo
        return this.direcao;
    }

    public int[] exibirPosicao() {
        // Retorna o vetor de coordenadas xy do robo
        return new int[] { this.posicaoX, this.posicaoY };
    }

    public void mudarNome(String n){
        // Muda o nome do robo
        this.nome = n;
    }

    public String retornarNome() {
        // Retorna o nome do robo
        return this.nome;
    }

    public boolean identificarObstaculo(int deltaX, int deltaY, Ambiente amb) {
        //Checa se a posicao para a qual o robo quer mover ja esta ocupada por outro robo (obstaculo)
        Robo obstaculos[] = amb.retornarRobosAtivos();
        for (int i = 0; i < obstaculos.length; i++) {
            if (obstaculos[i].posicaoX == this.posicaoX + deltaX && obstaculos[i].posicaoY == this.posicaoY + deltaY && obstaculos[i] != this) {
                return true;
            }
        }
        return false;
    }
}
