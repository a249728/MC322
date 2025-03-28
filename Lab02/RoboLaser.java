public class RoboLaser extends RoboTerrestre{

    private int alcanceLaser;

    public RoboLaser(String n, int x, int y, String dir, int vmax, int alc){
        super(n, x, y, dir, vmax);
        this.alcanceLaser=alc;
    }

    public void dispararLaser(Ambiente amb){

        if(this.retornarDirecao() == "Norte"){
            for(int i=0; i<alcanceLaser; i++){
                if(identificarObstaculo(i, 0, amb)){
                    amb.destruirRobo(amb.acharRobo(i, 0));
                }
            }
        }
        if(this.retornarDirecao() == "Sul"){
            for(int i=0; i<alcanceLaser; i++){
                if(identificarObstaculo(-i, 0, amb)){
                    amb.destruirRobo(amb.acharRobo(-i, 0));
                }
            }
        }
        if(this.retornarDirecao() == "Leste"){
            for(int i=0; i<alcanceLaser; i++){
                if(identificarObstaculo(0, i, amb)){
                    amb.destruirRobo(amb.acharRobo(0, i));
                }
            }
        }
        if(this.retornarDirecao() == "Oeste"){
            for(int i=0; i<alcanceLaser; i++){
                if(identificarObstaculo(0, i, amb)){
                    amb.destruirRobo(amb.acharRobo(0, -i));
                }
            }
        }
    }
}