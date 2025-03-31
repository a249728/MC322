public class Main {
    public static void main(String[] args) {
        // Cria um ambiente
        Ambiente ilhaMecanimais = new Ambiente(500, 500, 500);
        System.out.println("Criando o ambiente Ilha Base de dimensões 500x500x500");

        // Cria um robo criador que cria o restante dos robos
        RoboCriador JeffRosen = new RoboCriador("JeffRosen", 0, 0, "Norte", 1, 499);
        imprimirPosicaoAereo(JeffRosen);
        Robo Sasquatch = JeffRosen.criarRobo(ilhaMecanimais, "Sasquatch");
        moverRoboAereo(JeffRosen, 0, 1, 0, ilhaMecanimais);
        RoboTerrestre Rex = JeffRosen.criarRoboTerrestre(ilhaMecanimais, "Rex", 100);
        moverRoboAereo(JeffRosen, 0, 1, 0, ilhaMecanimais);
        RoboAereo Unicornio = JeffRosen.criarRoboAereo(ilhaMecanimais, "Unicornio", 0, 99);
        moverRoboAereo(JeffRosen, 0, 1, 0, ilhaMecanimais);
        RoboSubterraneo Corujao = JeffRosen.criarRoboSubterraneo(ilhaMecanimais, "Corujao", 0, -99);
        moverRoboAereo(JeffRosen, 0, 1, 0, ilhaMecanimais);
        RoboLaser Komodo = JeffRosen.criarRoboLaser(ilhaMecanimais, "Komodo", 100, 10);
        moverRoboAereo(JeffRosen, 0, 1, 0, ilhaMecanimais);
        RoboCorredor Mouse = JeffRosen.criarRoboCorredor(ilhaMecanimais, "Mouse", 200, 100);
        checarLimitesAereo(ilhaMecanimais, JeffRosen);
        imprimirPosicaoAereo(JeffRosen);
        System.out.println();

        // Sasquatch (Robo)
        imprimirPosicao(Sasquatch);
        checarLimites(ilhaMecanimais, Sasquatch);
        moverRobo(Sasquatch, 501, 0, ilhaMecanimais); //Move Sasquatch para fora dos limites
        checarLimites(ilhaMecanimais, Sasquatch); //Imprime que Sasquatch está fora dos limites
        System.out.println();

        // Rex (RoboTerrestre)
        imprimirPosicao(Rex);
        checarLimites(ilhaMecanimais, Rex);
        moverRoboTerrestre(Rex, 400, 0, ilhaMecanimais); //Rex não move pois tenta mover com uma velocidade maior do que a permitida
        moverRoboTerrestre(Rex, 2, -1, ilhaMecanimais); //Move Rex de acordo com as regras
        checarLimites(ilhaMecanimais, Rex); // Rex permanece dentro dos limites
        System.out.println();

        // Unicornio (RoboAereo)
        imprimirPosicaoAereo(Unicornio);
        checarLimitesAereo(ilhaMecanimais, Unicornio);
        moverRoboAereo(Unicornio, 0, 0, 80, ilhaMecanimais); //Unicornio sobe para cima em 80 (dentro da altitude maxima)
        moverRoboAereo(Unicornio, 0, 0, 50, ilhaMecanimais); //Unicornio nao se move pois ultrapassa altitude maxima
        moverRoboAereo(Unicornio, 0, 0, -90, ilhaMecanimais); //Unicornio nao se move pois tenta ir para a parte subterranea e nao e subterraneo
        moverRoboAereo(Unicornio, 3, -2, 10, ilhaMecanimais); //Move Unicornio de acordo com as regras
        checarLimitesAereo(ilhaMecanimais, Unicornio); //Unicornio permanece dentro dos limites
        System.out.println();
        
        // Corujao (RoboSubterraneo)
        imprimirPosicaoAereo(Corujao);
        checarLimitesAereo(ilhaMecanimais, Corujao); //Corujao esta dentro dos limites pois nao esta em baixo da terra (z=0)
        moverRoboSubterraneo(Corujao, 10, 10, 10, ilhaMecanimais); //Corujao nao move pois nao pode ir para altitudes positivas
        moverRoboSubterraneo(Corujao, 0, 0, -100, ilhaMecanimais); //Corijao nao move pois ultrapassa a altura minima
        moverRoboSubterraneo(Corujao, 10, 10, -10, ilhaMecanimais); //Move Corujao de acordo com as regras
        checarLimitesAereo(ilhaMecanimais, Corujao); //Corujao esta fora dos limites pois estara em uma altura negativa
        System.out.println();
        
        // Komodo (RoboLaser)
        imprimirPosicao(Komodo);
        checarLimites(ilhaMecanimais, Komodo);
        moverRobo(Komodo, 200, -5, ilhaMecanimais); //Komodo nao move pois passa a velocidade maxima
        moverRobo(Komodo, 1, -4, ilhaMecanimais); //Move Komodo de acordo com as regras
        mudarDirecaoRobo(Komodo, "Leste"); //Muda direcao de Komodo
        dispararLaserRobo(Komodo, ilhaMecanimais); //Komodo destroi Rex e Unicornio, nao destroi Sasquatch pois esta muito longe
        checarLimites(ilhaMecanimais, Komodo); //Komodo permanece dentro dos limites
        System.out.println();
        
        // Mouse (RoboCorredor)
        imprimirPosicao(Mouse);
        checarLimites(ilhaMecanimais, Komodo);
        moverRoboCorredor(Mouse, 95, ilhaMecanimais); //Mouse nao move pois esta abaixo da velocidade minima
        moverRoboCorredor(Mouse, 100, ilhaMecanimais); //Move mouse de acordo com as regras
        mudarDirecaoRobo(Mouse, "Leste"); //Muda direcao de Mouse
        moverRoboCorredor(Mouse, 100, ilhaMecanimais); //Move mouse de acordo com as regras
        mudarDirecaoRobo(Mouse, "Sul"); //Muda direcao de Mouse
        moverRoboCorredor(Mouse, 105, ilhaMecanimais); //Move mouse de acordo com as regras
        mudarDirecaoRobo(Mouse, "Oeste"); //Muda direcao de Mouse
        moverRoboCorredor(Mouse, 150, ilhaMecanimais); //Mouse nao move pois Komodo esta no caminho
        checarLimites(ilhaMecanimais, Mouse); //Mouse pemanece dentro dos limites
    }

    public static void mudarDirecaoRobo(Robo robo, String dir){
        //Utiliza do metodo mudarDirecao para mudar a direcao que o robo esta voltado
        robo.mudarDirecao(dir);
        System.out.println("O robo " + robo.retornarNome() + " está olhando para a direção " + dir);  
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

    public static void dispararLaserRobo(RoboLaser robo, Ambiente amb){
        // Dispara o laser do robo laser e imprime quantos robos foram destruidos dentro do alcance do laser
        int numDestruidos = robo.dispararLaser(amb);
        System.out.println("O robo " + robo.retornarNome() + " atingiu e destruiu " + numDestruidos + " robos");
    }
}