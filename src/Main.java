import Common.GlobalVariables;
import Common.Initializers.DisciplineInitializer;
import Common.Initializers.FullCourse;
import Common.Initializers.TeacherInitializer;
import Entities.Discipline;
import Entities.Period;
import Entities.Schedule;
import Entities.Teacher;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;


public class Main {
    public static void main(String[] args) {
        GlobalVariables.START_TIME = System.nanoTime();

        ArrayList<Teacher> teachers = TeacherInitializer.initializeTeachers();
        ArrayList<Discipline> disciplines = DisciplineInitializer.initializeDisciplines();

        ArrayList<ArrayList<Period>> population = initialization(teachers, disciplines);

        fitness(population);
    }

    public static ArrayList<ArrayList<Period>> initialization(ArrayList<Teacher> teachers, ArrayList<Discipline> disciplines) {
        ArrayList<ArrayList<Period>> population = new ArrayList<>();

        for(int i = 1; i < GlobalVariables.POPULATION_SIZE; i++) {
            population.add(FullCourse.generateRandomFullCourse(teachers, disciplines));
        }

        return population;
    }

    public static void fitness(ArrayList<ArrayList<Period>> population) {
        for (int j=0; j<population.size(); j++) {
            ArrayList<Period> individual = population.get(j);
            for(Period period: individual) {
                for(Schedule schedule: period.getSchedules()) {
                    String firstDiscipline = schedule.getDisciplines().get(0).getName();
                    String secondDiscipline = schedule.getDisciplines().get(1).getName();

                    if(firstDiscipline.equals(secondDiscipline)) {
                        System.out.println("População: " + j +
                                " No período: " + period.getOrder() +
                                " Repetiu a disciplina: " + firstDiscipline +
                                " [" + firstDiscipline + ", " + secondDiscipline + "]"
                        );
                    }
                }
            }
        }
    }
}