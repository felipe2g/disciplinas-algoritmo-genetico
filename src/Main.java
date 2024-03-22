import Common.GlobalVariables;
import Common.Initializers.DisciplineInitializer;
import Common.Initializers.FullCourse;
import Common.Initializers.TeacherInitializer;
import Entities.Discipline;
import Entities.Period;
import Entities.Schedule;
import Entities.Teacher;

import java.util.ArrayList;


public class Main {
    public static void main(String[] args) {
        ArrayList<Teacher> teachers = TeacherInitializer.initializeTeachers();
        ArrayList<Discipline> disciplines = DisciplineInitializer.initializeDisciplines();

        ArrayList<ArrayList<Period>> population = new ArrayList<>();

        for(int i = 1; i < GlobalVariables.POPULATION_SIZE; i++) {
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