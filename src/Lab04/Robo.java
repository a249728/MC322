import java.util.ArrayList;

public abstract class Robo implements Entidade, Sensoreavel {
    private String nome;
    private int posicaoX;
    private int posicaoY;
    private String direcao;
    private SensorIluminacao sensorIluminacao; // Referência direta para o sensor de iluminação
    private SensorPressao sensorPressao; // Referência direta para o sensor de colisão
    private boolean estado = true; // Estado do robô (ativo ou inativo)

    public Robo(String n, int x, int y, String dir) {
        this.nome = n;
        this.posicaoX = x;
        this.posicaoY = y;
        this.direcao = dir;
        this.sensorIluminacao = null; // Inicializa como null
        this.sensorPressao = null; // Inicializa como null
    }

    public boolean mover(int deltaX, int deltaY, Ambiente amb) throws RoboDesligadoException {
        if (!this.estado) {
            throw new RoboDesligadoException("O robo nao pode se mover pois estava desligado"); // Se o robô estiver desligado, não pode se mover
        }
        if (this.posicaoX + deltaX >= 0 && this.posicaoY + deltaY >= 0 && (!identificarObstaculo(deltaX, deltaY, amb) || this instanceof RoboAereo)) {
            this.posicaoX += deltaX;
            this.posicaoY += deltaY;
            return true;
        }
        return false;
    }

    public void mudarDirecao(String dir) throws RoboDesligadoException {
        if (!this.estado) {
            throw new RoboDesligadoException("O robo nao pode mudar de direcao pois estava desligado");
        }
        this.direcao = dir;
    }

    public String retornarDirecao() {
        return this.direcao;
    }

    @Override
    public int getX() {
        return this.posicaoX;
    }

    @Override
    public int getY() {
        return this.posicaoY;
    }

    public abstract int getZ();

    @Override
    public TipoEntidade getTipo() {
        return TipoEntidade.ROBO;
    }

    @Override
    public String getDescricao() {
        return TipoEntidade.ROBO.getDescricao();
    }

    public abstract char getRepresentacao();

    public int[] exibirPosicao() {
        return new int[] { this.posicaoX, this.posicaoY };
    }

    public void mudarNome(String n) {
        this.nome = n;
    }

    public String retornarNome() {
        return this.nome;
    }

    public void ligar() {
        this.estado = true;
    }

    public void desligar() {
        this.estado = false;
    }

    public boolean getEstado() {
        return this.estado;
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
            boolean dentroX = posicaoX + deltaX >= obstaculo.getX() && posicaoX + deltaX < obstaculo.getX() + obstaculo.getObstaculo().getComprimento();
            boolean dentroY = posicaoY + deltaY >= obstaculo.getY() && posicaoY + deltaY < obstaculo.getY() + obstaculo.getObstaculo().getLargura();
            if (dentroX && dentroY) {
                return true; // Colisão detectada com um obstáculo
            }
        }
        return false;
    }

    public String usarSensorIluminacao(int x, int y, int z, Ambiente amb) throws RoboDesligadoException, BateriaSensorException {
        if (!this.estado) {
            throw new RoboDesligadoException("O robo nao pode usar o sensor de iluminacao pois estava desligado");
        }
        // Verifica se o sensor de iluminação está disponível e chama o método de monitoramento
        if (this.sensorIluminacao != null) {
            return this.sensorIluminacao.monitorarIluminacao(x, y, z, amb);
        }
        return "Sensor de iluminacao nao disponivel";
    }

    public String usarSensorPressao(int x, int y, int z, Ambiente amb) throws RoboDesligadoException, BateriaSensorException {
        if (!this.estado) {
            throw new RoboDesligadoException("O robo nao pode usar o sensor de pressao pois estava desligado");
        }
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
