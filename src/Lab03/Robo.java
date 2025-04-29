import java.util.ArrayList;

public class Robo {
    private String nome;
    private int posicaoX;
    private int posicaoY;
    private String direcao;
    private SensorIluminacao sensorIluminacao; // Referência direta para o sensor de iluminação
    private SensorPressao sensorPressao; // Referência direta para o sensor de colisão

    public Robo(String n, int x, int y, String dir) {
        this.nome = n;
        this.posicaoX = x;
        this.posicaoY = y;
        this.direcao = dir;
        this.sensorIluminacao = null; // Inicializa como null
        this.sensorPressao = null; // Inicializa como null
    }

    public boolean mover(int deltaX, int deltaY, Ambiente amb) {
        if (this.posicaoX + deltaX >= 0 && this.posicaoY + deltaY >= 0 && (!identificarObstaculo(deltaX, deltaY, amb) || this instanceof RoboAereo)) {
            this.posicaoX += deltaX;
            this.posicaoY += deltaY;
            return true;
        }
        return false;
    }

    public void mudarDirecao(String dir) {
        this.direcao = dir;
    }

    public String retornarDirecao() {
        return this.direcao;
    }

    public int[] exibirPosicao() {
        return new int[] { this.posicaoX, this.posicaoY };
    }

    public void mudarNome(String n) {
        this.nome = n;
    }

    public String retornarNome() {
        return this.nome;
    }

    public boolean identificarObstaculo(int deltaX, int deltaY, Ambiente amb) {
        // Checa se a posicao para a qual o robo quer mover ja esta ocupada por outro robo (obstaculo)
        ArrayList<Robo> robos = amb.retornarRobosAtivos();
        ArrayList<Obstaculo> obstaculos = amb.retornarObstaculos();
        for (Robo robo : robos) {
            if (robo.posicaoX == this.posicaoX + deltaX && robo.posicaoY == this.posicaoY + deltaY && robo != this) {
                return true;
            }
        }
        for (Obstaculo obstaculo : obstaculos) {
            boolean dentroX = posicaoX + deltaX >= obstaculo.getPosicaoX() && posicaoX + deltaX < obstaculo.getPosicaoX() + obstaculo.getObstaculo().getComprimento();
            boolean dentroY = posicaoY + deltaY >= obstaculo.getPosicaoY() && posicaoY + deltaY < obstaculo.getPosicaoY() + obstaculo.getObstaculo().getLargura();
            if (dentroX && dentroY) {
                return true; // Colisão detectada com um obstáculo
            }
        }
        return false;
    }

    public String usarSensorIluminacao(int x, int y, int z, Ambiente amb) {
        // Verifica se o sensor de iluminação está disponível e chama o método de monitoramento
        if (this.sensorIluminacao != null) {
            return this.sensorIluminacao.monitorarIluminacao(x, y, z, amb);
        }
        return "Sensor de iluminacao nao disponivel";
    }

    public String usarSensorPressao(int x, int y, int z, Ambiente amb) {
        // Verifica se o sensor de pressão está disponível e chama o método de monitoramento
        if (this.sensorPressao != null) {
            return this.sensorPressao.monitorarPressao(x, y, z, amb);
        }
        return "Sensor de pressao nao disponivel";
    }

    public void adicionarSensorIluminacao(double raio, int bateria) {
        // Cria sensor de iluminacao
        if (this.sensorIluminacao == null) { // Garante que só pode haver um sensor de iluminação
            this.sensorIluminacao = new SensorIluminacao(raio, bateria, this);
        }
    }

    public void adicionarSensorPressao(double raio, int bateria) {
        // Cria sensor de pressao
        if (this.sensorPressao == null) { // Garante que só pode haver um sensor de colisão
            this.sensorPressao = new SensorPressao(raio, bateria, this);
        }
    }

    public SensorIluminacao getSensorIluminacao() {
        return this.sensorIluminacao;
    }

    public SensorPressao getSensorPressao() {
        return this.sensorPressao;
    }
}
