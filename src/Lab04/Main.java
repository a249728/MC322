import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.util.Optional;

public class Main {
    private static Ambiente ambiente;
    private static Scanner scanner;
    private static CentralComunicacao central;
    private static Robo roboSelecionado;

    public static void main(String[] args) {
        // Inicialização do ambiente
        ambiente = new Ambiente(30, 30, 500, "15:00");
        central = new CentralComunicacao();
        scanner = new Scanner(System.in);

        // Cria robôs iniciais
        inicializarRobos(ambiente);
        
        // Menu principal
        exibirMenuPrincipal();
    }

    private static void inicializarRobos(Ambiente ambiente) {
        try {
            RoboGerador JeffRosen = new RoboGerador("JeffRosen", 0, 0, "Norte", 1, 10);
            ambiente.adicionarRobo(JeffRosen);
            
            RoboTerrestre Rex = JeffRosen.gerarRoboTerrestre(ambiente, "Rex", 10);
            JeffRosen.mover(1, 1, 0, ambiente);
            RoboAereo Unicornio = JeffRosen.gerarRoboAereo(ambiente, "Unicornio", 0, 10);
            JeffRosen.mover(1, 1, 0, ambiente);
            RoboSubterraneo Corujao = JeffRosen.gerarRoboSubterraneo(ambiente, "Corujao", 0, -10);
            JeffRosen.mover(1, 1, 0, ambiente);
            RoboLaser Komodo = JeffRosen.gerarRoboLaser(ambiente, "Komodo", 10, 5);
            JeffRosen.mover(1, 1, 0, ambiente);
            RoboCorredor Mouse = JeffRosen.gerarRoboCorredor(ambiente, "Mouse", 15, 5);
            JeffRosen.mover(1, 1, 0, ambiente);


            // Adicionar sensores
            Rex.adicionarSensorPressao(20, 100);
            Unicornio.adicionarSensorIluminacao(20, 100);
            Komodo.adicionarSensorPressao(5, 10);
            Komodo.adicionarSensorIluminacao(5, 10);
            
            // Criar obstáculos
            ambiente.criarObstaculo(TipoObstaculo.PEDRA, 10, 3);
            ambiente.criarObstaculo(TipoObstaculo.ARVORE, 18, 12);
            ambiente.criarObstaculo(TipoObstaculo.BURACO, 10, 10);
            //ambiente.criarObstaculo(TipoObstaculo.LAGO, 50, 50);
            
        } catch (Exception e) {
            System.err.println("Erro na inicialização: " + e.getMessage());
        }
    }

    private static void exibirMenuPrincipal() {
        while (true) {
            System.out.println("\n=== MENU PRINCIPAL ===");
            System.out.println("1. Listar todos os robôs");
            System.out.println("2. Listar robôs por tipo");
            System.out.println("3. Listar robôs por estado");
            System.out.println("4. Selecionar robô");
            System.out.println("5. Visualizar mapa do ambiente");
            System.out.println("6. Listar mensagens de comunicação");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");

            int opcao = scanner.nextInt();
            scanner.nextLine(); // Limpar buffer

            switch (opcao) {
                case 1 -> listarTodosRobos();
                case 2 -> listarRobosPorTipo();
                case 3 -> listarRobosPorEstado();
                case 4 -> selecionarRobo();
                case 5 -> visualizarMapaAmbiente();
                case 6 -> exibirMensagensComunicacao();
                case 0 -> {
                    System.out.println("Encerrando o sistema...");
                    System.exit(0);
                }
                default -> System.out.println("Opção inválida!");
            }
        }
    }

    private static void listarTodosRobos() {
        System.out.println("\n=== LISTA DE TODOS OS ROBÔS ===");
        List<Robo> robos = ambiente.retornarRobosAtivos();
        if (robos.isEmpty()) {
            System.out.println("Nenhum robô cadastrado.");
            return;
        }
        for (int i = 0; i < robos.size(); i++) {
            Robo robo = robos.get(i);
            System.out.printf("%d. [%s] %s - Posição: (%d, %d, %d) - Estado: %s%n",
                    i + 1,
                    robo.getRepresentacao(),
                    robo.retornarNome(),
                    robo.getX(),
                    robo.getY(),
                    robo.getZ(),
                    robo.getEstado() ? "Ligado" : "Desligado");
        }
    }

