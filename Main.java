public class Main {

    public static void main(String[] args) {
        Robo C3PO = new Robo("C3PO", 0, 0);
        Robo R2D2 = new Robo("R2D2", 0, 0);

        C3PO.mover(3, 0);
        R2D2.mover(2, 2);

        System.out.println(C3PO.exibirPosicao());
        System.out.println(R2D2.exibirPosicao());
    }
}