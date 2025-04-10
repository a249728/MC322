public class SensorIluminacao extends Sensor {
    private final String momento;

    public SensorIluminacao(double raio, String momento) {
        super(raio);
        this.momento = momento;
    }

    public double monitorarIluminacao(Robo robo, double raio, Ambiente amb) {
        int area = 0, iluminado = 0;

        for(int i = 1; i <= raio; i++) {
            for(int j = 1; j <= raio; j++) {
                if (super.dentroDoRaio(i, j)) {
                    area++;
                    if (amb.getIluminacao(robo, i, j, momento)) {
                        iluminado++;
                    }
                }
            }
        }

        return iluminado/area;
    }
}