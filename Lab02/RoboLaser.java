public class RoboLaser extends RoboTerrestre{

    private int alcanceLaser;

    public RoboLaser(String n, int x, int y, String dir, int vmax, int alc){
        // Metodo construtor da classe
        super(n, x, y, dir, vmax);
        this.alcanceLaser=alc;
    }

    public int retornarAlcanceLaser(){
        // Retorna o alcance do laser do robo
        return this.alcanceLaser;
    }

    public void mudarAlcanceLaser(int alc){
        // Muda o alcance laser do robo
        this.alcanceLaser = alc;
    }

    public void dispararLaser(Ambiente amb){

        int[] direcao = {0,0};

        if(this.retornarDirecao() == "Leste"){
            direcao[0]=1;
        }else if(this.retornarDirecao() == "Oeste"){
            direcao[0]=-1;
        }else if(this.retornarDirecao() == "Norte"){
            direcao[1]=1;
        }else if(this.retornarDirecao() == "Sul"){
            direcao[1]=-1;
        }

        for(int i=0; i<alcanceLaser; i++){
            if(identificarObstaculo(i*direcao[0], i*direcao[1], amb)){
                amb.destruirRobo(amb.acharRobo(i*direcao[0], i*direcao[1]));
            }
        }
    }
}