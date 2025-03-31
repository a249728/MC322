public class Main {
    public static void main(String[] args) {
        // Cria dois robos
        Robo C3PO = new Robo("C3PO", 0, 0);
        Robo R2D2 = new Robo("R2D2", 0, 0);

        // Cria um ambiente
        Ambiente Millennium_Falcon = new Ambiente(3, 3);
        System.out.println("Criando o ambiente Millennium Falcon de dimensões 3x3");

        // Exibe a posicao inicial dos robos criados
        imprimirPosicao(C3PO);
        imprimirPosicao(R2D2);

        // Checa se os robos estão inicialmente dentro dos limites do ambiente
        checarLimites(Millennium_Falcon, C3PO);
        checarLimites(Millennium_Falcon, R2D2);

        // Move os dois robos criados
        moverRobo(C3PO, 3, 0);
        moverRobo(R2D2, 2, 2);

        // Checa se os robos estao dentro dos limites do ambiente apos o movimento
        checarLimites(Millennium_Falcon, C3PO);
        checarLimites(Millennium_Falcon, R2D2);
    }

    public static void moverRobo(Robo robo, int dx, int dy) {
        // Utiliza do metodo mover para atualizar as posicoes do robo e imprime as novas coordenadas adquiridas
        robo.mover(dx, dy);
        int coord[] = robo.exibirPosicao();
        System.out.println("O robo " + robo.retornarNome() + " moveu para a posicao: (" + coord[0] + ", " + coord[1] + ")");
    }

    public static void checarLimites(Ambiente amb, Robo robo) {
        // Checa se as atuais coordenadas do robo indicam que ele esta dentro dos limites do ambiente e imprime o resultado
        int coord[] = robo.exibirPosicao();
        if (amb.dentroDosLimites(coord[0], coord[1])) {
            System.out.println("O robo " + robo.retornarNome() + " esta dentro dos limites do ambiente");
        }
        else {
            System.out.println("O robo " + robo.retornarNome() + " nao esta dentro dos limites do ambiente");
        }
    }

    public static void imprimirPosicao(Robo robo) {
        // Imprime as atuais coordenadas do robo
        int coord[] = robo.exibirPosicao();
        System.out.println("O robo " + robo.retornarNome() + " esta na posicao (" + coord[0] + ", " + coord[1] + ")");
    }
}