package Common.Initializers;

import Entities.Teacher;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

public class TeacherInitializer {
    public static ArrayList<Teacher> initializeTeachers() {
        Teacher teacher1 = new Teacher("Clarimundo Machado");
        Teacher teacher2 = new Teacher("Edson Angoti");
        Teacher teacher3 = new Teacher("Carlos Magno");
        Teacher teacher4 = new Teacher("Crícia Zilda");
        Teacher teacher5 = new Teacher("Wilton Filho");
        Teacher teacher6 = new Teacher("Camilo Barreto");
        Teacher teacher7 = new Teacher("Ricardo Boaventura");
        Teacher teacher8 = new Teacher("Rodrigo Cavanha");
        Teacher teacher9 = new Teacher("Bruno Queiroz");
        Teacher teacher10 = new Teacher("Samira");
        Teacher teacher11 = new Teacher("Juliana Silvestre");
        Teacher teacher12 = new Teacher("Cibele");

        ArrayList<Teacher> teachers = new ArrayList<>();
        teachers.add(teacher1);
        teachers.add(teacher2);
        teachers.add(teacher3);
        teachers.add(teacher4);
        teachers.add(teacher5);
        teachers.add(teacher6);
        teachers.add(teacher7);
        teachers.add(teacher8);
        teachers.add(teacher9);
        teachers.add(teacher10);
        teachers.add(teacher11);
        teachers.add(teacher12);

        return teachers;
    }
}
