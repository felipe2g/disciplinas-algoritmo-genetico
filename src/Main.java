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

                for (int disciplineSequence = 0; disciplineSequence < GlobalVariables.DISCIPLINES_PER_DAY; disciplineSequence++) {
                    ArrayList<UUID> teachersInSameWeekDate = new ArrayList<>(TeacherInitializer.initializeTeachers().size());
                    for (int dayNumber = 0; dayNumber < WeekDate.getList().size(); dayNumber++) {
                        for (int periodNumber = 0; periodNumber < GlobalVariables.PERIOD_COUNT; periodNumber++) {
                            Period period = individual.getCourse().get(periodNumber);
                            Schedule schedule = period.getSchedules().get(dayNumber);
                            Discipline discipline = schedule.getDisciplines().get(disciplineSequence);
                            UUID teacherUUID = discipline.getTeacher().getId();

                            System.out.print(discipline.getTeacher().getName() + " | ");


                            Boolean teacherIdHasInArray = teachersInSameWeekDate.contains(teacherUUID);
                            if(!teacherIdHasInArray) {
                                teachersInSameWeekDate.add(teacherUUID);
                                break;
                            }
                            rate++;
                        }
                    }
                }

                individual.setRate(rate);
            }
        }
    }