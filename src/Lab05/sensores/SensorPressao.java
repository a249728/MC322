package sensores;
import robo.*;
import ambiente.*;
import exception.BateriaSensorException;

public class SensorPressao extends Sensor {
    public SensorPressao(double raio, int bat, Robo robo) {
        super(raio, bat, robo);
    }

    public String monitorarPressao(int x, int y, int z, Ambiente amb) throws BateriaSensorException {
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
        final double FATOR_ALTURA = 0.00012514185; // diminui rho*g Pascal, ou rho*g/101325=1.293*9.80665/101325=0.00012514185 atm por unidade acima do nível do mar
        final double FATOR_PROFUNDIDADE = 0.5337643696; // aumenta rho*g Pascal, ou rho*g/101325=5515*9.80665/101325=0.5337643696 atm por unidade abaixo do nível do mar

        if (z >= 0) {
            // Quanto mais alto, menor a pressão
            return Math.max(0, PRESSAO_NIVEL_MAR - (z * FATOR_ALTURA));
        } else {
            // Quanto mais baixo, maior a pressão
            return PRESSAO_NIVEL_MAR + ((-z) * FATOR_PROFUNDIDADE);
        }
    }
}