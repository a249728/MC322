public class RoboCorredor extends RoboTerrestre{
    
    private int velocidadeMinima;

    public RoboCorredor(String n, int x, int y, String dir, int vmax, int vmin){
        super(n, x, y, dir, vmax);
        this.velocidadeMinima = vmin;
    }

    public int retornarVelocidadeMinima(){
        return this.velocidadeMinima;
    }

    public void mudarVelocidadeMinima(int vmin){
        this.velocidadeMinima = vmin;
    }

    public boolean mover(int delta, Ambiente amb){
        
        if(delta < this.velocidadeMinima){
            return false;
        }
        
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

        for(int i=1; i<delta; i++){
            if(identificarObstaculo(i*direcao[0], i*direcao[1], amb)){
                return false;
            }
        }
        if(super.mover(delta*direcao[0], delta*direcao[1], amb)){
            return true;
        }
        return false;
    }
}
