package Common.Initializers;

import Common.GlobalVariables;
import Common.WeekDate;
import Entities.Discipline;
import Entities.Schedule;
import Entities.Teacher;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;
import java.util.Random;
import java.util.stream.Collectors;

public class FullCourse {

    public static ArrayList<Schedule> generateRandomFullCourseLine() {
        ArrayList<Teacher> teachers = TeacherInitializer.initializeTeachers();
        ArrayList<Discipline> disciplines = DisciplineInitializer.initializeDisciplines();

        ArrayList<Schedule> schedules = new ArrayList<>(disciplines.size());

        ArrayList<Discipline> disciplinesWithTeacher = generateDisciplinesWithTeacher(disciplines, teachers);

        for (int periodOrder = 1; periodOrder <= GlobalVariables.PERIOD_COUNT; periodOrder++) {
            ArrayList<Discipline> actualSemesterDisciplines = getDisciplinesOnSemester(disciplinesWithTeacher, periodOrder);
            HashMap<String, Integer> usageDisciplines = generateListWithUsageCount(actualSemesterDisciplines);

            for (int dayNumber = 0; dayNumber < WeekDate.getList().size(); dayNumber++) {
                WeekDate weekDate = WeekDate.getList().get(dayNumber);
                ArrayList<Discipline> dayDisciplines = new ArrayList<>();

                while (dayDisciplines.size() < GlobalVariables.DISCIPLINES_PER_DAY) {
                    Random random = new Random();
                    int rnd = random.nextInt(actualSemesterDisciplines.size());
                    Discipline discipline = actualSemesterDisciplines.get(rnd);
                    Discipline uniqueDiscipline = new Discipline(discipline.getTeacher(), discipline.getName(), discipline.getSemester());

                    usageDisciplines.put(uniqueDiscipline.getName(), usageDisciplines.getOrDefault(uniqueDiscipline.getName(), 0) + 1);
                    int disciplineUsageCount = usageDisciplines.get(uniqueDiscipline.getName());

                    if (Objects.equals(disciplineUsageCount, GlobalVariables.DISCIPLINES_PER_DAY)) {
                        actualSemesterDisciplines.remove(rnd);
                        usageDisciplines.remove(uniqueDiscipline.getName());
                    }

                    dayDisciplines.add(uniqueDiscipline);
                }

                Schedule schedule = new Schedule(dayDisciplines, weekDate, periodOrder);
                schedules.add(schedule);
            }
        }

        return schedules;
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
