import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        // Cria um ambiente
        Ambiente ilhaMecanimais = new Ambiente(500, 500, 500, "15:00");
        imprimir("\n=== SIMULADOR DE ROBÔS ===");
        imprimir("Ambiente criado: Ilha Mecanimais (500x500x500) - Horário: 15:00");
        
        // Cria e exibe robôs iniciais
        imprimir("\n=== ROBÔS INICIAIS ===");
        RoboGerador JeffRosen = new RoboGerador("JeffRosen", 0, 0, "Norte", 1, 499);
        ilhaMecanimais.adicionarRobo(JeffRosen);
        imprimir("- JeffRosen: Robô gerador em (0,0) - Pode criar outros robôs");
        
        Robo Sasquatch = JeffRosen.gerarRobo(ilhaMecanimais, "Sasquatch");
        JeffRosen.mover(0, 1, 0, ilhaMecanimais);
        imprimir("- Sasquatch: Robô básico em " + coordenadas(Sasquatch));
        
        RoboTerrestre Rex = JeffRosen.gerarRoboTerrestre(ilhaMecanimais, "Rex", 100);
        JeffRosen.mover(0, 1, 0, ilhaMecanimais);
        imprimir("- Rex: Robô terrestre em " + coordenadas(Rex) + " - Velocidade máxima: 100");
        
        RoboAereo Unicornio = JeffRosen.gerarRoboAereo(ilhaMecanimais, "Unicornio", 0, 99);
        JeffRosen.mover(0, 1, 0, ilhaMecanimais);
        imprimir("- Unicornio: Robô aéreo em " + coordenadas(Unicornio) + " - Altura máxima: 99");
        
        RoboSubterraneo Corujao = JeffRosen.gerarRoboSubterraneo(ilhaMecanimais, "Corujao", 0, -99);
        JeffRosen.mover(0, 1, 0, ilhaMecanimais);
        imprimir("- Corujao: Robô subterrâneo em " + coordenadas(Corujao) + " - Profundidade máxima: -99");
        
        RoboLaser Komodo = JeffRosen.gerarRoboLaser(ilhaMecanimais, "Komodo", 100, 10);
        JeffRosen.mover(0, 1, 0, ilhaMecanimais);
        imprimir("- Komodo: Robô laser em " + coordenadas(Komodo) + " - Alcance: 10 unidades");
        
        RoboCorredor Mouse = JeffRosen.gerarRoboCorredor(ilhaMecanimais, "Mouse", 200, 100);
        imprimir("- Mouse: Robô corredor em " + coordenadas(Mouse) + " - Velocidade: 100-200");
        
        // Adiciona e exibe sensores iniciais
        imprimir("\n=== SENSORES PRÉ-CONFIGURADOS ===");
        Rex.adicionarSensorPressao(100, 100);
        imprimir("- Rex: Sensor de Pressão (Raio: 100, Bateria: 100)");
        
        Unicornio.adicionarSensorIluminacao(100, 100);
        imprimir("- Unicornio: Sensor de Iluminação (Raio: 100, Bateria: 100)");
        
        Sasquatch.adicionarSensorPressao(10, 10);
        Sasquatch.adicionarSensorIluminacao(10, 10);
        imprimir("- Sasquatch: Sensor de Pressão e Iluminação (Raio: 10, Bateria: 10 cada)");
        
        // Cria e exibe obstáculos iniciais
        imprimir("\n=== OBSTÁCULOS INICIAIS ===");
        ilhaMecanimais.criarObstaculo(TipoObstaculo.PEDRA, 3, 3);
        imprimir("- PEDRA em (3,3) - Tamanho: 3x3 - Bloqueia robôs terrestres");
        
        ilhaMecanimais.criarObstaculo(TipoObstaculo.ARVORE, 20, 10);
        imprimir("- ARVORE em (20,10) - Altura: 10 - Bloqueia robôs aéreos abaixo de 10 unidades");
        
        ilhaMecanimais.criarObstaculo(TipoObstaculo.BURACO, 10, 20);
        imprimir("- BURACO em (10,20) - Profundidade: -5 - Perigoso para robôs terrestres");
        
        ilhaMecanimais.criarObstaculo(TipoObstaculo.LAGO, 50, 50);
        imprimir("- LAGO em (50,50) - Tamanho: 21x21 - Área alagada");

        imprimir("\nDigite 'help' para ver todos os comandos");
        imprimir("Digite 'testesExemplos' para ver exemplos de uso");
        imprimir("Digite 'sair' para encerrar\n");

        Scanner scanner = new Scanner(System.in);
        console(scanner, ilhaMecanimais);
        scanner.close();
    }

    public static void console(Scanner scanner, Ambiente ambiente) {
        // Menu interativo
        // Checa cada comando e executa a ação correspondente
        while (true) {
            imprimir(">>> ");
            String linha = ler(scanner);
            if (linha.equalsIgnoreCase("sair")) break;

            String[] partes = linha.split(" ");
            if (partes.length < 1) continue;

            String comando = partes[0].toLowerCase();

            try {
                switch (comando) {
                    case "adicionarsensor":
                        if (partes.length < 5) {
                            imprimir("Uso incorreto. Formato completo:");
                            imprimir("adicionarSensor <nomeRobo> <tipoSensor> <raio> <bateria>");
                            imprimir("Tipos de sensor: 'iluminacao' ou 'pressao'");
                            imprimir("Exemplo: adicionarSensor Rex pressao 50 100");
                            break;
                        }
                        String nomeRobo = partes[1];
                        String tipoSensor = partes[2].toLowerCase();
                        double raio = Double.parseDouble(partes[3]);
                        int bateria = Integer.parseInt(partes[4]);
                        
                        Robo robo = buscarRobo(ambiente, nomeRobo);
                        if (robo != null) {
                            if (tipoSensor.equals("iluminacao")) {
                                robo.adicionarSensorIluminacao(raio, bateria);
                                imprimir("✔ Sensor de iluminação adicionado a " + nomeRobo);
                                imprimir("  Raio: " + raio + " unidades | Bateria: " + bateria + " usos");
                            } else if (tipoSensor.equals("pressao")) {
                                robo.adicionarSensorPressao(raio, bateria);
                                imprimir("✔ Sensor de pressão adicionado a " + nomeRobo);
                                imprimir("  Raio: " + raio + " unidades | Bateria: " + bateria + " usos");
                            } else {
                                imprimir("Tipo de sensor inválido. Use 'iluminacao' ou 'pressao'");
                            }
                        } else {
                            imprimir("Robô '" + nomeRobo + "' não encontrado. Use 'listarRobos' para ver os disponíveis");
                        }
                        break;

                    case "gerarrobo":
                        if (partes.length < 4) {
                            imprimir("Uso incorreto. Formato completo:");
                            imprimir("gerarRobo <nomeGerador> <tipo> <nomeNovo> [parametros]");
                            imprimir("Tipos disponíveis e seus parâmetros:");
                            imprimir("- base: nenhum parâmetro extra");
                            imprimir("- terrestre: <velocidadeMaxima>");
                            imprimir("- aereo: <alturaInicial> <alturaMaxima>");
                            imprimir("- subterraneo: <profundidadeInicial> <profundidadeMinima>");
                            imprimir("- laser: <velocidadeMaxima> <alcance>");
                            imprimir("- corredor: <velocidadeMaxima> <velocidadeMinima>");
                            imprimir("Exemplo: gerarRobo JeffRosen aereo NovoRobo 0 100");
                            break;
                        }
                        String nomeGerador = partes[1];
                        String tipo = partes[2].toLowerCase();
                        String nomeNovo = partes[3];
                        Robo gerador = buscarRobo(ambiente, nomeGerador);
                        
                        if (gerador instanceof RoboGerador) {
                            Robo novo = null;
                            switch (tipo) {
                                case "base":
                                    novo = ((RoboGerador) gerador).gerarRobo(ambiente, nomeNovo);
                                    break;
                                case "terrestre":
                                    if (partes.length >= 5) {
                                        int vmaxT = Integer.parseInt(partes[4]);
                                        novo = ((RoboGerador) gerador).gerarRoboTerrestre(ambiente, nomeNovo, vmaxT);
                                    } else {
                                        imprimir("Faltam parâmetros. Uso: gerarRobo <nomeGerador> terrestre <nomeNovo> <velocidadeMaxima>");
                                        break;
                                    }
                                    break;
                                case "aereo":
                                    if (partes.length >= 6) {
                                        int z = Integer.parseInt(partes[4]);
                                        int zmax = Integer.parseInt(partes[5]);
                                        novo = ((RoboGerador) gerador).gerarRoboAereo(ambiente, nomeNovo, z, zmax);
                                    } else {
                                        imprimir("Faltam parâmetros. Uso: gerarRobo <nomeGerador> aereo <nomeNovo> <alturaInicial> <alturaMaxima>");
                                        break;
                                    }
                                    break;
                                case "subterraneo":
                                    if (partes.length >= 6) {
                                        int z = Integer.parseInt(partes[4]);
                                        int zmin = Integer.parseInt(partes[5]);
                                        novo = ((RoboGerador) gerador).gerarRoboSubterraneo(ambiente, nomeNovo, z, zmin);
                                    } else {
                                        imprimir("Faltam parâmetros. Uso: gerarRobo <nomeGerador> subterraneo <nomeNovo> <profundidadeInicial> <profundidadeMinima>");
                                        break;
                                    }
                                    break;
                                case "laser":
                                    if (partes.length >= 6) {
                                        int vmax = Integer.parseInt(partes[4]);
                                        int alcance = Integer.parseInt(partes[5]);
                                        novo = ((RoboGerador) gerador).gerarRoboLaser(ambiente, nomeNovo, vmax, alcance);
                                    } else {
                                        imprimir("Faltam parâmetros. Uso: gerarRobo <nomeGerador> laser <nomeNovo> <velocidadeMaxima> <alcance>");
                                        break;
                                    }
                                    break;
                                case "corredor":
                                    if (partes.length >= 6) {
                                        int vmax = Integer.parseInt(partes[4]);
                                        int vmin = Integer.parseInt(partes[5]);
                                        novo = ((RoboGerador) gerador).gerarRoboCorredor(ambiente, nomeNovo, vmax, vmin);
                                    } else {
                                        imprimir("Faltam parâmetros. Uso: gerarRobo <nomeGerador> corredor <nomeNovo> <velocidadeMaxima> <velocidadeMinima>");
                                        break;
                                    }
                                    break;
                                default:
                                    imprimir("Tipo de robô inválido. Use 'help' para ver os tipos disponíveis");
                                    break;
                            }
                            if (novo != null) {
                                imprimir("✔ Robô " + nomeNovo + " do tipo " + tipo + " gerado por " + nomeGerador + " em " + coordenadas(novo));
                            }
                        } else {
                            imprimir("Robô " + nomeGerador + " não é um gerador ou não foi encontrado");
                        }
                        break;

                    case "mover":
                        if (partes.length < 2) {
                            imprimir("Uso incorreto. Formato completo:");
                            imprimir("Para robôs terrestres: mover <nome> <deltaX> <deltaY>");
                            imprimir("Para robôs aéreos/subterrâneos: mover <nome> <deltaX> <deltaY> <deltaZ>");
                            imprimir("Para robôs corredores: mover <nome> <distancia>");
                            imprimir("Use 'listarRobos' para ver os tipos de cada robô");
                            break;
                        }
                        String nomeMov = partes[1];
                        Robo robMov = buscarRobo(ambiente, nomeMov);
                        
                        if (robMov != null) {
                            boolean sucesso = false;
                            if (robMov instanceof RoboSubterraneo && partes.length == 5) {
                                sucesso = ((RoboSubterraneo) robMov).mover(
                                    Integer.parseInt(partes[2]),
                                    Integer.parseInt(partes[3]),
                                    Integer.parseInt(partes[4]),
                                    ambiente
                                );
                            } else if (robMov instanceof RoboAereo && partes.length == 5) {
                                sucesso = ((RoboAereo) robMov).mover(
                                    Integer.parseInt(partes[2]),
                                    Integer.parseInt(partes[3]),
                                    Integer.parseInt(partes[4]),
                                    ambiente
                                );
                            } else if (robMov instanceof RoboCorredor && partes.length == 3) {
                                sucesso = ((RoboCorredor) robMov).correr(
                                    Integer.parseInt(partes[2]),
                                    ambiente
                                );
                            } else if (robMov instanceof RoboTerrestre && partes.length == 4) {
                                sucesso = ((RoboTerrestre) robMov).mover(
                                    Integer.parseInt(partes[2]),
                                    Integer.parseInt(partes[3]),
                                    ambiente
                                );
                            } else if (partes.length == 4) {
                                sucesso = robMov.mover(
                                    Integer.parseInt(partes[2]),
                                    Integer.parseInt(partes[3]),
                                    ambiente
                                );
                            } else {
                                imprimir("Parâmetros incorretos para este tipo de robô");
                                imprimir("Use 'help' para ver o formato correto");
                                break;
                            }
                            
                            if (sucesso) {
                                imprimir("✔ " + nomeMov + " movido para " + coordenadas(robMov));
                            } else {
                                imprimir("Falha ao mover " + nomeMov + ". Verifique obstáculos ou limites");
                            }
                        } else {
                            imprimir("Robô '" + nomeMov + "' não encontrado");
                        }
                        break;

                    case "listarsensores":
                        if (partes.length < 2) {
                            imprimir("Uso incorreto. Formato completo:");
                            imprimir("listarSensores <nomeRobo>");
                            imprimir("Exemplo: listarSensores Sasquatch");
                            break;
                        }
                        
                        String nomeRoboSensores = partes[1];
                        Robo roboSensores = buscarRobo(ambiente, nomeRoboSensores);
                        
                        if (roboSensores != null) {
                            imprimir("\n🔍 SENSORES DO ROBÔ " + nomeRoboSensores.toUpperCase());
                            
                            SensorIluminacao sensorIluminacao = roboSensores.getSensorIluminacao();
                            SensorPressao sensorPressao = roboSensores.getSensorPressao();
                            
                            if (sensorIluminacao == null && sensorPressao == null) {
                                imprimir("Este robô não possui sensores instalados");
                                imprimir("Use 'adicionarSensor' para instalar novos sensores");
                            } else {
                                if (sensorIluminacao != null) {
                                    imprimir("🟡 Sensor de Iluminação:");
                                    imprimir("   • Raio de alcance: " + sensorIluminacao.getRaioDeAlcance());
                                    imprimir("   • Bateria restante: " + sensorIluminacao.getBateria() + " usos");
                                }
                                if (sensorPressao != null) {
                                    imprimir("🔵 Sensor de Pressão:");
                                    imprimir("   • Raio de alcance: " + sensorPressao.getRaioDeAlcance());
                                    imprimir("   • Bateria restante: " + sensorPressao.getBateria() + " usos");
                                }
                            }
                        } else {
                            imprimir("Robô '" + nomeRoboSensores + "' não encontrado");
                        }
                        break;

                    case "exibirposicao":
                        if (partes.length < 2) {
                            imprimir("Uso incorreto. Formato completo:");
                            imprimir("exibirPosicao <nomeRobo>");
                            imprimir("Exemplo: exibirPosicao Rex");
                            break;
                        }
                        Robo robPos = buscarRobo(ambiente, partes[1]);
                        if (robPos != null) {
                            imprimir(robPos.retornarNome() + " está na posição " + coordenadas(robPos));
                        } else {
                            imprimir("Robô não encontrado");
                        }
                        break;

                    case "checarlimites":
                        if (partes.length < 2) {
                            imprimir("Uso incorreto. Formato completo:");
                            imprimir("checarLimites <nomeRobo>");
                            imprimir("Exemplo: checarLimites Unicornio");
                            break;
                        }
                        Robo robLim = buscarRobo(ambiente, partes[1]);
                        if (robLim != null) {
                            int[] xy = robLim.exibirPosicao();
                            boolean dentro;
                            if (robLim instanceof RoboAereo) {
                                int z = ((RoboAereo) robLim).exibirAltura();
                                dentro = ambiente.dentroDosLimitesAereo(xy[0], xy[1], z);
                            } else {
                                dentro = ambiente.dentroDosLimites(xy[0], xy[1]);
                            }
                            imprimir("O robô " + robLim.retornarNome() + (dentro ? " está " : " não está ") + "dentro dos limites");
                        } else {
                            imprimir("Robô não encontrado");
                        }
                        break;

                    case "listarrobos":
                        if (ambiente != null) {
                            imprimir("\n=== ROBÔS ATIVOS ===");
                            for (Robo r : ambiente.retornarRobosAtivos()) {
                                imprimir("- " + r.retornarNome() + " em " + coordenadas(r) + 
                                       " (" + r.getClass().getSimpleName() + ")");
                            }
                        }
                        break;

                    case "listarobstaculos":
                        if (ambiente != null) {
                            imprimir("\n=== OBSTÁCULOS ===");
                            for (Obstaculo o : ambiente.retornarObstaculos()) {
                                imprimir("- " + o.getObstaculo() + " em (" + 
                                       o.getPosicaoX() + ", " + o.getPosicaoY() + ")");
                            }
                        }
                        break;

                    case "destruirrobo":
                        if (partes.length < 2) {
                            imprimir("Uso incorreto. Formato completo:");
                            imprimir("destruirRobo <nomeRobo>");
                            imprimir("Exemplo: destruirRobo Alvo1");
                            break;
                        }
                        Robo r = buscarRobo(ambiente, partes[1]);
                        if (r != null) {
                            ambiente.destruirRobo(r);
                            imprimir("Robô " + partes[1] + " destruído com sucesso");
                        } else {
                            imprimir("Robô não encontrado");
                        }
                        break;

                    case "mudardirecao":
                        if (partes.length < 3) {
                            imprimir("Uso incorreto. Formato completo:");
                            imprimir("mudarDirecao <nomeRobo> <direcao>");
                            imprimir("Direções válidas: Norte, Sul, Leste, Oeste");
                            imprimir("Exemplo: mudarDirecao Rex Norte");
                            break;
                        }
                        Robo robDir = buscarRobo(ambiente, partes[1]);
                        if (robDir != null) {
                            robDir.mudarDirecao(partes[2]);
                            imprimir(robDir.retornarNome() + " agora está virado para " + partes[2]);
                        } else {
                            imprimir("Robô não encontrado");
                        }
                        break;

                    case "dispararlaser":
                        if (partes.length < 2) {
                            imprimir("Uso incorreto. Formato completo:");
                            imprimir("dispararLaser <nomeRobo>");
                            imprimir("Exemplo: dispararLaser Komodo");
                            break;
                        }
                        Robo robLas = buscarRobo(ambiente, partes[1]);
                        if (robLas instanceof RoboLaser) {
                            int destruidos = ((RoboLaser) robLas).dispararLaser(ambiente);
                            imprimir("Laser disparado! " + destruidos + " robô(s) destruído(s)");
                        } else {
                            imprimir("Este robô não possui um laser para disparar");
                        }
                        break;

                    case "removerobstaculo":
                        if (partes.length < 3) {
                            imprimir("Uso incorreto. Formato completo:");
                            imprimir("removerObstaculo <x> <y>");
                            imprimir("Exemplo: removerObstaculo 3 3");
                            break;
                        }
                        int x = Integer.parseInt(partes[1]);
                        int y = Integer.parseInt(partes[2]);
                        Obstaculo alvo = null;
                        for (Obstaculo o : ambiente.retornarObstaculos()) {
                            if (o.getPosicaoX() == x && o.getPosicaoY() == y) {
                                alvo = o;
                                break;
                            }
                        }
                        if (alvo != null) {
                            ambiente.destruirObstaculo(alvo);
                            imprimir("Obstáculo removido em (" + x + ", " + y + ")");
                        } else {
                            imprimir("Nenhum obstáculo encontrado na posição (" + x + ", " + y + ")");
                        }
                        break;

                    case "monitorar":
                        if (partes.length < 6) {
                            imprimir("Uso incorreto. Formato completo:");
                            imprimir("monitorar <tipo> <nomeRobo> <x> <y> <z>");
                            imprimir("Tipos: iluminacao, pressao");
                            imprimir("Exemplo: monitorar iluminacao Sasquatch 30 40 0");
                            break;
                        }
                        String tipoMon = partes[1];
                        String nomeMon = partes[2];
                        int xMon = Integer.parseInt(partes[3]);
                        int yMon = Integer.parseInt(partes[4]);
                        int zMon = Integer.parseInt(partes[5]);
                        Robo robMon = buscarRobo(ambiente, nomeMon);
                        if (robMon != null) {
                            monitorarSensor(tipoMon, robMon, xMon, yMon, zMon, ambiente);
                        } else {
                            imprimir("Robô não encontrado");
                        }
                        break;

                    case "criarobstaculo":
                        if (partes.length < 4) {
                            imprimir("Uso incorreto. Formato completo:");
                            imprimir("criarObstaculo <tipo> <x> <y>");
                            imprimir("Tipos: PEDRA, ARVORE, BURACO, LAGO");
                            imprimir("Exemplo: criarObstaculo PEDRA 100 100");
                            break;
                        }
                        try {
                            String tipoObs = partes[1].toUpperCase();
                            int xObs = Integer.parseInt(partes[2]);
                            int yObs = Integer.parseInt(partes[3]);
                            
                            Obstaculo novoObs = ambiente.criarObstaculo(TipoObstaculo.valueOf(tipoObs), xObs, yObs);
                            if (novoObs != null) {
                                imprimir("✔ Obstáculo " + tipoObs + " criado em (" + xObs + ", " + yObs + ")");
                            } else {
                                imprimir("Falha ao criar obstáculo: posição ocupada ou inválida");
                            }
                        } catch (IllegalArgumentException e) {
                            imprimir("Tipo de obstáculo inválido. Tipos disponíveis: PEDRA, ARVORE, BURACO, LAGO");
                        }
                        break;

                    case "testesexemplos":
                        imprimir("\n=== EXEMPLOS PRÁTICOS ===");
                        imprimir("1. Movimentar o robô Rex 5 unidades para norte:");
                        imprimir("   mover Rex 0 5");
                        
                        imprimir("\n2. Criar um novo robô aéreo chamado 'Fenix':");
                        imprimir("   gerarRobo JeffRosen aereo Fenix 0 50");
                        
                        imprimir("\n3. Adicionar sensor de pressão ao Unicornio:");
                        imprimir("   adicionarSensor Unicornio pressao 75 80");
                        
                        imprimir("\n4. Verificar posição do robô:");
                        imprimir("   exibirPosicao Mouse");
                        
                        imprimir("\n5. Monitorar iluminação em uma posição:");
                        imprimir("   monitorar iluminacao Sasquatch 30 40 0");
                        
                        imprimir("\n6. Criar uma nova árvore como obstáculo:");
                        imprimir("   criarObstaculo ARVORE 30 40");
                        
                        imprimir("\n7. Listar sensores de um robô:");
                        imprimir("   listarSensores Sasquatch");
                        break;

                    case "help":
                        imprimir("\n🌟 COMANDOS - SIMULADOR DE ROBÔS 🌟");
                        
                        imprimir("\n📋 INFORMAÇÕES BÁSICAS");
                        imprimir("- listarRobos - Mostra todos os robôs existentes");
                        imprimir("   • Exibe nome, posição e tipo de cada robô");
                        imprimir("- listarObstaculos - Lista todos os obstáculos do ambiente");
                        imprimir("   • Mostra tipo e posição de cada obstáculo");
                        imprimir("- listarSensores <nomeRobo> - Lista os sensores do robô especificado");
                        imprimir("   • Mostra tipo, raio de alcance e bateria dos sensores");
                        imprimir("- exibirPosicao <nomeRobo> - Mostra coordenadas exatas do robô");
                        imprimir("   • Para robôs aéreos/subterrâneos, mostra também altura/profundidade");
                        imprimir("- checarLimites <nomeRobo> - Verifica se o robô está dentro dos limites do ambiente");

                        imprimir("\n🚀 MOVIMENTAÇÃO");
                        imprimir("- mover <nomeRobo> <parametros> - Move o robô conforme seu tipo:");
                        imprimir("   • Robôs terrestres básicos: mover <nome> <deltaX> <deltaY>");
                        imprimir("     Exemplo: mover Rex 5 3 (move 5 para leste e 3 para norte)");
                        imprimir("   • Robôs aéreos/subterrâneos: mover <nome> <deltaX> <deltaY> <deltaZ>");
                        imprimir("     Exemplo: mover Unicornio 2 0 5 (move 2 leste e sobe 5 unidades)");
                        imprimir("   • Robôs corredores: mover <nome> <distancia>");
                        imprimir("     Exemplo: mover Mouse 10 (corre 10 unidades na direção atual)");
                        imprimir("- mudarDirecao <nomeRobo> <direcao> - Altera a orientação do robô");
                        imprimir("   • Direções válidas: Norte, Sul, Leste, Oeste");

                        imprimir("\n🛠️  CRIAÇÃO E PERSONALIZAÇÃO");
                        imprimir("- gerarRobo <nomeGerador> <tipo> <nomeNovo> [params] - Cria novo robô:");
                        imprimir("*nomeGerador = robô que gera novos robôs (exemplo: JeffRosen)");
                        imprimir("   • Tipos disponíveis e parâmetros extras necessários:");
                        imprimir("     - base: nenhum parâmetro extra");
                        imprimir("     - terrestre: <velocidadeMaxima>");
                        imprimir("     - aereo: <alturaInicial> <alturaMaxima>");
                        imprimir("     - subterraneo: <profundidadeInicial> <profundidadeMinima>");
                        imprimir("     - laser: <velocidadeMaxima> <alcanceLaser>");
                        imprimir("     - corredor: <velocidadeMaxima> <velocidadeMinima>");
                        imprimir("- criarObstaculo <tipo> <x> <y> - Adiciona novo obstáculo:");
                        imprimir("   • Tipos: PEDRA (3x3), ARVORE (1x1, altura 10), BURACO (5x5), LAGO (21x21)");
                        imprimir("- adicionarSensor <nomeRobo> <tipo> <raio> <bateria> - Instala sensor:");
                        imprimir("   • Tipos: iluminacao (detecta sombras), pressao (mede pressão atmosférica)");

                        imprimir("\n📡 SENSORES E AÇÕES ESPECIAIS");
                        imprimir("- monitorar <tipo> <nomeRobo> <x> <y> <z> - Usa sensor do robô:");
                        imprimir("    • Exemplo: monitorar iluminacao Sasquatch 30 40 0");
                        imprimir("    • Retorna: 'Iluminado' ou 'Sombra' (para sensor de iluminação)");
                        imprimir("- dispararLaser <nomeRobo> - Aciona laser (apenas robôs laser)");
                        imprimir("    • Destrói todos robôs no caminho na direção atual");

                        imprimir("\n🗑️  GERENCIAMENTO");
                        imprimir("- destruirRobo <nomeRobo> - Remove robô do ambiente");
                        imprimir("- removerObstaculo <x> <y> - Elimina obstáculo na posição especificada");

                        imprimir("\n❓ AJUDA E EXEMPLOS");
                        imprimir("- testesExemplos - Mostra exemplos práticos de comandos");
                        imprimir("- help - Exibe esta mensagem de ajuda");
                        imprimir("- sair - Encerra o simulador");

                        imprimir("\n💡 DICAS:");
                        imprimir("- Digite qualquer comando sem parâmetros para ver ajuda específica");
                        imprimir("- Sensores consomem bateria a cada uso");

                        imprimir("\n⚙️  CONFIGURAÇÃO INICIAL PRÉ-DEFINIDA:");
                        imprimir("- 7 robôs criados automaticamente (digite 'listarRobos' para ver)");
                        imprimir("- 4 obstáculos posicionados (digite 'listarObstaculos' para ver)");
                        imprimir("- 3 robôs com sensores pré-instalados");
                        break;

                    default:
                        imprimir("Comando desconhecido: '" + comando + "'");
                        imprimir("Digite 'help' para ver a lista de comandos disponíveis");
                }
            // checa por exceções
            } catch (NumberFormatException e) {
                imprimir("Erro: Parâmetro numérico inválido");
                imprimir("Digite o comando sem parâmetros para ver a ajuda específica");
            } catch (Exception e) {
                imprimir("Erro ao executar comando: " + e.getMessage());
                imprimir("Digite 'help' para ver a lista de comandos disponíveis");
            }
        }
        imprimir("Simulação encerrada. Até logo!");
    }

    private static void monitorarSensor(String tipo, Robo robo, int x, int y, int z, Ambiente ambiente) {
        // Usa sensor do robô
        if (tipo.equalsIgnoreCase("iluminacao")) {
            imprimir(robo.usarSensorIluminacao(x, y, z, ambiente));
        } else if (tipo.equalsIgnoreCase("pressao")) {
            imprimir(robo.usarSensorPressao(x, y, z, ambiente));
        } else {
            imprimir("Tipo de sensor desconhecido. Use 'iluminacao' ou 'pressao'");
        }
    }
    
    private static String ler(Scanner scanner) {
        // Lê uma linha do console
        return scanner.nextLine();
    }

    private static void imprimir(String str) {
        // Imprime uma mensagem no console
        System.out.println(str);
    }

    private static Robo buscarRobo(Ambiente ambiente, String nome) {
        // Busca um robô pelo nome no ambiente
        for (Robo r : ambiente.retornarRobosAtivos()) {
            if (r.retornarNome().equalsIgnoreCase(nome)) {
                return r;
            }
        }
        return null;
    }

    private static String coordenadas(Robo r) {
        // Retorna as coordenadas do robô em formato de string
        if (r instanceof RoboAereo) {
            int[] xy = r.exibirPosicao();
            int z = ((RoboAereo) r).exibirAltura();
            return String.format("(%d, %d, %d)", xy[0], xy[1], z);
        } else {
            int[] xy = r.exibirPosicao();
            return String.format("(%d, %d)", xy[0], xy[1]);
        }
    }
}