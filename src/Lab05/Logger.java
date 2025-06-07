import java.io.FileWriter;
import java.io.IOException;

public class Logger {
    private static final String LOG_FILE = "log.txt";

    public static void log(String mensagem) {
        try (FileWriter writer = new FileWriter(LOG_FILE, true)) {
            writer.write(mensagem + "\n");
        } catch (IOException e) {
            System.err.println("Erro ao escrever no log: " + e.getMessage());
        }
    }
}
