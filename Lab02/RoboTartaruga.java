public class RoboTartaruga extends RoboTerrestre{
    
    public RoboTartaruga(String n, int x, int y, String dir, int vmax){
        super(n, x, y, dir, vmax);
    }

    public boolean mover(int delta, int v, Ambiente amb){
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
        if(super.mover(delta*direcao[0], delta*direcao[1], v, amb)){
            return true;
        };
        return false;
    }
}
