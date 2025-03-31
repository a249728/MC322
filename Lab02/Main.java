public class Main {
        public static void main(String[] args) {
            // Cria um ambiente
            Ambiente ilhaMecanimais = new Ambiente(500, 500, 500);
            System.out.println("Criando o ambiente Ilha Base de dimensões 500x500x500");

            // Cria um robo criador que cria o restante dos robos
            RoboCriador JeffRosen = new RoboCriador("JeffRosen", 0, 0, "Leste", 1, 499);    
            Robo Sasquatch = JeffRosen.criarRobo(ilhaMecanimais, "Sasquatch", "Leste");
            moverRoboAereo(JeffRosen, 0, 1, 0, ilhaMecanimais);
            RoboTerrestre Rex = JeffRosen.criarRoboTerrestre(ilhaMecanimais, "Rex", "Leste", 100);
            moverRoboAereo(JeffRosen, 0, 1, 0, ilhaMecanimais);
            RoboAereo Unicornio = JeffRosen.criarRoboAereo(ilhaMecanimais, "Unicornio", "Leste", 0, 99);
            moverRoboAereo(JeffRosen, 0, 1, 0, ilhaMecanimais);
            RoboSubterraneo Corujao = JeffRosen.criarRoboSubterraneo(ilhaMecanimais, "Corujao", "Leste", 0, -99);
            moverRoboAereo(JeffRosen, 0, 1, 0, ilhaMecanimais);
            RoboLaser Komodo = JeffRosen.criarRoboLaser(ilhaMecanimais, "Komodo", "Leste", 100, 10);
            moverRoboAereo(JeffRosen, 0, 1, 0, ilhaMecanimais);
            RoboCorredor Mouse = JeffRosen.criarRoboCorredor(ilhaMecanimais, "Mouse", "Leste", 200, 100);

            // Exibe a posicao inicial dos robos criados
            imprimirPosicao(Sasquatch);
            imprimirPosicao(Rex);
            imprimirPosicao(Mouse);
            imprimirPosicao(Komodo);
            imprimirPosicaoAereo(Unicornio);
            imprimirPosicaoAereo(Corujao);
            imprimirPosicaoAereo(JeffRosen);
    
            // Move alguns dos robos criados
            moverRobo(Sasquatch, 501, 0, ilhaMecanimais);
            moverRoboTerrestre(Rex, 10, 0, ilhaMecanimais);
            moverRoboAereo(Unicornio, 10, -3, 0, ilhaMecanimais);
            moverRoboCorredor(Mouse, 400, ilhaMecanimais);
            moverRoboSubterraneo(Corujao, 0, 0, -100, ilhaMecanimais);


            //Utiliza dos métodos (ainda não utilizados) específicos dos robos especiais
            int numDestruidos = Komodo.dispararLaser(ilhaMecanimais);
            System.out.println("O robo " + Komodo.retornarNome() + " atingiu e destruiu " + numDestruidos + " robos");
            
            // Checa se os robos estão inicialmente dentro dos limites do ambiente
            checarLimites(ilhaMecanimais, Sasquatch);
            checarLimites(ilhaMecanimais, Rex);
            checarLimites(ilhaMecanimais, Mouse);
            checarLimites(ilhaMecanimais, Komodo);
            checarLimitesAereo(ilhaMecanimais, Unicornio);
            checarLimitesAereo(ilhaMecanimais, Corujao);
            checarLimitesAereo(ilhaMecanimais, JeffRosen);
    
            // Exibe a posicao final dos robos criados
            imprimirPosicao(Sasquatch);
            imprimirPosicao(Rex);
            imprimirPosicao(Mouse);
            imprimirPosicao(Komodo);
            imprimirPosicaoAereo(Unicornio);
            imprimirPosicaoAereo(Corujao);
            imprimirPosicaoAereo(JeffRosen);

    }

    public static void moverRobo(Robo robo, int dx, int dy, Ambiente amb) {
        // Utiliza do metodo mover para atualizar as posicoes do robo base e imprime as novas coordenadas adquiridas
        if (robo.mover(dx, dy, amb)) {
            int coord[] = robo.exibirPosicao();
            System.out.println("O robo " + robo.retornarNome() + " moveu para a posicao: (" + coord[0] + ", " + coord[1] + ")");
        }
        else {
            System.out.println("O robo " + robo.retornarNome() + " não se moveu");
        }
    }

    public static void moverRoboTerrestre(RoboTerrestre robo, int dx, int dy, Ambiente amb) {
        // Utiliza do metodo mover para atualizar as posicoes do robo terrestre e imprime as novas coordenadas adquiridas
        if (robo.mover(dx, dy, amb)) {
            int coord[] = robo.exibirPosicao();
            System.out.println("O robo " + robo.retornarNome() + " moveu para a posicao: (" + coord[0] + ", " + coord[1] + ")");
        }
        else {
            System.out.println("O robo " + robo.retornarNome() + " não se moveu");
        }
    }

    public static void moverRoboCorredor(RoboCorredor robo, int d, Ambiente amb) {
        // Utiliza do metodo mover para atualizar as posicoes do robo terrestre e imprime as novas coordenadas adquiridas
        if (robo.mover(d, amb)) {
            int coord[] = robo.exibirPosicao();
            System.out.println("O robo " + robo.retornarNome() + " moveu para a posicao: (" + coord[0] + ", " + coord[1] + ")");
        }
        else {
            System.out.println("O robo " + robo.retornarNome() + " não se moveu");
        }
    }

    public static void moverRoboSubterraneo(RoboSubterraneo robo, int dx, int dy, int dz, Ambiente amb) {
        // Utiliza do metodo mover para atualizar as posicoes do robo terrestre e imprime as novas coordenadas adquiridas
        if (robo.mover(dx, dy, dz, amb)) {
            int coord_xy[] = robo.exibirPosicao();
            int z = robo.exibirAltura();
            System.out.println("O robo " + robo.retornarNome() + " moveu para a posicao: (" + coord_xy[0] + ", " + coord_xy[1] + ", " + z + ")");
        }
        else {
            System.out.println("O robo " + robo.retornarNome() + " não se moveu");
        }
    }

    public static void moverRoboAereo(RoboAereo robo, int dx, int dy, int dz, Ambiente amb) {
        // Utiliza do metodo mover para atualizar as posicoes do robo aereo e imprime as novas coordenadas adquiridas
        if (robo.mover(dx, dy, dz, amb)) {
            int coord_xy[] = robo.exibirPosicao();
            int z = robo.exibirAltura();
            System.out.println("O robo " + robo.retornarNome() + " moveu para a posicao: (" + coord_xy[0] + ", " + coord_xy[1] + ", " + z + ")");
        }
        else {
            System.out.println("O robo " + robo.retornarNome() + " não se moveu");
        }
    }

    public static void checarLimites(Ambiente amb, Robo robo) {
        // Checa se as atuais coordenadas do robo base/terrestre indicam que ele esta dentro dos limites do ambiente e imprime o resultado
        int coord[] = robo.exibirPosicao();
        if (amb.dentroDosLimites(coord[0], coord[1])) {
            System.out.println("O robo " + robo.retornarNome() + " esta dentro dos limites do ambiente");
        }
        else {
            System.out.println("O robo " + robo.retornarNome() + " nao esta dentro dos limites do ambiente");
        }
    }

    public static void checarLimitesAereo(Ambiente amb, RoboAereo robo) {
        // Checa se as atuais coordenadas do robo aereo indicam que ele esta dentro dos limites do ambiente e imprime o resultado
        int coord_xy[] = robo.exibirPosicao();
        int z = robo.exibirAltura();
        if (amb.dentroDosLimitesAereo(coord_xy[0], coord_xy[1], z)) {
            System.out.println("O robo aereo " + robo.retornarNome() + " esta dentro dos limites do ambiente");
        }
        else {
            System.out.println("O robo " + robo.retornarNome() + " nao esta dentro dos limites do ambiente");
        }
    }

    public static void imprimirPosicao(Robo robo) {
        // Imprime as atuais coordenadas do robo base/terrestre
        int coord[] = robo.exibirPosicao();
        System.out.println("O robo " + robo.retornarNome() + " esta na posicao (" + coord[0] + ", " + coord[1] + ")");
    }

    public static void imprimirPosicaoAereo(RoboAereo robo) {
        // Imprime as atuais coordenadas do robo aereo
        int coord_xy[] = robo.exibirPosicao();
        int z = robo.exibirAltura();
        System.out.println("O robo " + robo.retornarNome() + " esta na posicao (" + coord_xy[0] + ", " + coord_xy[1] + ", " + z + ")");
    }
}