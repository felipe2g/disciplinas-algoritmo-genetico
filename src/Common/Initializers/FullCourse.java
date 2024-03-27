package Common.Initializers;

import Common.GlobalVariables;
import Common.WeekDate;
import Entities.Discipline;
import Entities.Period;
import Entities.Schedule;
import Entities.Teacher;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;
import java.util.Random;
import java.util.stream.Collectors;

public class FullCourse {
    public static ArrayList<Period> generateRandomFullCourse(ArrayList<Teacher> teachers, ArrayList<Discipline> disciplines) {

        ArrayList<Period> fullCourse = new ArrayList<>();

        ArrayList<Discipline> disciplinesWithTeacher = generateDisciplinesWithTeacher(disciplines, teachers);

        for (int periodOrder = 1; periodOrder <= GlobalVariables.PERIOD_COUNT; periodOrder++) {
            Period period = new Period(periodOrder);

            ArrayList<Discipline> actualSemesterDisciplines = getDisciplinesOnSemester(disciplinesWithTeacher, periodOrder);
            HashMap<String, Integer> usageDisciplines = generateListWithUsageCount(actualSemesterDisciplines);

            for (int dayNumber = 0; dayNumber < WeekDate.getList().size(); dayNumber++) {
                WeekDate weekDate = WeekDate.getList().get(dayNumber);
                ArrayList<Discipline> dayDisciplines = new ArrayList<>();

                while (dayDisciplines.size() < GlobalVariables.DISCIPLINES_PER_DAY) {
                    Random random = new Random();
                    int rnd = random.nextInt(actualSemesterDisciplines.size());
                    Discipline randomDiscipline = actualSemesterDisciplines.get(rnd);

                    if (!GlobalVariables.HAS_SAME_DISCIPLINE_IN_DAY && !dayDisciplines.isEmpty()) {
                        String nameSelectedDiscipline = dayDisciplines.get(0).getName();
                        String nameRandomDiscipline = randomDiscipline.getName();

                        while (nameSelectedDiscipline.equals(nameRandomDiscipline) && usageDisciplines.size() > 1) {
                            rnd = random.nextInt(actualSemesterDisciplines.size());
                            randomDiscipline = actualSemesterDisciplines.get(rnd);
                            nameRandomDiscipline = randomDiscipline.getName();
                        }
                    }

                    usageDisciplines.put(randomDiscipline.getName(), usageDisciplines.getOrDefault(randomDiscipline.getName(), 0) + 1);
                    int disciplineUsageCount = usageDisciplines.get(randomDiscipline.getName());

                    if (Objects.equals(disciplineUsageCount, GlobalVariables.DISCIPLINES_PER_DAY)) {
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

    private static ArrayList<Discipline> generateDisciplinesWithTeacher(ArrayList<Discipline> disciplines,
                                                                        ArrayList<Teacher> teachers) {
        ArrayList<Discipline> disciplinesWithTeacher = new ArrayList<>();

        for (int i = 0; i < disciplines.size(); i++) {
            Discipline newDiscipline = disciplines.get(i);
            int relativePositionDisciplineForTeacher = i % teachers.size();
            newDiscipline.setTeacher(teachers.get(relativePositionDisciplineForTeacher));
            disciplinesWithTeacher.add(newDiscipline);
        }

        return disciplinesWithTeacher;
    }

    private static HashMap<String, Integer> generateListWithUsageCount(ArrayList<Discipline> disciplines) {
        return disciplines.stream()
                .collect(Collectors.toMap(Discipline::getName, d -> 0, (a, b) -> b, HashMap::new));
    }

    private static ArrayList<Discipline> getDisciplinesOnSemester(ArrayList<Discipline> disciplines, Integer semester) {
        return disciplines.stream()
                .filter(discipline -> discipline.getSemester()
                        .equals(semester))
                .collect(Collectors.toCollection(ArrayList::new));
    }
}
