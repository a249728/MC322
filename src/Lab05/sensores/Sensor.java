package sensores;
public class Sensor {
    private double raioDeAlcance;
    private int bateria;
    protected Robo robo;

    public Sensor(double raio, int bat, Robo robo) {
        this.raioDeAlcance = raio;
        this.bateria = bat;
        this.robo = robo;
    }

    public boolean monitorar(int x, int y, int z) throws BateriaSensorException {
        // Verifica se o sensor está dentro do raio de alcance e se a bateria está disponível
        if (bateria <= 0) {
            throw new BateriaSensorException("Bateria do sensor esgotada.");
        }
        int coord[] = robo.exibirPosicao();
        double dist;
        if(robo instanceof RoboAereo){
            dist = (Math.pow(x - coord[0], 2) + Math.pow(y - coord[1], 2) + Math.pow(z - ((RoboAereo)robo).exibirAltura(), 2));
        }
        else{
            dist = (Math.pow(x - coord[0], 2) + Math.pow(y - coord[1], 2) + Math.pow(z, 2));
        }
        if (dist <= Math.pow(raioDeAlcance, 2)) {
            bateria--;
            return true;
        }
        return false;
    }

    public double getRaioDeAlcance() {
        // Retorna o raio de alcance do sensor
        return raioDeAlcance;
    }

    public void setRaioDeAlcance(double novoRaio) {
        // Muda o raio de alcance do sensor
        raioDeAlcance = novoRaio;
    }

    public int getBateria() {
        // Retorna a bateria atual do sensor
        return bateria;
    }

    public void setBateria(int novaBateria) {
        // Muda a bateria atual do sensor
        bateria = novaBateria;
    }
}