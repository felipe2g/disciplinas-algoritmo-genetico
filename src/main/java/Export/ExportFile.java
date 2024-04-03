package Export;

import Common.WeekDate;
import Entities.Individual;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class ExportFile {
    public static void exportToHTMLFile(ArrayList<Individual> population) throws IOException {
        FileWriter arq = new FileWriter("horarios.html");
        PrintWriter gravarArq = new PrintWriter(arq);

        gravarArq.printf("<html>");

        gravarArq.printf("<style>\n" +
                ".conflict    {color: red;}\n" +
                "</style>\n" +
                "</head>");

        gravarArq.printf("<body>");

        for (int individualIndex = 0; individualIndex < population.size(); individualIndex++) {
            Individual individual = population.get(individualIndex);
//            ArrayList<Period> course = individual.getCourse();

            gravarArq.printf("<h1>Indivíduo " + individualIndex + "</h1>");
            gravarArq.printf("<h3>Nota " + individual.getRate() + "</h3>");


            gravarArq.printf("<table style=\"border-collapse: collapse;\">%n");
            gravarArq.printf("<thead>%n  <tr style=\"border: 1px solid black;\">%n");
            gravarArq.printf("    <th style=\"border: 1px solid black; padding: 5px; background-color: #f2f2f2;\"> Período </th>%n");
            for (WeekDate weekDate : WeekDate.getList()) {
                gravarArq.printf("    <th style=\"border: 1px solid black; padding: 5px; background-color: #f2f2f2;\">" + weekDate.getKey() + "</th>%n");
            }
            gravarArq.printf("  </tr>%n</thead>%n");

            gravarArq.printf("<tbody>%n");

//            for (Period period : course) {
//                gravarArq.printf("  <tr style=\"text-align: center\">%n <td style=\"border: 1px solid black; padding: 5px;\"> " + period.getOrder() + "º Período </td>");
//                for (Schedule schedule : period.getSchedules()) {
//                    gravarArq.printf("    <td style=\"border: 1px solid black; padding: 5px;\">%n");
//                    for (int i = 0; i < GlobalVariables.DISCIPLINES_PER_DAY; i++) {
//                        String disciplineName = schedule.getDisciplines().get(i).getName();
//                        String teacherName = schedule.getDisciplines().get(i).getTeacher().getName();
//                        if(schedule.getDisciplines().get(i).getScheduleConflict()) {
//                            gravarArq.printf(disciplineName + "</br><b class=\"conflict\">" + teacherName + "</b></br>");
//                        } else {
//                            gravarArq.printf(disciplineName + "</br><b>" + teacherName + "</b></br>");
//                        }
//                        gravarArq.printf(schedule.getWeekDate().getKey());
//                    }
//                    gravarArq.printf("    </td>%n");
//                }
//                gravarArq.printf("  </tr>%n");
//            }

            gravarArq.printf("</tbody></table>");
        }

        gravarArq.printf("</body>");

        gravarArq.printf("</html>");

        arq.close();
    }
}
