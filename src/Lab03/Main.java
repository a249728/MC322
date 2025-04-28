import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        console(scanner);
        scanner.close();
    }

    public static void console(Scanner scanner) {
        Ambiente ambiente = null;

        imprimir("Simulador iniciado. Digite comandos (ou 'sair' para encerrar):");

        while (true) {
            imprimir(">>> ");
            String linha = ler(scanner);
            if (linha.equalsIgnoreCase("sair")) break;

            String[] partes = linha.split(" ");
            if (partes.length < 1) continue;

            String comando = partes[0];

            try {
                switch (comando) {
                    case "criarAmbiente":
                        if (partes.length == 5) {
                            int c = Integer.parseInt(partes[1]);
                            int l = Integer.parseInt(partes[2]);
                            int a = Integer.parseInt(partes[3]);
                            String sol = partes[4];
                            ambiente = new Ambiente(c, l, a, sol);
                            imprimir("Ambiente criado com sucesso.");
                        } else {
                            imprimir("Uso: criarAmbiente <comprimento> <largura> <altura> <posSol>");
                        }
                        break;

                    case "criarRobo":
                        if (ambiente != null && partes.length >= 6) {
                            String tipo = partes[1];
                            String nome = partes[2];
                            int x = Integer.parseInt(partes[3]);
                            int y = Integer.parseInt(partes[4]);
                            String dir = partes[5];
                            Robo robo = null;
                            switch (tipo) {
                                case "base":
                                    robo = new Robo(nome, x, y, dir);
                                    break;
                                case "terrestre":
                                    if (partes.length >= 7) {
                                        int vmaxT = Integer.parseInt(partes[6]);
                                        robo = new RoboTerrestre(nome, x, y, dir, vmaxT);
                                    } else {
                                        imprimir("Uso: criarRobo terrestre <nome> <x> <y> <direcao> <velocidadeMaxima>");
                                    }
                                    break;
                                case "aereo":
                                    if (partes.length >= 8) {
                                        int zA = Integer.parseInt(partes[6]);
                                        int zmaxA = Integer.parseInt(partes[7]);
                                        robo = new RoboAereo(nome, x, y, dir, zA, zmaxA);
                                    } else {
                                        imprimir("Uso: criarRobo aereo <nome> <x> <y> <direcao> <alturaInicial> <alturaMaxima>");
                                    }
                                    break;
                                case "subterraneo":
                                    if (partes.length >= 8) {
                                        int zS = Integer.parseInt(partes[6]);
                                        int zminS = Integer.parseInt(partes[7]);
                                        robo = new RoboSubterraneo(nome, x, y, dir, zS, zminS);
                                    } else {
                                        imprimir("Uso: criarRobo subterraneo <nome> <x> <y> <direcao> <profundidadeInicial> <profundidadeMinima>");
                                    }
                                    break;
                                case "laser":
                                    if (partes.length >= 8) {
                                        int vmaxL = Integer.parseInt(partes[6]);
                                        int alcance = Integer.parseInt(partes[7]);
                                        robo = new RoboLaser(nome, x, y, dir, vmaxL, alcance);
                                    } else {
                                        imprimir("Uso: criarRobo laser <nome> <x> <y> <direcao> <velocidadeMaxima> <alcance>");
                                    }
                                    break;
                                case "corredor":
                                    if (partes.length >= 8) {
                                        int vmaxC = Integer.parseInt(partes[6]);
                                        int vminC = Integer.parseInt(partes[7]);
                                        robo = new RoboCorredor(nome, x, y, dir, vmaxC, vminC);
                                    } else {
                                        imprimir("Uso: criarRobo corredor <nome> <x> <y> <direcao> <velocidadeMaxima> <velocidadeMinima>");
                                    }
                                    break;
                                case "gerador":
                                    if (partes.length >= 8) {
                                        int zC = Integer.parseInt(partes[6]);
                                        int zmaxC = Integer.parseInt(partes[7]);
                                        robo = new RoboGerador(nome, x, y, dir, zC, zmaxC);
                                    } else {
                                        imprimir("Uso: criarRobo gerador <nome> <x> <y> <direcao> <alturaInicial> <alturaMaxima>");
                                    }
                                    break;
                                default:
                                    imprimir("Tipo inválido.");
                                    break;
                            }
                            if (robo != null) {
                                ambiente.adicionarRobo(robo);
                                imprimir("Robo " + nome + " criado em " + coordenadas(robo) + " direção " + dir + ".");
                            }
                        } else {
                            imprimir("Uso: criarRobo <tipo> <nome> <x> <y> <direcao> [parametros adicionais dependendo do tipo]");
                        }
                        break;

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

                        case "criarObstaculo":
                        if (ambiente != null && partes.length == 4) {
                            String tipo = partes[1];
                            int x = Integer.parseInt(partes[2]);
                            int y = Integer.parseInt(partes[3]);
                            Obstaculo obs = new Obstaculo(tipo, x, y);
                            if (obs.criarObstaculo(ambiente, tipo, x, y) != null) {
                                imprimir("Obstáculo do tipo " + tipo + " criado em (" + x + ", " + y + ")");
                            } else {
                                imprimir("Não foi possível criar o obstáculo nesta posição.");
                            }
                        } else {
                            imprimir("Uso: criarObstaculo <tipo> <x> <y>");
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

                    case "criarSensorIluminacao":
                        if (ambiente != null && partes.length == 4) {
                            String nome = partes[1];
                            double raio = Double.parseDouble(partes[2]);
                            int bateria = Integer.parseInt(partes[3]);
                            Robo robo = buscarRobo(ambiente, nome);
                            if (robo != null) {
                                robo.adicionarSensorIluminacao(raio, bateria);
                                imprimir("Sensor de iluminação criado para " + nome + " com raio " + raio + " e bateria " + bateria + ".");
                            } else {
                                imprimir("Robo não encontrado.");
                            }
                        } else {
                            imprimir("Uso: criarSensorIluminacao <nomeRobo> <raio> <bateria>");
                        }
                        break;
                    
                    case "criarSensorPressao":
                        if (ambiente != null && partes.length == 4) {
                            String nome = partes[1];
                            double raio = Double.parseDouble(partes[2]);
                            int bateria = Integer.parseInt(partes[3]);
                            Robo robo = buscarRobo(ambiente, nome);
                            if (robo != null) {
                                robo.adicionarSensorPressao(raio, bateria);
                                imprimir("Sensor de pressão criado para " + nome + " com raio " + raio + " e bateria " + bateria + ".");
                            } else {
                                imprimir("Robo não encontrado.");
                            }
                        } else {
                            imprimir("Uso: criarSensorPressao <nomeRobo> <raio> <bateria>");
                        }
                        break;
                    

                    case "monitorarIluminacao":
                        if (ambiente != null && partes.length == 5) {
                            String nome = partes[1];
                            int x = Integer.parseInt(partes[2]);
                            int y = Integer.parseInt(partes[3]);
                            int z = Integer.parseInt(partes[4]);
                            Robo robo = buscarRobo(ambiente, nome);
                            if (robo != null) {
                                String resultado = robo.usarSensorIluminacao(x, y, z, ambiente);
                                imprimir(resultado);
                            } else {
                                imprimir("Robo não encontrado.");
                            }
                        } else {
                            imprimir("Uso: monitorarIluminacao <robo> <x> <y> <z>");
                        }
                        break;

                    case "monitorarPressao":
                        if (ambiente != null && partes.length == 5) {
                            String nome = partes[1];
                            int x = Integer.parseInt(partes[2]);
                            int y = Integer.parseInt(partes[3]);
                            int z = Integer.parseInt(partes[4]);
                            Robo robo = buscarRobo(ambiente, nome);
                            if (robo != null) {
                                String resultado = robo.usarSensorPressao(x, y, z, ambiente);
                                imprimir(resultado);
                            } else {
                                imprimir("Robo não encontrado.");
                            }
                        } else {
                            imprimir("Uso: monitorarPressao <robo> <x> <y> <z>");
                        }
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