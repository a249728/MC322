public class SensorPressao extends Sensor {
    public SensorPressao(double raio, int bat, Robo robo) {
        super(raio, bat, robo);
    }

    public String monitorarPressao(int x, int y, int z, Ambiente amb) {
        // Verifica se o ponto está dentro do alcance do sensor
        if (!this.monitorar(x, y, z)) {
            return "Nao foi possivel monitorar essa posicao";
        }

        // Calcula a pressão com base na altura
        double pressao = calcularPressao(z);

        return "A pressão no ponto (" + x + ", " + y + ", " + z + ") é " + pressao + " atm.";
    }

    private double calcularPressao(int z) {
        // Pressão atmosférica padrão ao nível do mar
        final double PRESSAO_NIVEL_MAR = 1.0; // em atm

        // Fator de variação da pressão por unidade de altura
        final double FATOR_ALTURA = 0.1; // diminui 0.1 atm por unidade acima do nível do mar
        final double FATOR_PROFUNDIDADE = 0.2; // aumenta 0.2 atm por unidade abaixo do nível do mar

        if (z >= 0) {
            // Quanto mais alto, menor a pressão
            return Math.max(0, PRESSAO_NIVEL_MAR - (z * FATOR_ALTURA));
        } else {
            // Quanto mais baixo, maior a pressão
            return PRESSAO_NIVEL_MAR + (Math.abs(z) * FATOR_PROFUNDIDADE);
        }
    }
}