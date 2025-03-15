public class Main {
    public static void main(String[] args) {
        Robo C3PO = new Robo("C3PO", 0, 0);
        Robo R2D2 = new Robo("R2D2", 0, 0);

        Ambiente Millennium_Falcon = new Ambiente(2, 2);

        imprimirPosicao(C3PO);
        imprimirPosicao(R2D2);

        moverRobo(C3PO, 3, 0);
        moverRobo(R2D2, 2, 2);

        checarLimites(Millennium_Falcon, C3PO);
        checarLimites(Millennium_Falcon, R2D2);
    }

    public static void moverRobo(Robo robo, int x, int y) {
        robo.mover(x, y);
        int coord[] = robo.exibirPosicao();
        System.out.println("O robo " + robo.retornarNome() + " moveu para a posicao: (" + coord[0] + ", " + coord[1] + ")");
    }

    public static void checarLimites(Ambiente amb, Robo robo) {
        int coord[] = robo.exibirPosicao();
        if (amb.dentroDosLimites(coord[0], coord[1])) {
            System.out.println("O robo " + robo.retornarNome() + " esta dentro dos limites do ambiente");
        }
        else {
            System.out.println("O robo " + robo.retornarNome() + " nao esta dentro dos limites do ambiente");
        }
    }

    public static void imprimirPosicao(Robo robo) {
        int coord[] = robo.exibirPosicao();
        System.out.println("O robo " + robo.retornarNome() + " esta na posicao (" + coord[0] + ", " + coord[1] + ")");
    }
}