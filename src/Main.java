import Common.Initializers.DisciplineInitializer;
import Common.Initializers.TeacherInitializer;
import Common.WeekDate;
import Entities.Discipline;
import Entities.Period;
import Entities.Schedule;
import Entities.Teacher;

import java.util.*;
import java.util.stream.Collectors;

public class Main {
    private static final Integer PERIOD_COUNT = 5;
    private static final Integer DISCIPLINES_PER_DAY = 2;

    private static final Boolean DONT_REPEAT_DISCIPLINE_IN_DAY = true;

    public static void main(String[] args) {
        ArrayList<Teacher> teachers = TeacherInitializer.initializeTeachers();
        ArrayList<Discipline> disciplines = DisciplineInitializer.initializeDisciplines();

        ArrayList<ArrayList<Period>> population = new ArrayList<>();

        for(int i = 0; i < 99; i++) {
            population.add(generateRandomFullCourse(PERIOD_COUNT, teachers, disciplines));
        }

        for (int j=0; j<population.size(); j++) {
            ArrayList<Period> individual = population.get(j);
            for(Period period: individual) {
                for(Schedule schedule: period.getSchedules()) {
                    Discipline firstDiscipline = schedule.getDisciplines().get(0);
                    Discipline secondDiscipline = schedule.getDisciplines().get(1);

                    if(firstDiscipline.getName().equals(secondDiscipline.getName())) {
                        System.out.println("População: " + j +
                                " No período: " + period.getOrder() +
                                " Repetiu a disciplina: " + firstDiscipline.getName() +
                                " [" + firstDiscipline.getName() + ", " + secondDiscipline.getName() + "]"
                        );
                    }
                }
            }
        }
    }

    public static ArrayList<Period> generateRandomFullCourse(int PERIOD_COUNT,
                                                        ArrayList<Teacher> teachers,
                                                        ArrayList<Discipline> disciplines) {

        ArrayList<Period> fullCourse = new ArrayList<>();

        ArrayList<Discipline> disciplinesWithTeacher = generateDisciplinesWithTeacher(disciplines, teachers);

        for(int periodOrder = 1; periodOrder <= PERIOD_COUNT; periodOrder++) {
            Period period = new Period(periodOrder);

            ArrayList<Discipline> actualSemesterDisciplines = getDisciplinesOnSemester(disciplinesWithTeacher, periodOrder);
            HashMap<String, Integer> usageDisciplines = generateListWithUsageCount(actualSemesterDisciplines);

            for(int dayNumber = 0; dayNumber < WeekDate.getList().size(); dayNumber++) {
                WeekDate weekDate = WeekDate.getList().get(dayNumber);
                ArrayList<Discipline> dayDisciplines = new ArrayList<>();


                while(dayDisciplines.size() < DISCIPLINES_PER_DAY) {
                    int rnd = new Random().nextInt(actualSemesterDisciplines.size());

                    if (DONT_REPEAT_DISCIPLINE_IN_DAY && !dayDisciplines.isEmpty()) {
                        String nameSelectedDiscipline = dayDisciplines.get(0).getName();
                        String nameRandomDiscipline = actualSemesterDisciplines.get(rnd).getName();
                        boolean selectedDisciplineHasSameNameOfRandomDiscipline = nameSelectedDiscipline.equals(nameRandomDiscipline);

                        while(selectedDisciplineHasSameNameOfRandomDiscipline) {
                            rnd = new Random().nextInt(actualSemesterDisciplines.size());

                            selectedDisciplineHasSameNameOfRandomDiscipline = nameSelectedDiscipline.equals(actualSemesterDisciplines.get(rnd).getName());
                        }
                    }

                    Discipline randomDiscipline = actualSemesterDisciplines.get(rnd);

                    usageDisciplines.put(randomDiscipline.getName(), usageDisciplines.get(randomDiscipline.getName()) + 1);
                    int disciplineUsageCount = usageDisciplines.get(randomDiscipline.getName());

                    if(Objects.equals(disciplineUsageCount, DISCIPLINES_PER_DAY)) {
                        actualSemesterDisciplines.remove(rnd);
                        usageDisciplines.remove(randomDiscipline.getName());
                    }
                    dayDisciplines.add(randomDiscipline);
                }

                Schedule schedule = new Schedule(dayDisciplines, weekDate);
                period.addSchedule(schedule);
            }

            fullCourse.add(period);
        }

        return fullCourse;
    }

    public static ArrayList<Discipline> generateDisciplinesWithTeacher(ArrayList<Discipline> disciplines,
                                                                       ArrayList<Teacher> teachers) {
        ArrayList<Discipline> disciplinesWithTeacher = new ArrayList<>();

        for(int i = 0; i < disciplines.size(); i++) {
            Discipline newDiscipline = disciplines.get(i);
            int relativePositionDisciplineForTeacher = i % teachers.size();
            newDiscipline.setTeacher(teachers.get(relativePositionDisciplineForTeacher));
            disciplinesWithTeacher.add(newDiscipline);
        }

        return disciplinesWithTeacher;
    }

    public static HashMap<String, Integer> generateListWithUsageCount(ArrayList<Discipline> disciplines) {
        return disciplines.stream()
                .collect(Collectors.toMap(Discipline::getName, d -> 0, (a, b) -> b, HashMap::new));
    }

    public static ArrayList<Discipline> getDisciplinesOnSemester(ArrayList<Discipline> disciplines, Integer semester) {

        return disciplines.stream()
                .filter(discipline -> discipline.getSemester()
                .equals(semester))
                .collect(Collectors.toCollection(ArrayList::new));
    }

}