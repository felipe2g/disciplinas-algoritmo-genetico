package Common.Initializers;

import Entities.Teacher;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

public class TeacherInitializer {
    public static ArrayList<Teacher> initializeTeachers() {
        Teacher teacher1 = new Teacher("Professor 1");
        Teacher teacher2 = new Teacher("Professor 2");
        Teacher teacher3 = new Teacher("Professor 3");
        Teacher teacher4 = new Teacher("Professor 4");
        Teacher teacher5 = new Teacher("Professor 5");
        Teacher teacher6 = new Teacher("Professor 6");
        Teacher teacher7 = new Teacher("Professor 7");
        Teacher teacher8 = new Teacher("Professor 8");
        Teacher teacher9 = new Teacher("Professor 9");
        Teacher teacher10 = new Teacher("Professor 10");
        Teacher teacher11 = new Teacher("Professor 11");
        Teacher teacher12 = new Teacher("Professor 12");

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
