public class Main {
        public static void main(String[] args) {
            // Cria um ambiente
            Ambiente Ilha_Base = new Ambiente(500, 500, 500);
            System.out.println("Criando o ambiente Ilha Base de dimensões 500x500x500");
            
            // Cria dois robos
            Robo Rex = new Robo("Rex", 500, 500, "Norte");
            RoboTerrestre Mouse = new RoboTerrestre("Mouse", 0, 0, "Norte", 100);
            RoboAereo Unicornio = new RoboAereo("Unicornio", 0, 0, "Norte", 0, 100);
            Ilha_Base.adicionarRobo(Ambiente.robosAtivos, Rex);
            Ilha_Base.adicionarRobo(Ambiente.robosAtivos, Mouse);
            Ilha_Base.adicionarRobo(Ambiente.robosAtivos, Unicornio);

            // Exibe a posicao inicial dos robos criados
            imprimirPosicao(Rex);
            imprimirPosicao(Mouse);
            imprimirPosicaoAereo(Unicornio);
    
            // Checa se os robos estão inicialmente dentro dos limites do ambiente
            checarLimites(Ilha_Base, Rex);
            checarLimites(Ilha_Base, Mouse);
            checarLimitesAereo(Ilha_Base, Unicornio);
    
            // Move os robos criados
            moverRobo(Rex, -3, 0);
            moverRoboTerrestre(Mouse, 400, 560, 50);
            moverRoboAereo(Unicornio, 50, 50, 100);
    
            // Exibe a posicao final dos robos criados
            imprimirPosicao(Rex);
            imprimirPosicao(Mouse);
            imprimirPosicaoAereo(Unicornio);
    }

    public static void moverRobo(Robo robo, int dx, int dy) {
        // Utiliza do metodo mover para atualizar as posicoes do robo base e imprime as novas coordenadas adquiridas
        if (robo.mover(dx, dy)) {
            int coord[] = robo.exibirPosicao();
            System.out.println("O robo " + robo.retornarNome() + " moveu para a posicao: (" + coord[0] + ", " + coord[1] + ")");
        }
        else {
            System.out.println("O robo " + robo.retornarNome() + " não se moveu");
        }
    }

    public static void moverRoboTerrestre(RoboTerrestre robo, int dx, int dy, int v) {
        // Utiliza do metodo mover para atualizar as posicoes do robo e imprime as novas coordenadas adquiridas
        if (robo.moverTerrestre(dx, dy, v)) {
            int coord[] = robo.exibirPosicao();
            System.out.println("O robo " + robo.retornarNome() + " moveu para a posicao: (" + coord[0] + ", " + coord[1] + ")");
        }
        else {
            System.out.println("O robo " + robo.retornarNome() + " não se moveu");
        }
    }

    public static void moverRoboAereo(RoboAereo robo, int dx, int dy, int dz) {
        // Utiliza do metodo mover para atualizar as posicoes do robo base e imprime as novas coordenadas adquiridas
        if (robo.moverAereo(dx, dy, dz)) {
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