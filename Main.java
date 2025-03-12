public class Main {

    public static void main(String[] args) {
        Robo C3PO = new Robo("C3PO", 0, 0);
        Robo R2D2 = new Robo("R2D2", 0, 0);

        moverRobo(C3PO, 3, 0);
        moverRobo(R2D2, 2, 2);
    }

    public static void moverRobo(Robo robo, int x, int y) {
        robo.mover(x, y);
        System.out.println("O robo " + robo.retornarNome() + " moveu para a posição: " + robo.exibirPosicao());
    }
}