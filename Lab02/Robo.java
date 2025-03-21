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
        if (this.posicaoX + deltaX >= 0 && this.posicaoY + deltaY >= 0) {
            if ((deltaX == 0 || deltaY == 0) && (identificarObstaculo(deltaX, deltaY, amb))) {
                return false;
            }
            this.posicaoX += deltaX;
            this.posicaoY += deltaY;
            return true;
        }
        return false;
    }

    public int[] exibirPosicao() {
        // Retorna o vetor de coordenadas xy do robo
        return new int[] { this.posicaoX, this.posicaoY };
    }

    public String retornarNome() {
        // Retorna o nome do robo
        return this.nome;
    }

    public boolean identificarObstaculo(int deltaX, int deltaY, Ambiente amb) {
        /* 
         *  Basicamente esse método assume que os robos (se precisarem e conseguirem) podem contornar obstáculos.
         * 
         *  Por exemplo: tenho um robo e quero move-lo em 2 e 2, mas tenho um outro robo logo a minha frente (e que portanto bloquearia meu robo se ele 
         *  tentasse andar para frente primeiro). Assumo que meu robo e inteligente para saber que consegue ir para o lado antes e contornar o robo bloqueador.
         *  
         *  No entanto existem casos em que contornar é impossível. Se algum dos meus deltas é 0 e o outro não é, tenho um problema caso haja qualquer
         *  tipo de obstaculo na minha direcao de movimento antes do meu ponto de destino. Esse método busca análisar esse caso, retornando true caso 
         *  encontre um obstaculo ou false caso o caminho esteja livre.
         * 
         *  PROBLEMA: se eu tiver delta x e delta y diferentes de 0, mas quando eu tento contornar acabo encontrando outro obstaculo
         *  Talvez fosse melhor só checar posição por posição do movimento (mas isso me soa altamente ineficiente)
         */
        Robo obstaculos[] = amb.robosAtivos;
        for (int i = 0; i < obstaculos.length; i++) {
            if (deltaX == 0 && deltaY != 0) {
                if (obstaculos[i].posicaoX == this.posicaoX) {
                    if ((obstaculos[i].posicaoY > this.posicaoY) && (obstaculos[i].posicaoY < this.posicaoY + deltaY)) {
                        return true;
                    }
                    else if ((obstaculos[i].posicaoY < this.posicaoY) && (obstaculos[i].posicaoY > this.posicaoY + deltaY)) {
                        return true;
                    }
                }
            }
            else if (deltaX != 0 && deltaY == 0) {
                if (obstaculos[i].posicaoY == this.posicaoY) {
                    if ((obstaculos[i].posicaoX > this.posicaoX) && (obstaculos[i].posicaoX < this.posicaoX + deltaX)) {
                        return true;
                    }
                    else if ((obstaculos[i].posicaoX < this.posicaoX) && (obstaculos[i].posicaoX > this.posicaoX + deltaY)) {
                        return true;
                    }
                } 
            }
        }
        return false;
    }
}