    private static void listarRobosPorTipo() {
        System.out.println("\n=== LISTAR ROBÔS POR TIPO ===");
        System.out.println("1. Robôs Terrestres");
        System.out.println("2. Robôs Aéreos");
        System.out.println("3. Robôs Subterrâneos");
        System.out.println("4. Robôs Geradores");
        System.out.println("5. Robôs com Laser");
        System.out.println("6. Robôs Corredores");
        System.out.print("Escolha um tipo: ");
        
        int tipo = scanner.nextInt();
        scanner.nextLine();
        
        List<Robo> robos = ambiente.retornarRobosAtivos();
        List<Robo> filtrados = new ArrayList<>();
        String tipoNome = "";
        
        switch (tipo) {
            case 1:
                filtrados = robos.stream().filter(r -> r instanceof RoboTerrestre).toList();
                tipoNome = "Robôs Terrestres";
                break;
            case 2:
                filtrados = robos.stream().filter(r -> r instanceof RoboAereo).toList();
                tipoNome = "Robôs Aéreos";
                break;
            case 3:
                filtrados = robos.stream().filter(r -> r instanceof RoboSubterraneo).toList();
                tipoNome = "Robôs Subterrâneos";
                break;
            case 4:
                filtrados = robos.stream().filter(r -> r instanceof RoboGerador).toList();
                tipoNome = "Robôs Geradores";
                break;
            case 5:
                filtrados = robos.stream().filter(r -> r instanceof RoboLaser).toList();
                tipoNome = "Robôs com Laser";
                break;
            case 6:
                filtrados = robos.stream().filter(r -> r instanceof RoboCorredor).toList();
                tipoNome = "Robôs Corredores";
                break;
            default:
                System.out.println("Tipo inválido!");
                return;
        }
        
        System.out.println("\n=== " + tipoNome + " ===");
        if (filtrados.isEmpty()) {
            System.out.println("Nenhum robô deste tipo encontrado.");
            return;
        }
        filtrados.forEach(r -> System.out.printf("- [%s] %s - Posição: (%d, %d, %d)%n",
                r.getRepresentacao(), r.retornarNome(), r.getX(), r.getY(), r.getZ()));
    }

    private static void listarRobosPorEstado() {
        System.out.println("\n=== LISTAR ROBÔS POR ESTADO ===");
        System.out.println("1. Ligados");
        System.out.println("2. Desligados");
        System.out.print("Escolha um estado: ");
        
        int estadoOp = scanner.nextInt();
        scanner.nextLine();
        
        boolean estado;
        String estadoNome;
        if (estadoOp == 1) {
            estado = true;
            estadoNome = "Ligados";
        } else if (estadoOp == 2) {
            estado = false;
            estadoNome = "Desligados";
        } else {
            System.out.println("Opção inválida!");
            return;
        }
        
        List<Robo> robos = ambiente.retornarRobosAtivos();
        List<Robo> filtrados = robos.stream()
                .filter(r -> r.getEstado() == estado)
                .toList();
        
        System.out.println("\n=== ROBÔS " + estadoNome.toUpperCase() + " ===");
        if (filtrados.isEmpty()) {
            System.out.println("Nenhum robô neste estado encontrado.");
            return;
        }
        filtrados.forEach(r -> System.out.printf("- [%s] %s - Posição: (%d, %d, %d)%n",
                r.getRepresentacao(), r.retornarNome(), r.getX(), r.getY(), r.getZ()));
    }

    private static void selecionarRobo() {
        System.out.print("\nDigite o nome do robô: ");
        String nome = scanner.nextLine();
        
        Optional<Robo> roboOpt = ambiente.retornarRobosAtivos().stream()
                .filter(r -> r.retornarNome().equalsIgnoreCase(nome))
                .findFirst();
        
        if (roboOpt.isPresent()) {
            roboSelecionado = roboOpt.get();
            System.out.println("Robô selecionado: " + roboSelecionado.retornarNome());
            exibirMenuRobo();
        } else {
            System.out.println("Robô não encontrado!");
        }
    }

