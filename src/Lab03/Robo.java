public class Robo {
    private String nome;
    private int posicaoX;
    private int posicaoY;
    private String direcao;
    private SensorIluminacao sensorIluminacao; // Referência direta para o sensor de iluminação
    private SensorColisao sensorColisao; // Referência direta para o sensor de colisão

    public Robo(String n, int x, int y, String dir) {
        this.nome = n;
        this.posicaoX = x;
        this.posicaoY = y;
        this.direcao = dir;
        this.sensorIluminacao = null; // Inicializa como null
        this.sensorColisao = null; // Inicializa como null
    }

    public boolean mover(int deltaX, int deltaY, Ambiente amb) {
        if (this.posicaoX + deltaX >= 0 && this.posicaoY + deltaY >= 0 && !identificarObstaculo(deltaX, deltaY, amb)) {
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

    public Object[] identificarObstaculo(int deltaX, int deltaY, Ambiente amb) {
        if (sensorColisao != null) {
            String resultado = sensorColisao.monitorarColisao(this.posicaoX + deltaX, this.posicaoY + deltaY, amb);
            return new Object[] { temObstaculo, resultado };
        }
        return new Object[] { false, "" }; // Sem sensor de colisão ou sem obstáculo
    }

    public void adicionarSensorIluminacao(double raio, int bateria) {
        if (this.sensorIluminacao == null) { // Garante que só pode haver um sensor de iluminação
            this.sensorIluminacao = new SensorIluminacao(raio, bateria, this);
        }
    }

    public void adicionarSensorColisao(double raio, int bateria) {
        if (this.sensorColisao == null) { // Garante que só pode haver um sensor de colisão
            this.sensorColisao = new SensorColisao(raio, bateria, this);
        }
    }

    public SensorIluminacao getSensorIluminacao() {
        return this.sensorIluminacao;
    }

    public SensorColisao getSensorColisao() {
        return this.sensorColisao;
    }
}
