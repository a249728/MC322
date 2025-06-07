public class RoboCorredor extends RoboTerrestre implements Corredor {
    
    private int velocidadeMinima;

    public RoboCorredor(String n, int x, int y, String dir, int vmax, int vmin){
        // Metodo construtor da classe
        super(n, x, y, dir, vmax);
        this.velocidadeMinima = vmin;
    }

    public int retornarVelocidadeMinima(){
        // Retorna a velocidade minima do robo
        return this.velocidadeMinima;
    }

    public void mudarVelocidadeMinima(int vmin){
        // Muda a velocidade minima do robo
        this.velocidadeMinima = vmin;
    }

    public boolean correr(int delta, Ambiente amb) throws RoboDesligadoException, ColisaoException, VelocidadeMinimaException, VelocidadeMaximaException, ForaDosLimitesException {
        // Se a velocidade for menor que a minima retorna false
        if(delta < this.velocidadeMinima){
            throw new VelocidadeMinimaException("Nao foi possivel correr, a velocidade minima nao foi atingida");
        }
        
        // Converte a palavra da direcao em um vetor direcao
        int[] direcao = {0,0};
        if(this.retornarDirecao() == "Leste"){
            direcao[0]=1;
        }else if(this.retornarDirecao() == "Oeste"){
            direcao[0]=-1;
        }else if(this.retornarDirecao() == "Norte"){
            direcao[1]=1;
        }else if(this.retornarDirecao() == "Sul"){
            direcao[1]=-1;
        }else{
            return false;
        }

        // Verifica se tem algum obstaculo no caminho, se tiver retorna false, senao anda e retorna true
        for(int i=1; i<delta; i++){
            if(identificarObstaculo(i*direcao[0], i*direcao[1], amb)){
                return false;
            }
        }
        if(super.mover(delta*direcao[0], delta*direcao[1], amb)){
            amb.moverRoboMapa(this, delta*direcao[0], delta*direcao[1], 0);
            return true;
        }
        return false;
    }

    @Override
    public char getRepresentacao() {
        // Retorna o caractere que representa a entidade visualmente
        return 'C';
    }
}
