package Export;

import Common.GlobalVariables;
import Common.WeekDate;
import Entities.Discipline;
import Entities.Individual;
import Entities.Schedule;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

public class ExportFile {
    public static void exportToHTMLFile(ArrayList<Individual> population) throws IOException {
        FileWriter arq = new FileWriter("horarios.html");
        PrintWriter gravarArq = new PrintWriter(arq);

        gravarArq.printf("<html>");

        gravarArq.printf("<style>\n" +
                ".conflict    {color: red;}\n" +
                "th, td { width: 20%%; }" +
                "</style>\n" +
                "</head>");

        gravarArq.printf("<body>");

        for (int individualIndex = 0; individualIndex < population.size(); individualIndex++) {
            Individual individual = population.get(individualIndex);
            List<Schedule> courses = individual.getCourse();
            int courseSize = courses.size();
            int weeksSize = WeekDate.getList().size();
            int cellsPerWeek = courseSize / weeksSize;

            gravarArq.printf("<h1>Indivíduo " + (individualIndex + 1) + "</h1>");
            gravarArq.printf("<h3>Nota " + individual.getRate() + "</h3>%n");

            for (int i = 0; i < courseSize / 5; i++) {
                gravarArq.printf("<table style=\"border-collapse: collapse; width:100%%; margin: 16px 0;\">%n");
                gravarArq.printf("  <thead>%n");
                gravarArq.printf("    <tr>%n");
                gravarArq.printf("      <td align=\"center\" style=\"border: 1px solid black; padding: 5px; background-color: #f2f2f2;font-weight:bold;\" colspan=" + WeekDate.getList().size() + ">" + (i + 1) + "º Período</td>%n");
                gravarArq.printf("    </tr>%n");
                gravarArq.printf("    <tr style=\"border: 1px solid black;\">%n");
                for (WeekDate weekDate : WeekDate.getList()) {
                    gravarArq.printf("      <th style=\"border: 1px solid black; padding: 5px; background-color: #f2f2f2;\">" + weekDate.getKey() + "</th>%n");
                }
                gravarArq.printf("    </tr>%n");
                gravarArq.printf("  </thead>%n");

                gravarArq.printf("  <tbody>%n");

                gravarArq.printf("    <tr>%n");
                    for (int j = 0; j < cellsPerWeek; j++) {
                        int positionToGet = ((i * 5) + j);
                        gravarArq.printf("      <td style=\"text-align: center; border: 1px solid black; padding: 5px;\">");
                        for (int l = 0; l < GlobalVariables.DISCIPLINES_PER_DAY; l++) {
                            Discipline discipline = individual.getCourse().get(positionToGet).getDisciplines().get(l);
                            gravarArq.printf("<span class=\"" + (discipline.getScheduleConflict() ? "conflict" : "") + "\">");
                            gravarArq.printf(discipline.getName() + "</br>");
                            gravarArq.printf(discipline.getTeacher().getName() + "</br>");
                            gravarArq.printf("</span>");
                        }
                        gravarArq.printf("</td>%n");
                    }
                gravarArq.printf("    </tr>%n");

                gravarArq.printf("  </tbody>%n");
                gravarArq.printf("</table>%n");
            }
        }

        gravarArq.printf("</body>");

        gravarArq.printf("</html>");

        arq.close();
    }

    public static void exportJSONToFile(ArrayList<Individual> population) throws IOException {
        FileWriter arq = new FileWriter("export.json");
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String json = ow.writeValueAsString(population);

        arq.write(json);
        arq.close();
    }
}
