import Common.Initializers.DisciplineInitializer;
import Common.Initializers.FullCourse;
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
    private static final Boolean SAME_DISCIPLINE_IN_DAY = false;

    public static void main(String[] args) {
        ArrayList<Teacher> teachers = TeacherInitializer.initializeTeachers();
        ArrayList<Discipline> disciplines = DisciplineInitializer.initializeDisciplines();

        ArrayList<ArrayList<Period>> population = new ArrayList<>();

        for(int i = 0; i < 99; i++) {
            population.add(FullCourse.generateRandomFullCourse(teachers, disciplines));
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
}