    private static void exibirMenuRobo() {
        while (roboSelecionado != null) {
            System.out.println("\n=== MENU DO ROBÔ " + roboSelecionado.retornarNome().toUpperCase() + " ===");
            System.out.println("1. Visualizar status");
            System.out.println("2. Executar tarefa principal");
            System.out.println("3. Controlar movimento");
            System.out.println("4. Comunicar com outro robô");
            System.out.println("5. Acionar sensores");
            System.out.println("6. Ligar/Desligar");
            System.out.println("7. Voltar ao menu principal");
            System.out.print("Escolha uma opção: ");
            
            int opcao = scanner.nextInt();
            scanner.nextLine();
            
            switch (opcao) {
                case 1 -> visualizarStatusRobo();
                case 2 -> executarTarefaPrincipal();
                case 3 -> controlarMovimento();
                case 4 -> iniciarComunicacao();
                case 5 -> acionarSensores();
                case 6 -> alternarEstadoRobo();
                case 7 -> {
                    roboSelecionado = null;
                    return;
                }
                default -> System.out.println("Opção inválida!");
            }
        }
    }

    private static void visualizarStatusRobo() {
        System.out.println("\n=== STATUS DO ROBÔ ===");
        System.out.println("Nome: " + roboSelecionado.retornarNome());
        System.out.println("Tipo: " + roboSelecionado.getClass().getSimpleName());
        System.out.println("Estado: " + (roboSelecionado.getEstado() ? "Ligado" : "Desligado"));
        System.out.printf("Posição: (%d, %d, %d)%n", roboSelecionado.getX(), roboSelecionado.getY(), roboSelecionado.getZ());
        System.out.println("Direção: " + roboSelecionado.retornarDirecao());
        
        // Informações específicas
        if (roboSelecionado instanceof RoboAereo) {
            RoboAereo aereo = (RoboAereo) roboSelecionado;
            System.out.println("Altitude: " + aereo.exibirAltura());
            System.out.println("Altitude máxima: " + aereo.retornarAltitudeMaxima());
        } else if (roboSelecionado instanceof RoboSubterraneo) {
            RoboSubterraneo sub = (RoboSubterraneo) roboSelecionado;
            System.out.println("Profundidade: " + sub.exibirAltura());
            System.out.println("Profundidade máxima: " + sub.retornarAltitudeMinima());
        } else if (roboSelecionado instanceof RoboTerrestre) {
            RoboTerrestre terrestre = (RoboTerrestre) roboSelecionado;
            System.out.println("Velocidade máxima: " + terrestre.retornarVelocidadeMaxima());
        } else if (roboSelecionado instanceof RoboLaser) {
            RoboLaser laser = (RoboLaser) roboSelecionado;
            System.out.println("Alcance do laser: " + laser.retornarAlcanceLaser());
        } else if (roboSelecionado instanceof RoboCorredor) {
            RoboCorredor corredor = (RoboCorredor) roboSelecionado;
            System.out.println("Velocidade mínima: " + corredor.retornarVelocidadeMinima());
            System.out.println("Velocidade máxima: " + corredor.retornarVelocidadeMaxima());
        } else if (roboSelecionado instanceof RoboGerador) {
            RoboGerador gerador = (RoboGerador) roboSelecionado;
            System.out.println("Número de filhos gerados: " + gerador.retornarFilhos());
        }
        
        // Sensores
        System.out.println("\nSensores:");
        if (roboSelecionado.getSensorIluminacao() != null) {
            System.out.println("- Sensor de Iluminação: raio=" + roboSelecionado.getSensorIluminacao().getRaioDeAlcance() +
                    ", bateria=" + roboSelecionado.getSensorIluminacao().getBateria());
        }
        if (roboSelecionado.getSensorPressao() != null) {
            System.out.println("- Sensor de Pressão: raio=" + roboSelecionado.getSensorPressao().getRaioDeAlcance() +
                    ", bateria=" + roboSelecionado.getSensorPressao().getBateria());
        }
        if (roboSelecionado.getSensorIluminacao() == null && roboSelecionado.getSensorPressao() == null) {
            System.out.println("Nenhum sensor instalado.");
        }
        
        // Status do ambiente
        System.out.println("\n=== STATUS DO AMBIENTE ===");
        System.out.println("Horário: " + ambiente.retornarHorario());
        System.out.println("Dimensões: " + ambiente.getComprimento() + "x" + ambiente.getLargura() + "x" + ambiente.getAltura());
        System.out.println("Total de robôs: " + ambiente.retornarRobosAtivos().size());
        System.out.println("Total de obstáculos: " + ambiente.retornarObstaculos().size());
    }

