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

        System.out.println(population);

        //exportToHTMLFile(fitnessPopulation);
    }

    public static ArrayList<Individual> initialization() {
        ArrayList<Individual> population = new ArrayList<Individual>();

        for (int i = 1; i < GlobalVariables.POPULATION_SIZE; i++) {
            population.add(new Individual());
        }

        return population;
    }

    public static ArrayList<Individual> fitness(ArrayList<Individual> population) throws JsonProcessingException {
        for (Individual individual : population) {
            Double rate = 0.0;

            ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
            String json = ow.writeValueAsString(individual);

            System.out.println(json);

            HashMap<WeekDate, HashSet<String>> dayWeekHashMap = WeekDate.getHashSet();

            for (int i = 0; i < individual.getCourse().size(); i++) {
                Integer weekdateSize = WeekDate.getList().size();
                for(int j = 0; j < weekdateSize; j++) {
                    for(int k = 0; k < GlobalVariables.DISCIPLINES_PER_DAY; k++) {
                        String firstScheduleDisciplineName = individual.getCourse().get(j).getDisciplines().get(k).getName();

                        WeekDate firstDayWeek = individual.getCourse().get(j).getWeekDate();

                        int dayToCompare = j < 4 ? j + 5 : (j*5) + 5;
                        String secondScheduleDisciplineName = individual.getCourse().get(dayToCompare).getDisciplines().get(k).getName();

                        WeekDate secondDayWeek = individual.getCourse().get(dayToCompare).getWeekDate();
                        if(firstScheduleDisciplineName.equals(secondScheduleDisciplineName)) {
                            rate++;
                        }
                    }
                }
            }

//            for(Period period: individual.getCourse()) {
//                for(Schedule schedule: period.getSchedules()) {
//                    for(Discipline discipline: schedule.getDisciplines()) {
//                        String teacherName = discipline.getTeacher().getName();
//                        WeekDate weekDate = schedule.getWeekDate();
//
//                        HashSet<String> teachersInDay = dayWeekHashMap.get(schedule.getWeekDate());
//
//                        if (!teachersInDay.contains(teacherName)) {
//                            teachersInDay.add(teacherName);
//                            dayWeekHashMap.put(weekDate, teachersInDay);
//                        } else {
//                            discipline.setScheduleConflict(true);
//                        }
//                    }
//                }
//            }

//            for (int disciplineSequence = 0; disciplineSequence < GlobalVariables.DISCIPLINES_PER_DAY; disciplineSequence++) {
//                ArrayList<UUID> teachersInSameWeekDate = new ArrayList<>(TeacherInitializer.initializeTeachers().size());
//                for (int dayNumber = 0; dayNumber < WeekDate.getList().size(); dayNumber++) {
//                    for (int periodNumber = 0; periodNumber < GlobalVariables.PERIOD_COUNT; periodNumber++) {
//                        Period period = individual.getCourse().get(periodNumber);
//                        Schedule schedule = period.getSchedules().get(dayNumber);
//                        Discipline discipline = schedule.getDisciplines().get(disciplineSequence);
//                        UUID teacherUUID = discipline.getTeacher().getId();
//
//                        System.out.print(discipline.getTeacher().getName() + " | ");
//
//
//                        Boolean teacherIdHasInArray = teachersInSameWeekDate.contains(teacherUUID);
//                        if (!teacherIdHasInArray) {
//                            teachersInSameWeekDate.add(teacherUUID);
//                            break;
//                        }
//                        rate++;
//                    }
//                }
//            }

            individual.setRate(rate);
        }

        return population;
    }
}