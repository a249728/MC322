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
                            String h = partes[4];
                            ambiente = new Ambiente(c, l, a, h);
                            imprimir("Ambiente criado com sucesso.");
                        } else {
                            imprimir("Uso: criarAmbiente <comprimento> <largura> <altura> <horario>");
                        }
                        break;

                    case "criarRobo":
                        if (ambiente != null && partes.length >= 3) {
                            String tipo = partes[1];
                            String nome = partes[2];
                            Robo robo = null;
                            switch (tipo) {
                                case "base":
                                    robo = new Robo(nome, 0, 0, "Norte");
                                    break;
                                case "terrestre":
                                    robo = new RoboTerrestre(nome, 0, 0, "Norte", 100);
                                    break;
                                case "aereo":
                                    robo = new RoboAereo(nome, 0, 0, "Norte", 0, 99);
                                    break;
                                case "subterraneo":
                                    robo = new RoboSubterraneo(nome, 0, 0, "Norte", 0, -99);
                                    break;
                                case "laser":
                                    robo = new RoboLaser(nome, 0, 0, "Norte", 100, 10);
                                    break;
                                case "corredor":
                                    robo = new RoboCorredor(nome, 0, 0, "Norte", 200, 100);
                                    break;
                                case "criador":
                                    robo = new RoboCriador(nome, 0, 0, "Norte", 1, 499);
                                    break;
                                default:
                                    imprimir("Tipo inválido.");
                            }
                            if (robo != null) {
                                ambiente.adicionarRobo(robo);
                                imprimir("Robo " + nome + " criado.");
                            }
                        } else {
                            imprimir("Uso: criarRobo <tipo> <nome>");
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

                    case "mover":
                        if (ambiente != null && partes.length >= 2) {
                            Robo robo = buscarRobo(ambiente, partes[1]);
                            if (robo == null) {
                                imprimir("Robo não encontrado.");
                                break;
                            }
                            if (robo instanceof RoboAereo && partes.length == 5) {
                                ((RoboAereo) robo).mover(Integer.parseInt(partes[2]), Integer.parseInt(partes[3]), Integer.parseInt(partes[4]), ambiente);
                            } else if (robo instanceof RoboSubterraneo && partes.length == 5) {
                                ((RoboSubterraneo) robo).mover(Integer.parseInt(partes[2]), Integer.parseInt(partes[3]), Integer.parseInt(partes[4]), ambiente);
                            } else if (robo instanceof RoboCorredor && partes.length == 3) {
                                ((RoboCorredor) robo).mover(Integer.parseInt(partes[2]), ambiente);
                            } else if (robo instanceof RoboTerrestre && partes.length == 4) {
                                ((RoboTerrestre) robo).mover(Integer.parseInt(partes[2]), Integer.parseInt(partes[3]), ambiente);
                            } else if (partes.length == 4) {
                                robo.mover(Integer.parseInt(partes[2]), Integer.parseInt(partes[3]), ambiente);
                            } else {
                                imprimir("Parâmetros incorretos para mover.");
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