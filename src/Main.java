import Common.GlobalVariables;
import Common.Initializers.DisciplineInitializer;
import Common.Initializers.FullCourse;
import Common.Initializers.TeacherInitializer;
import Common.WeekDate;
import Entities.Discipline;
import Entities.Individual;
import Entities.Period;
import Entities.Schedule;
import Entities.Teacher;

import java.util.ArrayList;
import java.util.UUID;
import java.util.concurrent.TimeUnit;


public class Main {
    public static void main(String[] args) {
        GlobalVariables.START_TIME = System.nanoTime();

        ArrayList<Teacher> teachers = TeacherInitializer.initializeTeachers();
        ArrayList<Discipline> disciplines = DisciplineInitializer.initializeDisciplines();

        ArrayList<Individual> population = initialization();

        fitness(population);

    }

    public static ArrayList<Individual> initialization() {
        ArrayList<Individual> population = new ArrayList<Individual>();

        for(int i = 1; i < GlobalVariables.POPULATION_SIZE; i++) {
            population.add(new Individual());
        }

        return population;
    }

    public static void fitness(ArrayList<Individual> population) {
            for(Individual individual: population) {
                Double rate = 0.0;

                for (int i = 0; i < GlobalVariables.PERIOD_COUNT; i++) {
                    ArrayList<UUID> teacherIds = new ArrayList<>();
                    for (int dayNumber = 0; dayNumber < WeekDate.getList().size(); dayNumber++) {
                        for (int j = 0; j < GlobalVariables.DISCIPLINES_PER_DAY; j++) {
                            teacherIds.add(individual.getCourse().get(i).getSchedules().get(dayNumber).getDisciplines().get(j).getTeacher().getId());
                        }
                    }
                }



                // Verificar choques de horários
                // >> individual > course > schedules > 0 > disciplines > primeira posicao > joga no array
                // >> individual > course > schedules > 0 > disciplines > segunda posicao > joga no array
                System.out.println(individual.getCourse().get(0));
            }
    }
}