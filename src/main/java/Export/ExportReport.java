package Export;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class ExportReport {
    public static void addTimeStatisticLineToFile(String log) throws IOException {
        try {
            FileWriter writer = new FileWriter("statistics.csv", true);
            BufferedWriter bufferedWriter = new BufferedWriter(writer);

            bufferedWriter.write(log);
            bufferedWriter.newLine();
            bufferedWriter.close();
        } catch (IOException e) {
            System.err.println("Erro ao escrever no arquivo: " + e.getMessage());
        }
    }
}
