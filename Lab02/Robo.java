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

    public boolean mover(int delta, Ambiente amb, String nova_dir) {
        this.direcao = nova_dir;
        if (delta < 0) {
            delta = -delta;
            if (this.direcao == "Norte") {
                this.direcao = "Sul";
            }
            else if (this.direcao == "Sul") {
                this.direcao = "Norte";
            }
            else if (this.direcao == "Leste") {
                this.direcao = "Oeste";
            }
            else {
                this.direcao = "Leste";
            }
        }
        // Checa se o movimento nao ultrapassa limites ou é bloqueado por obstaculos, movimenta e retorna true ou false dependendo se o movimento foi bem sucedido ou nao
        if (!identificarObstaculo(delta, amb)) {
            if (this.direcao == "Norte") {
                this.posicaoY += delta;
                return true;
            }
            else if (this.direcao == "Sul" && this.posicaoY - delta >= 0) {
                this.posicaoY -= delta;
                return true;
            }
            else if (this.direcao == "Leste") {
                this.posicaoX += delta;
                return true;
            }
            else if (this.direcao == "Oeste" && this.posicaoX - delta >= 0) {
                this.posicaoX -= delta;
                return true;
            }
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

    public boolean identificarObstaculo(int delta, Ambiente amb) {
        //Checa se a posicao para a qual o robo quer mover ja esta ocupada por outro robo (obstaculo)
        Robo obstaculos[] = amb.robosAtivos;
        if (this.direcao == "Norte") {
            for (int i = 0; i < obstaculos.length; i++) {
                if (obstaculos[i].posicaoX == this.posicaoX && obstaculos[i].posicaoY > this.posicaoY && obstaculos[i].posicaoY <= this.posicaoY + delta) {
                    return true;
                }
            }
        }
        else if (this.direcao == "Sul") {
            for (int i = 0; i < obstaculos.length; i++) {
                if (obstaculos[i].posicaoX == this.posicaoX && obstaculos[i].posicaoY < this.posicaoY && obstaculos[i].posicaoY >= this.posicaoY + delta) {
                    return true;
                }
            }
        }
        else if (this.direcao == "Leste") {
            for (int i = 0; i < obstaculos.length; i++) {
                if (obstaculos[i].posicaoY == this.posicaoY && obstaculos[i].posicaoX > this.posicaoX && obstaculos[i].posicaoX <= this.posicaoX + delta) {
                    return true;
                }
            } 
        }
        else if (this.direcao == "Oeste") {
            for (int i = 0; i < obstaculos.length; i++) {
                if (obstaculos[i].posicaoY == this.posicaoY && obstaculos[i].posicaoX < this.posicaoX && obstaculos[i].posicaoX >= this.posicaoX + delta) {
                    return true;
                }
            }
        }
        return false;
    }
}
