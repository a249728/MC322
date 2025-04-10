public class Sensor {
    private double raioDeAlcance;

    public Sensor(double raio) {
        this.raioDeAlcance = raio;
    }

    public boolean dentroDoRaio(int x, int y) {
        if ((Math.pow(x, 2) + Math.pow(y, 2)) <= raioDeAlcance) {
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