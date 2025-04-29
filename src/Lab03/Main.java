import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        
        // Cria um ambiente
        Ambiente ilhaMecanimais = new Ambiente(500, 500, 500, "15:00");
        imprimir("Criando o ambiente Ilha Base de dimensões 500x500x500");

        // Cria um robo Gerador que cria o restante dos robos
        RoboGerador JeffRosen = new RoboGerador("JeffRosen", 0, 0, "Norte", 1, 499);
        ilhaMecanimais.adicionarRobo(JeffRosen);
        Robo Sasquatch = JeffRosen.gerarRobo(ilhaMecanimais, "Sasquatch");
        JeffRosen.mover(0, 1, 0, ilhaMecanimais);
        RoboTerrestre Rex = JeffRosen.gerarRoboTerrestre(ilhaMecanimais, "Rex", 100);
        JeffRosen.mover(0, 1, 0, ilhaMecanimais);
        RoboAereo Unicornio = JeffRosen.gerarRoboAereo(ilhaMecanimais, "Unicornio", 0, 99);
        JeffRosen.mover(0, 1, 0, ilhaMecanimais);
        RoboSubterraneo Corujao = JeffRosen.gerarRoboSubterraneo(ilhaMecanimais, "Corujao", 0, -99);
        JeffRosen.mover(0, 1, 0, ilhaMecanimais);
        RoboLaser Komodo = JeffRosen.gerarRoboLaser(ilhaMecanimais, "Komodo", 100, 10);
        JeffRosen.mover(0, 1, 0, ilhaMecanimais);
        RoboCorredor Mouse = JeffRosen.gerarRoboCorredor(ilhaMecanimais, "Mouse", 200, 100);
        
        Rex.adicionarSensorPressao(100, 100);
        Unicornio.adicionarSensorIluminacao(100, 100);
        Sasquatch.adicionarSensorPressao(10, 10);
        Sasquatch.adicionarSensorIluminacao(10, 10);
        
        ilhaMecanimais.criarObstaculo(TipoObstaculo.PEDRA, 3, 3);
        ilhaMecanimais.criarObstaculo(TipoObstaculo.ARVORE, 20, 10);
        ilhaMecanimais.criarObstaculo(TipoObstaculo.BURACO, 10, 20);
        ilhaMecanimais.criarObstaculo(TipoObstaculo.LAGO, 50, 50);
        
        Scanner scanner = new Scanner(System.in);
        console(scanner, ilhaMecanimais);
        scanner.close();
    }

    public static void console(Scanner scanner, Ambiente ambiente) {
        imprimir("Bem-vindo ao simulador de robôs!");
        imprimir("Você pode digitar 'help' a qualquer momento para ver a lista de comandos disponíveis.");

        while (true) {
            imprimir(">>> ");
            String linha = ler(scanner);
            if (linha.equalsIgnoreCase("sair")) break;

            String[] partes = linha.split(" ");
            if (partes.length < 1) continue;

            String comando = partes[0];

            try {
                switch (comando) {
                    case "gerarRobo":
                        if (ambiente != null && partes.length >= 4) {
                            String nomeGerador = partes[1];
                            String tipo = partes[2];
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
                                            imprimir("Uso: gerarRobo <nomeGerador> terrestre <nomeNovo> <velocidadeMaxima>");
                                        }
                                        break;
                                    case "aereo":
                                        if (partes.length >= 6) {
                                            int z = Integer.parseInt(partes[4]);
                                            int zmax = Integer.parseInt(partes[5]);
                                            novo = ((RoboGerador) gerador).gerarRoboAereo(ambiente, nomeNovo, z, zmax);
                                        } else {
                                            imprimir("Uso: gerarRobo <nomeGerador> aereo <nomeNovo> <alturaInicial> <alturaMaxima>");
                                        }
                                        break;
                                    case "subterraneo":
                                        if (partes.length >= 6) {
                                            int z = Integer.parseInt(partes[4]);
                                            int zmin = Integer.parseInt(partes[5]);
                                            novo = ((RoboGerador) gerador).gerarRoboSubterraneo(ambiente, nomeNovo, z, zmin);
                                        } else {
                                            imprimir("Uso: gerarRobo <nomeGerador> subterraneo <nomeNovo> <profundidadeInicial> <profundidadeMinima>");
                                        }
                                        break;
                                    case "laser":
                                        if (partes.length >= 6) {
                                            int vmax = Integer.parseInt(partes[4]);
                                            int alcance = Integer.parseInt(partes[5]);
                                            novo = ((RoboGerador) gerador).gerarRoboLaser(ambiente, nomeNovo, vmax, alcance);
                                        } else {
                                            imprimir("Uso: gerarRobo <nomeGerador> laser <nomeNovo> <velocidadeMaxima> <alcance>");
                                        }
                                        break;
                                    case "corredor":
                                        if (partes.length >= 6) {
                                            int vmax = Integer.parseInt(partes[4]);
                                            int vmin = Integer.parseInt(partes[5]);
                                            novo = ((RoboGerador) gerador).gerarRoboCorredor(ambiente, nomeNovo, vmax, vmin);
                                        } else {
                                            imprimir("Uso: gerarRobo <nomeGerador> corredor <nomeNovo> <velocidadeMaxima> <velocidadeMinima>");
                                        }
                                        break;
                                    case "gerador":
                                        if (partes.length >= 6) {
                                            int z = Integer.parseInt(partes[4]);
                                            int zmax = Integer.parseInt(partes[5]);
                                            novo = ((RoboGerador) gerador).gerarRoboGerador(ambiente, nomeNovo, z, zmax);
                                        } else {
                                            imprimir("Uso: gerarRobo <nomeGerador> gerador <nomeNovo> <alturaInicial> <alturaMaxima>");
                                        }
                                        break;
                                    default:
                                        imprimir("Tipo inválido para geração.");
                                        break;
                                }
                                if (novo != null) {
                                    imprimir("Robo " + nomeNovo + " do tipo " + tipo + " gerado por " + nomeGerador + " em " + coordenadas(novo));
                                } else {
                                    imprimir("Falha ao gerar o robô.");
                                }
                            } else {
                                imprimir("Robo " + nomeGerador + " não é um gerador.");
                            }
                        } else {
                            imprimir("Uso: gerarRobo <nomeGerador> <tipo> <nomeNovo> [parametros adicionais]");
                        }
                        break;

                    case "mover":
                        if (ambiente != null && partes.length >= 2) {
                            Robo robo = buscarRobo(ambiente, partes[1]);
                            if (robo == null) {
                                imprimir("Robo não encontrado.");
                                break;
                            }
                            boolean sucesso = false;
                            if (robo instanceof RoboAereo && partes.length == 5) {
                                sucesso = ((RoboAereo) robo).mover(Integer.parseInt(partes[2]), Integer.parseInt(partes[3]), Integer.parseInt(partes[4]), ambiente);
                            } else if (robo instanceof RoboSubterraneo && partes.length == 5) {
                                sucesso = ((RoboSubterraneo) robo).mover(Integer.parseInt(partes[2]), Integer.parseInt(partes[3]), Integer.parseInt(partes[4]), ambiente);
                            } else if (robo instanceof RoboCorredor && partes.length == 3) {
                                sucesso = ((RoboCorredor) robo).mover(Integer.parseInt(partes[2]), ambiente);
                            } else if (robo instanceof RoboTerrestre && partes.length == 4) {
                                sucesso = ((RoboTerrestre) robo).mover(Integer.parseInt(partes[2]), Integer.parseInt(partes[3]), ambiente);
                            } else if (partes.length == 4) {
                                sucesso = robo.mover(Integer.parseInt(partes[2]), Integer.parseInt(partes[3]), ambiente);
                            } else {
                                imprimir("Uso: mover <nomeRobo> [parametros de movimento]");
                            }
                            if (sucesso) {
                                imprimir("Movimento realizado com sucesso.");
                            } else {
                                imprimir("Movimento bloqueado: obstáculo, limites ou parâmetros inválidos.");
                            }
                        } else {
                            imprimir("Uso: mover <nomeRobo> [parametros de movimento]");
                        }
                        break;


                    case "exibirPosicao":
                        if (ambiente != null && partes.length == 2) {
                            Robo robo = buscarRobo(ambiente, partes[1]);
                            if (robo != null) {
                                imprimir(robo.retornarNome() + " está na posição " + coordenadas(robo));
                            } else {
                                imprimir("Robo não encontrado.");
                            }
                        }
                        break;

                    case "checarLimites":
                        if (ambiente != null && partes.length == 2) {
                            Robo robo = buscarRobo(ambiente, partes[1]);
                            if (robo != null) {
                                int[] xy = robo.exibirPosicao();
                                boolean dentro;
                                if (robo instanceof RoboAereo) {
                                    int z = ((RoboAereo) robo).exibirAltura();
                                    dentro = ambiente.dentroDosLimitesAereo(xy[0], xy[1], z);
                                } else {
                                    dentro = ambiente.dentroDosLimites(xy[0], xy[1]);
                                }
                                imprimir("O robo " + robo.retornarNome() + (dentro ? " está " : " não está ") + "dentro dos limites.");
                            } else {
                                imprimir("Robo não encontrado.");
                            }
                        }
                        break;

                    case "listarRobos":
                        if (ambiente != null) {
                            for (Robo r : ambiente.retornarRobosAtivos()) {
                                imprimir(r.retornarNome() + " em " + coordenadas(r));
                            }
                        } else {
                            imprimir("Ambiente não criado ainda.");
                        }
                        break;

                    case "listarObstaculos":
                        if (ambiente != null) {
                            for (Obstaculo o : ambiente.retornarObstaculos()) {
                                imprimir(o.getObstaculo() + " em (" + o.getPosicaoX() + ", " + o.getPosicaoY() + ")");
                            }
                        } else {
                            imprimir("Ambiente não criado ainda.");
                        }

                    case "destruirRobo":
                        if (ambiente != null && partes.length == 2) {
                            Robo r = buscarRobo(ambiente, partes[1]);
                            if (r != null) {
                                ambiente.destruirRobo(r);
                                imprimir("Robo " + partes[1] + " destruído.");
                            } else {
                                imprimir("Robo não encontrado.");
                            }
                        }
                        break;

                    case "mudarDirecao":
                        if (ambiente != null && partes.length == 3) {
                            Robo robo = buscarRobo(ambiente, partes[1]);
                            if (robo != null) {
                                robo.mudarDirecao(partes[2]);
                                imprimir(robo.retornarNome() + " agora está olhando para " + partes[2]);
                            } else {
                                imprimir("Robo não encontrado.");
                            }
                        }
                        break;

                    case "dispararLaser":
                        if (ambiente != null && partes.length == 2) {
                            Robo robo = buscarRobo(ambiente, partes[1]);
                            if (robo instanceof RoboLaser) {
                                int n = ((RoboLaser) robo).dispararLaser(ambiente);
                                imprimir("Laser disparado! " + n + " robô(s) destruído(s).");
                            } else {
                                imprimir("Esse robô não possui laser.");
                            }
                        }
                        break;

                    case "removerObstaculo":
                        if (ambiente != null && partes.length == 3) {
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
                                alvo.removerObstaculo(alvo, ambiente);
                                imprimir("Obstáculo removido em (" + x + ", " + y + ")");
                            } else {
                                imprimir("Nenhum obstáculo encontrado na posição.");
                            }
                        } else {
                            imprimir("Uso: removerObstaculo <x> <y>");
                        }
                        break;

                    case "monitorar":
                        if (ambiente != null && partes.length == 6) {
                            String tipo = partes[1];
                            String nome = partes[2];
                            int x = Integer.parseInt(partes[3]);
                            int y = Integer.parseInt(partes[4]);
                            int z = Integer.parseInt(partes[5]);
                            Robo robo = buscarRobo(ambiente, nome);
                            if (robo != null) {
                                monitorarSensor(tipo, robo, x, y, z, ambiente);
                            } else {
                                imprimir("Robo não encontrado.");
                            }
                        } else {
                            imprimir("Uso: monitorar <tipo> <robo> <x> <y> <z>");
                        }
                        break;

                    case "help":
                        imprimir("Comandos disponíveis:");
                        imprimir("1. gerarRobo <nomeGerador> <nomeNovo> [parâmetros adicionais dependendo do tipo] - Gera um novo robô a partir de um robô gerador.");
                        imprimir("2. mover <nomeRobo> <distancia> [parâmetros adicionais dependendo do tipo] - Move o robô. Se a direção não for informada, usa a atual.");
                        imprimir("3. exibirPosicao <nomeRobo> - Mostra a posição atual do robô.");
                        imprimir("4. checarLimites <nomeRobo> - Verifica se o robô está dentro dos limites do ambiente.");
                        imprimir("5. listarRobos - Lista todos os robôs ativos no ambiente.");
                        imprimir("6. destruirRobo <nomeRobo> - Destrói o robô informado.");
                        imprimir("7. mudarDirecao <nomeRobo> <novaDirecao> - Altera a direção do robô.");
                        imprimir("8. dispararLaser <nomeRobo> - Dispara o laser do robô (caso ele tenha).");
                        imprimir("9. removerObstaculo <x> <y> - Remove o obstáculo localizado na posição informada.");
                        imprimir("10. monitorar <nomeRobo> <x> <y> <z> <tipoSensor> - Usa o sensor do robô para monitorar uma posição.");
                        imprimir("11. sair - Encerra a simulação.");
                        imprimir("12. help - Mostra esta lista de comandos.");
                        imprimir("13. listarObstaculos - Lista todos os obstáculos do ambiente.");
                    
                        imprimir("\nTipos de robôs disponíveis:");
                        imprimir("- Robo (ou base): Robô terrestre padrão.");
                        imprimir("- RoboAereo (ou aereo): Robô que voa e se movimenta em três dimensões.");
                        imprimir("- RoboGerador (ou gerador): Robô aéreo que pode criar novos robôs.");
                        imprimir("- RoboLaser (ou laser): Robô equipado com um laser destrutivo.");
                        imprimir("- RoboTerrestre (ou terrestre): Robô especializado em se mover pelo solo.");
                        imprimir("- RoboSubterraneo (ou subterraneo): Robô que se desloca sob a terra, escapando de obstáculos.");
                        imprimir("- RoboCorredor (ou corredor): Robô de alta velocidade que anda apenas em linha reta.");
                    
                        imprimir("\nTipos de obstáculos disponíveis:");
                        imprimir("- PEDRA: Ocupa 3x3 no solo e altura 3.");
                        imprimir("- ARVORE: Ocupa 1x1 no solo e altura 10.");
                        imprimir("- BURACO: Ocupa 5x5 no solo e profundidade -5.");
                        imprimir("- LAGO: Ocupa 21x21 no solo e profundidade -8.");
                        break;
                    

                    default:
                        imprimir("Comando desconhecido: " + comando);
                }
            } catch (Exception e) {
                imprimir("Erro ao executar comando: " + e.getMessage());
            }
        }

        imprimir("Simulação encerrada.");
    }

    // Função para monitorar os sensores
    private static void monitorarSensor(String tipo, Robo robo, int x, int y, int z, Ambiente ambiente) {
        if (tipo.equals("iluminacao")) {
            String resultado = robo.usarSensorIluminacao(x, y, z, ambiente);
            imprimir(resultado);
        } else if (tipo.equals("pressao")) {
            String resultado = robo.usarSensorPressao(x, y, z, ambiente);
            imprimir(resultado);
        } else {
            imprimir("Tipo de sensor desconhecido: " + tipo);
        }
    }
    
    private static String ler(Scanner scanner) {
        return scanner.nextLine();
    }

    private static void imprimir(String str) {
        System.out.println(str);
    }

    private static Robo buscarRobo(Ambiente ambiente, String nome) {
        for (Robo r : ambiente.retornarRobosAtivos()) {
            if (r.retornarNome().equals(nome)) return r;
        }
        return null;
    }

    private static String coordenadas(Robo r) {
        if (r instanceof RoboAereo) {
            int[] xy = r.exibirPosicao();
            int z = ((RoboAereo) r).exibirAltura();
            return "(" + xy[0] + ", " + xy[1] + ", " + z + ")";
        } else {
            int[] xy = r.exibirPosicao();
            return "(" + xy[0] + ", " + xy[1] + ")";
        }
    }
}