    private static void gerarNovoRobo() {
        System.out.println("\n=== GERAR NOVO ROBÔ ===");
        System.out.println("1. Robô Terrestre");
        System.out.println("2. Robô Aéreo");
        System.out.println("3. Robô Subterrâneo");
        System.out.println("4. Robô Laser");
        System.out.println("5. Robô Corredor");
        System.out.println("6. Robô Gerador");
        System.out.print("Escolha o tipo de robô a ser gerado: ");
        
        int tipo = scanner.nextInt();
        scanner.nextLine();
        
        System.out.print("Digite o nome do novo robô: ");
        String nome = scanner.nextLine();
        
        RoboGerador gerador = (RoboGerador) roboSelecionado;
        
        try {
            switch (tipo) {
                case 1:
                    System.out.print("Velocidade máxima: ");
                    int vmax = scanner.nextInt();
                    scanner.nextLine();
                    RoboTerrestre roboT = gerador.gerarRoboTerrestre(ambiente, nome, vmax);
                    ambiente.adicionarRobo(roboT);
                    System.out.println("Robô terrestre gerado com sucesso!");
                    break;
                case 2:
                    System.out.print("Altitude inicial: ");
                    int zAereo = scanner.nextInt();
                    System.out.print("Altitude máxima: ");
                    int zmax = scanner.nextInt();
                    scanner.nextLine();
                    RoboAereo roboA = gerador.gerarRoboAereo(ambiente, nome, zAereo, zmax);
                    ambiente.adicionarRobo(roboA);
                    System.out.println("Robô aéreo gerado com sucesso!");
                    break;
                case 3:
                    System.out.print("Profundidade inicial: ");
                    int zSub = scanner.nextInt();
                    System.out.print("Profundidade mínima: ");
                    int zmin = scanner.nextInt();
                    scanner.nextLine();
                    RoboSubterraneo roboS = gerador.gerarRoboSubterraneo(ambiente, nome, zSub, zmin);
                    ambiente.adicionarRobo(roboS);
                    System.out.println("Robô subterrâneo gerado com sucesso!");
                    break;
                case 4:
                    System.out.print("Velocidade máxima: ");
                    int vmaxL = scanner.nextInt();
                    System.out.print("Alcance do laser: ");
                    int alc = scanner.nextInt();
                    scanner.nextLine();
                    RoboLaser roboL = gerador.gerarRoboLaser(ambiente, nome, vmaxL, alc);
                    ambiente.adicionarRobo(roboL);
                    System.out.println("Robô laser gerado com sucesso!");
                    break;
                case 5:
                    System.out.print("Velocidade máxima: ");
                    int vmaxC = scanner.nextInt();
                    System.out.print("Velocidade mínima: ");
                    int vminC = scanner.nextInt();
                    scanner.nextLine();
                    RoboCorredor roboC = gerador.gerarRoboCorredor(ambiente, nome, vmaxC, vminC);
                    ambiente.adicionarRobo(roboC);
                    System.out.println("Robô corredor gerado com sucesso!");
                    break;
                case 6:
                    System.out.print("Altitude inicial: ");
                    int zGer = scanner.nextInt();
                    System.out.print("Altitude máxima: ");
                    int zmaxGer = scanner.nextInt();
                    scanner.nextLine();
                    RoboGerador roboG = gerador.gerarRoboGerador(ambiente, nome, zGer, zmaxGer);
                    ambiente.adicionarRobo(roboG);
                    System.out.println("Robô gerador gerado com sucesso!");
                    break;
                default:
                    System.out.println("Tipo inválido!");
            }
        } catch (RoboDesligadoException | ForaDosLimitesException e) {
            System.out.println("Erro ao gerar robô: " + e.getMessage());
        }
    }

private static void executarTarefaPrincipal() {
    if (roboSelecionado instanceof RoboGerador) {
        gerarNovoRobo(); // Chama o novo método de geração
    } else if (roboSelecionado instanceof RoboLaser) {
        try {
            int destruidos = ((RoboLaser) roboSelecionado).dispararLaser(ambiente);
            System.out.println("Laser disparado! " + destruidos + " robô(s) destruído(s)");
        } catch (RoboDesligadoException | ColisaoException | ForaDosLimitesException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    } else if (roboSelecionado instanceof RoboCorredor) {
        System.out.print("Digite a distância para correr: ");
        int distancia = scanner.nextInt();
        scanner.nextLine();
        
        try {
            boolean sucesso = ((RoboCorredor) roboSelecionado).correr(distancia, ambiente);
            if (sucesso) {
                System.out.println("Corrida realizada com sucesso!");
                System.out.println("Nova posição: (" + roboSelecionado.getX() + ", " + roboSelecionado.getY() + ")");
            } else {
                System.out.println("Falha ao correr. Verifique obstáculos ou direção");
            }
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    } else {
        System.out.println("Este robô não possui uma tarefa principal específica");
    }
}

    private static void controlarMovimento() {
        System.out.println("\n=== CONTROLE DE MOVIMENTO ===");
        System.out.println("1. Frente");
        System.out.println("2. Trás");
        System.out.println("3. Direita");
        System.out.println("4. Esquerda");
        System.out.println("5. Subir (aéreos/subterrâneos)");
        System.out.println("6. Descer (aéreos/subterrâneos)");
        System.out.print("Escolha uma direção: ");
        
        int direcao = scanner.nextInt();
        scanner.nextLine();
        
        System.out.print("Distância (passos): ");
        int passos = scanner.nextInt();
        scanner.nextLine();
        
        int dx = 0, dy = 0, dz = 0;
        
        switch (direcao) {
            case 1 -> dy = passos;   // Frente (norte)
            case 2 -> dy = -passos;  // Trás (sul)
            case 3 -> dx = passos;   // Direita (leste)
            case 4 -> dx = -passos;  // Esquerda (oeste)
            case 5 -> dz = passos;   // Subir
            case 6 -> dz = -passos;  // Descer
            default -> {
                System.out.println("Direção inválida!");
                return;
            }
        }
        
        try {
            if (roboSelecionado instanceof RoboAereo) {
                ((RoboAereo) roboSelecionado).mover(dx, dy, dz, ambiente);
            } else if (roboSelecionado instanceof RoboSubterraneo) {
                ((RoboSubterraneo) roboSelecionado).mover(dx, dy, dz, ambiente);
            } else {
                roboSelecionado.mover(dx, dy, ambiente);
            }
            System.out.println("Movimento realizado com sucesso!");
            System.out.println("Nova posição: (" + roboSelecionado.getX() + ", " + roboSelecionado.getY() + ")");
        } catch (RoboDesligadoException | ColisaoException | ForaDosLimitesException e) {
            System.out.println("Erro ao mover: " + e.getMessage());
        }
    }

    private static void iniciarComunicacao() {
        if (!(roboSelecionado instanceof Comunicavel)) {
            System.out.println("Este robô não pode se comunicar!");
            return;
        }
        Comunicavel comunicador = (Comunicavel) roboSelecionado;
        
        System.out.print("Digite o nome do robô destinatário: ");
        String nomeDest = scanner.nextLine();
        
        Optional<Robo> destOpt = ambiente.retornarRobosAtivos().stream()
                .filter(r -> r.retornarNome().equalsIgnoreCase(nomeDest) && r instanceof Comunicavel)
                .findFirst();
        
        if (destOpt.isEmpty()) {
            System.out.println("Destinatário não encontrado ou não é comunicável!");
            return;
        }
        Comunicavel destino = (Comunicavel) destOpt.get();
        
        System.out.print("Digite a mensagem: ");
        String mensagem = scanner.nextLine();
        
        try {
            comunicador.enviarMensagem(destino, mensagem);
            System.out.println("Mensagem enviada com sucesso!");
        } catch (RoboDesligadoException | ErroComunicacaoException e) {
            System.out.println("Erro na comunicação: " + e.getMessage());
        }
    }

    private static void acionarSensores() {
        if (!(roboSelecionado instanceof Sensoreavel)) {
            System.out.println("Este robô não possui sensores!");
            return;
        }
        
        System.out.print("Digite a coordenada X para monitorar: ");
        int x = scanner.nextInt();
        System.out.print("Digite a coordenada Y para monitorar: ");
        int y = scanner.nextInt();
        System.out.print("Digite a coordenada Z para monitorar: ");
        int z = scanner.nextInt();
        scanner.nextLine();
        
        try {
            if (roboSelecionado.getSensorIluminacao() != null) {
                String resultado = roboSelecionado.usarSensorIluminacao(x, y, z, ambiente);
                System.out.println("Resultado do sensor de iluminação: " + resultado);
            }
            if (roboSelecionado.getSensorPressao() != null) {
                String resultado = roboSelecionado.usarSensorPressao(x, y, z, ambiente);
                System.out.println("Resultado do sensor de pressão: " + resultado);
            }
        } catch (RoboDesligadoException | BateriaSensorException e) {
            System.out.println("Erro ao acionar sensores: " + e.getMessage());
        }
    }

    private static void alternarEstadoRobo() {
        if (roboSelecionado.getEstado()) {
            roboSelecionado.desligar();
            System.out.println("Robô desligado!");
        } else {
            roboSelecionado.ligar();
            System.out.println("Robô ligado!");
        }
    }

    private static void visualizarMapaAmbiente() {
    System.out.println("\n=== MAPA DO AMBIENTE (VISTA SUPERIOR) ===");
    char[][] mapa = ambiente.visualizarAmbiente();
    
    // Dimensões do mapa
    int largura = ambiente.getLargura();
    int comprimento = ambiente.getComprimento();
    
    // Imprimir linha separadora superior
    System.out.print("   +");
    for (int x = 0; x < comprimento; x++) {
        System.out.print("---");
    }
    System.out.println("+");
    
    // Imprimir mapa
    for (int y = largura-1; y >= 0; y--) {
        System.out.printf("%2d | ", y);
        for (int x = 0; x < comprimento; x++) {
            System.out.print(mapa[x][y] + "  ");
        }
        System.out.println("|");
    }
    
    // Imprimir linha separadora inferior
    System.out.print("   +");
    for (int x = 0; x < comprimento; x++) {
        System.out.print("---");
    }
    System.out.println("+");
        
    // Imprimir coordenadas X (horizontal)
    System.out.print("     ");
    for (int x = 0; x < comprimento; x++) {
        System.out.printf("%2d ", x);
    }
    System.out.println();
    
    // Legenda
    System.out.println("\nLEGENDA:");
    System.out.println("  .  - Espaço vazio");
    System.out.println("  T  - Robô Terrestre");
    System.out.println("  A  - Robô Aéreo");
    System.out.println("  S  - Robô Subterrâneo");
    System.out.println("  G  - Robô Gerador");
    System.out.println("  L  - Robô Laser");
    System.out.println("  C  - Robô Corredor");
    System.out.println("  P  - Pedra");
    System.out.println("  V  - Árvore");
    System.out.println("  B  - Buraco");
    System.out.println("  ~  - Lago");
}

    private static void exibirMensagensComunicacao() {
        System.out.println("\n=== MENSAGENS DE COMUNICAÇÃO ===");
        List<String> mensagens = CentralComunicacao.getMensagens();
        
        if (mensagens.isEmpty()) {
            System.out.println("Nenhuma mensagem registrada.");
            return;
        }
        
        for (int i = 0; i < mensagens.size(); i++) {
            System.out.printf("%d. %s%n", i + 1, mensagens.get(i));
        }
    }

}
