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

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;
import java.util.concurrent.TimeUnit;


public class Main {
    public static void main(String[] args) throws IOException {
        GlobalVariables.START_TIME = System.nanoTime();

        ArrayList<Teacher> teachers = TeacherInitializer.initializeTeachers();
        ArrayList<Discipline> disciplines = DisciplineInitializer.initializeDisciplines();

        ArrayList<Individual> population = initialization();

        FileWriter arq = new FileWriter("horarios.html");
        PrintWriter gravarArq = new PrintWriter(arq);

        for (int individualIndex = 0; individualIndex < population.size(); individualIndex++) {
            gravarArq.printf("+-- Indivíduo " + individualIndex + " --+%n");
            Individual individual = population.get(individualIndex);
            ArrayList<Period> course = individual.getCourse();

            HashMap<WeekDate, ArrayList<Schedule>> hashMap = generateHashMap();

            for (int courseIndex = 0; courseIndex < course.size(); courseIndex++) {
                ArrayList<Schedule> schedules = course.get(courseIndex).getSchedules();
                for (int schedulesIndex = 0; schedulesIndex < schedules.size(); schedulesIndex++) {
                    Schedule schedule = schedules.get(schedulesIndex);
                    ArrayList<Schedule> scheduleListWithInsert = hashMap.get(schedule.getWeekDate());

                    scheduleListWithInsert.add(schedule);

                    hashMap.put(schedule.getWeekDate(), scheduleListWithInsert);
                }
            }

            for(WeekDate weekDate: WeekDate.getList()) {
                gravarArq.printf("+-- " + weekDate.getKey() + " --+%n");
                ArrayList<Schedule> schedulesInDay = hashMap.get(weekDate);

                for (int disciplineIndex = 0; disciplineIndex < GlobalVariables.DISCIPLINES_PER_DAY; disciplineIndex++) {
                    for(Schedule schedule: schedulesInDay) {
                        gravarArq.printf(" " + schedule.getDisciplines().get(disciplineIndex).getName() + " | ");
                    }
                    gravarArq.printf("%n");
                    for(Schedule schedule: schedulesInDay) {
                        gravarArq.printf(" " + schedule.getDisciplines().get(disciplineIndex).getTeacher().getName() + " | ");
                    }
                    gravarArq.printf("%n");
                }
            }
        }

        arq.close();
        //fitness(population);

    }

    public static HashMap<WeekDate, ArrayList<Schedule>> generateHashMap() {
        HashMap<WeekDate, ArrayList<Schedule>> hashMap = new HashMap<>(WeekDate.getList().size());

        for (WeekDate day : WeekDate.values()) {
            hashMap.put(day, new ArrayList<>());
        }

        return hashMap;
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