public class RoboTerrestre extends Robo {
    private int velocidadeMaxima;

    public RoboTerrestre(String n, int x, int y, String dir, int vmax) {
        // Metodo construtor da classe
        super(n, x, y, dir);
        this.velocidadeMaxima = vmax;
    }

    public int retornarVelocidadeMaxima(){
        // Retorna a velocidade maxima do robo
        return this.velocidadeMaxima;
    }

    public void mudarVelocidadeMaxima(int vmax){
        // Muda a velocidade maxima do robo
        this.velocidadeMaxima = vmax;
    }

    public boolean mover(int deltaX, int deltaY, Ambiente amb) {
        // Sobrescreve o mover de Robo e checa se o movimento nao ultrapassa a velocidade maxima e retorna true ou false dependendo se o movimento foi bem sucedido ou nao
        // Os robos fazem 1 acao por unidade de tempo, assim, a velocidade em bloco por unidade de tempo tem que ser maior que a distancia
        if (Math.abs(deltaX) + Math.abs(deltaY) <= this.velocidadeMaxima) {
            if (super.mover(deltaX, deltaY, amb)) {
                return true;
            }
            return false;
        }
        return false;
    }
}
