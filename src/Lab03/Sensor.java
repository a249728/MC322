public class Sensor {
    private double raioDeAlcance;
    private int bateria;
    protected Robo robo;

    public Sensor(double raio, int bat, Robo robo) {
        this.raioDeAlcance = raio;
        this.bateria = bat;
        this.robo = robo;
    }

    public boolean monitorar(int x, int y) {
        int coord[] = robo.exibirPosicao();
        if ((Math.pow(x-coord[0], 2) + Math.pow(y-coord[1], 2)) <= raioDeAlcance && bateria > 0) {
            bateria--;
            return true;
        }
        return false;
    }

    public double getRaioDeAlcance() {
        return raioDeAlcance;
    }

    public void setRaioDeAlcance(double novoRaio) {
        raioDeAlcance = novoRaio;
    }
}