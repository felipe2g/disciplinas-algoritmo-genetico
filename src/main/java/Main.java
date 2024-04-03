import Common.GlobalVariables;
import Common.Initializers.DisciplineInitializer;
import Common.Initializers.TeacherInitializer;
import Common.WeekDate;
import Entities.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import java.io.IOException;
import java.util.*;

import static Common.Initializers.FullCourse.generateRandomFullCourseLine;
import static Export.ExportFile.exportToHTMLFile;


public class Main {
    public static void main(String[] args) throws IOException {
        GlobalVariables.START_TIME = System.nanoTime();

        ArrayList<Individual> population = initialization();
        ArrayList<Individual> fitnessPopulation = fitness(population);

        //System.out.println(population);

        //exportToHTMLFile(fitnessPopulation);
    }

    public static ArrayList<Individual> initialization() {
        ArrayList<Individual> population = new ArrayList<Individual>();

        for (int i = 1; i <= GlobalVariables.POPULATION_SIZE; i++) {
            population.add(new Individual());
        }

        return population;
    }

    public static ArrayList<Individual> fitness(ArrayList<Individual> population) {
        for (Individual individual : population) {
            Double rate = 0.0;

            int individualSize = individual.getCourse().size()/WeekDate.getList().size();
            int weekDateListSize = WeekDate.getList().size();

            for (int k = 0; k < individualSize; k++) {
                for (int i = 0; i < individualSize; i++) {
                    for (int j = 0; j < (weekDateListSize - 1) - k; j++) {
                        for (int l = 0; l < GlobalVariables.DISCIPLINES_PER_DAY; l++) {
                            int firstItemToCompare = i + (k * weekDateListSize);
                            int secondItemToCompare = i + (j * weekDateListSize) + (k * weekDateListSize) + weekDateListSize;

                            Discipline firstItem = individual.getCourse().get(firstItemToCompare).getDisciplines().get(l);
                            Discipline secondItem = individual.getCourse().get(secondItemToCompare).getDisciplines().get(l);

                            if(firstItem.getTeacher().getId().equals(secondItem.getTeacher().getId())) {
                                rate++;
                                firstItem.setScheduleConflict(true);
                                secondItem.setScheduleConflict(true);

                                System.out.println("@@@ Conflito => " + firstItem.getTeacher().getName() + " e " + secondItem.getTeacher().getName());
                            }

                            System.out.println("HORÁRIO " + l);
                            System.out.println(firstItemToCompare + " => " + secondItemToCompare);
                            System.out.println(individual.getCourse().get(firstItemToCompare).getWeekDate().getKey() + " => " + individual.getCourse().get(secondItemToCompare).getWeekDate().getKey());
                        }
                    }
                }
            }

            individual.setRate(rate);
        }

        return population;
    }
}