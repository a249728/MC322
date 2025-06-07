package robo;
import ambiente.*;
import exception.*;
import java.util.ArrayList;

public class RoboAereo extends AgenteInteligente {
    // Declara as propriedades da classe RoboAereo
    private int altitude;
    private int altitudeMaxima;

    public RoboAereo(String n, int x, int y, String dir, int z, int zmax) {
        // Metodo construtor da classe
        super(n, x, y, dir);
        this.altitude = z;
        this.altitudeMaxima = zmax;
    }

    public int retornarAltitudeMaxima(){
        // Retorna a altitude maxima do robo
        return this.altitudeMaxima;
    }

    public void mudarAltitudeMaxima(int zmax){
        // Muda a altidude maxima do robo
        this.altitudeMaxima = zmax;
    }

    public int exibirAltura() {
        // Retorna posicao z
        return this.altitude;
    }

    public void subir(int deltaZ) throws RoboDesligadoException {
        if (!super.getEstado()) throw new RoboDesligadoException("O robo nao pode se mover pois estava desligado");
        // Atualiza a altitude do robo adicionando a variacao de posicao desejada
        this.altitude += deltaZ;
    }

    public void descer(int deltaZ) throws RoboDesligadoException {
        if (!super.getEstado()) throw new RoboDesligadoException("O robo nao pode se mover pois estava desligado");
        // Atualiza a altitude do robo adicionando a variacao de posicao desejada
        this.altitude -= deltaZ;
    }

    public boolean mover(int deltaX, int deltaY, int deltaZ, Ambiente amb) throws RoboDesligadoException, ColisaoException, ForaDosLimitesException, VelocidadeMaximaException {
        // Checa se o movimento nao ultrapassa a altitude maxima e retorna true ou false dependendo se o movimento foi bem sucedido ou nao
        if (this.altitude + deltaZ <= altitudeMaxima  && this.altitude + deltaZ >= 0 && !identificarObstaculo(deltaX, deltaY, deltaZ, amb)) {
            if (super.mover(deltaX, deltaY, amb)) {
                if(deltaZ>0){
                    subir(deltaZ);
                }
                else{
                    descer(-deltaZ);
                }
                amb.moverRoboMapa(this, deltaX, deltaY, deltaZ);
                return true;
            }
            else {
                return false;
            }
        }
        return false;
    }

    public boolean identificarObstaculo(int deltaX, int deltaY, int deltaZ, Ambiente amb) throws ColisaoException {
         // Checa se a posicao para a qual o robo quer mover ja esta ocupada por outro robo (obstaculo)
         ArrayList<Robo> robos = amb.retornarRobosAtivos();
         ArrayList<Obstaculo> obstaculos = amb.retornarObstaculos();
         int coord[] = exibirPosicao();
         int altura;
         for (Robo robo : robos) {
            altura = 0;
            if (robo instanceof RoboAereo) {
                altura = ((RoboAereo) robo).exibirAltura();
            }
            if (robo.exibirPosicao()[0] == coord[0] + deltaX && robo.exibirPosicao()[1] == coord[1] + deltaY && altura == this.altitude + deltaZ) {
                throw new ColisaoException("Nao foi possivel se mover por conta de possivel colisao");
            }
         }
         for (Obstaculo obstaculo : obstaculos) {
            boolean dentroX = coord[0] + deltaX >= obstaculo.getX() && coord[0] + deltaX < obstaculo.getX() + obstaculo.getObstaculo().getComprimento();
            boolean dentroY = coord[1] + deltaY >= obstaculo.getY() && coord[1] + deltaY < obstaculo.getY() + obstaculo.getObstaculo().getLargura();
            boolean dentroZ = Math.abs(this.altitude + deltaZ) < Math.abs(obstaculo.getObstaculo().getAltura());
            if (dentroX && dentroY && dentroZ) {
                throw new ColisaoException("Nao foi possivel se mover por conta de possivel colisao"); // Colisão detectada com um obstáculo
            }
         }
         return false;       
    }

    @Override
    public int getZ() {
        // Retorna a coordenada Z da entidade
        return this.altitude;
    }

    @Override
    public char getRepresentacao() {
        // Retorna o caractere que representa a entidade visualmente
        return 'A';
    }

    @Override
    public void executarMissao(Ambiente a)  throws RoboDesligadoException, ForaDosLimitesException, BateriaSensorException {
        if (temMissao()) {
            System.out.println(this.missao.executar(this, a));
        } else {
            System.out.println("Nenhuma missão definida para este robô.");
        }
    }
}
