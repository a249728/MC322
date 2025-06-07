import java.io.FileWriter;
import java.io.IOException;

public class Logger {
    private static final String LOG_FILE = "Lab05/log.txt";

    static {
        // Limpa o arquivo ao iniciar a aplicação
        try (FileWriter writer = new FileWriter(LOG_FILE, false)) {
            // Apenas abre o arquivo em modo overwrite para limpá-lo
        } catch (IOException e) {
            System.err.println("Erro ao limpar o log: " + e.getMessage());
        }
    }

    public static void log(String mensagem) {
        try (FileWriter writer = new FileWriter(LOG_FILE, true)) {
            writer.write(mensagem + "\n");
        } catch (IOException e) {
            System.err.println("Erro ao escrever no log: " + e.getMessage());
        }
    }
}
