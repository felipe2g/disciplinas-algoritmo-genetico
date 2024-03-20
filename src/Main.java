import Common.WeekDate;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Integer SEMESTER_COUNT = 5;

        // 12 professores
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

        HashMap<UUID, Teacher> teachers = new HashMap<>();
        teachers.put(teacher1.getId(), teacher1);
        teachers.put(teacher2.getId(), teacher2);
        teachers.put(teacher3.getId(), teacher3);
        teachers.put(teacher4.getId(), teacher4);
        teachers.put(teacher5.getId(), teacher5);
        teachers.put(teacher6.getId(), teacher6);
        teachers.put(teacher7.getId(), teacher7);
        teachers.put(teacher8.getId(), teacher8);
        teachers.put(teacher9.getId(), teacher9);
        teachers.put(teacher10.getId(), teacher10);
        teachers.put(teacher11.getId(), teacher11);
        teachers.put(teacher12.getId(), teacher12);

        // 25 disciplinas
        Discipline discipline1 = new Discipline("Disciplina 1", 1);
        Discipline discipline2 = new Discipline("Disciplina 2", 1);
        Discipline discipline3 = new Discipline("Disciplina 3", 1);
        Discipline discipline4 = new Discipline("Disciplina 4", 1);
        Discipline discipline5 = new Discipline("Disciplina 5", 1);
        Discipline discipline6 = new Discipline("Disciplina 6", 2);
        Discipline discipline7 = new Discipline("Disciplina 7", 2);
        Discipline discipline8 = new Discipline("Disciplina 8", 2);
        Discipline discipline9 = new Discipline("Disciplina 9", 2);
        Discipline discipline10 = new Discipline("Disciplina 10", 2);
        Discipline discipline11 = new Discipline("Disciplina 11", 3);
        Discipline discipline12 = new Discipline("Disciplina 12", 3);
        Discipline discipline13 = new Discipline("Disciplina 13", 3);
        Discipline discipline14 = new Discipline("Disciplina 14", 3);
        Discipline discipline15 = new Discipline("Disciplina 15", 3);
        Discipline discipline16 = new Discipline("Disciplina 16", 4);
        Discipline discipline17 = new Discipline("Disciplina 17", 4);
        Discipline discipline18 = new Discipline("Disciplina 18", 4);
        Discipline discipline19 = new Discipline("Disciplina 19", 4);
        Discipline discipline20 = new Discipline("Disciplina 20", 4);
        Discipline discipline21 = new Discipline("Disciplina 21", 5);
        Discipline discipline22 = new Discipline("Disciplina 22", 5);
        Discipline discipline23 = new Discipline("Disciplina 23", 5);
        Discipline discipline24 = new Discipline("Disciplina 24", 5);
        Discipline discipline25 = new Discipline("Disciplina 25", 5);

        HashMap<String, Discipline> disciplines = new HashMap<>();
        disciplines.put(discipline1.getName(), discipline1);
        disciplines.put(discipline2.getName(), discipline2);
        disciplines.put(discipline3.getName(), discipline3);
        disciplines.put(discipline4.getName(), discipline4);
        disciplines.put(discipline5.getName(), discipline5);
        disciplines.put(discipline6.getName(), discipline6);
        disciplines.put(discipline7.getName(), discipline7);
        disciplines.put(discipline8.getName(), discipline8);
        disciplines.put(discipline9.getName(), discipline9);
        disciplines.put(discipline10.getName(), discipline10);
        disciplines.put(discipline11.getName(), discipline11);
        disciplines.put(discipline12.getName(), discipline12);
        disciplines.put(discipline13.getName(), discipline13);
        disciplines.put(discipline14.getName(), discipline14);
        disciplines.put(discipline15.getName(), discipline15);
        disciplines.put(discipline16.getName(), discipline16);
        disciplines.put(discipline17.getName(), discipline17);
        disciplines.put(discipline18.getName(), discipline18);
        disciplines.put(discipline19.getName(), discipline19);
        disciplines.put(discipline20.getName(), discipline20);
        disciplines.put(discipline21.getName(), discipline21);
        disciplines.put(discipline22.getName(), discipline22);
        disciplines.put(discipline23.getName(), discipline23);
        disciplines.put(discipline24.getName(), discipline24);
        disciplines.put(discipline25.getName(), discipline25);

        ArrayList<Semester> semesterArrayList = new ArrayList<>();

        for(int i = 0; i < 99; i++) {
            semesterArrayList.add(generateRandomSemester(SEMESTER_COUNT, teachers, disciplines));
        }

        for (Semester semester: semesterArrayList) {
            System.out.println("Semestre");
            System.out.println(semester);
        }
    }

    public static Semester generateRandomSemester(Integer SEMESTER_COUNT,
                                                  HashMap<UUID, Teacher> teachers,
                                                  HashMap<String, Discipline> disciplines) {
        //Gera as disciplinas do dia
        List<WeekDate> weekDateList = new ArrayList<WeekDate>(EnumSet.allOf(WeekDate.class));

        Semester semester = new Semester();

        for(int i = 1; i <= SEMESTER_COUNT; i++) {
            HashMap<String, Discipline> actualSemesterDisciplines = getDisciplinesOnSemester(disciplines, i);
            ArrayList<UUID> selectedTeachers = new ArrayList<>();

            semester.setOrder(i);

            for (WeekDate weekDate : weekDateList) {
                String disciplineKey = getRandomItemFromHashMap(actualSemesterDisciplines);

                //TODO: lembrar de buscar aleatorio entre os professores que nao foram escolhidos
                // Utilizar teachers exceto selectedTeachers
                // formula de acordo com o indice do for escolhe o professor
                UUID teacherKey = getRandomItemFromHashMap(teachers);

                Discipline discipline = actualSemesterDisciplines.get(disciplineKey);
                Teacher teacher = teachers.get(teacherKey);

                selectedTeachers.add(teacherKey);
                actualSemesterDisciplines.remove(disciplineKey);

                Schedule schedule = new Schedule(teacher, discipline, weekDate);

                semester.addSchedule(schedule);
            }
        }

        return semester;
    }

    public static HashMap<String, Discipline> getDisciplinesOnSemester(HashMap<String, Discipline> disciplines, Integer semester) {
        HashMap<String, Discipline> disciplinesOnSemester = new HashMap<>();

        Iterator<Map.Entry<String, Discipline>> iterator = disciplines.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, Discipline> entry = iterator.next();
            if (entry.getValue().getSemester().equals(semester)) {
                disciplinesOnSemester.put(entry.getKey(), entry.getValue());
            }
        }

        return disciplinesOnSemester;
    }

    public static <K, V> K getRandomItemFromHashMap(HashMap<K, V> list) {
        if (list.isEmpty()) {
            return null;
        }

        Random random = new Random();
        int randomIndex = random.nextInt(list.size());
        int currentIndex = 0;

        for (Map.Entry<K, V> entry : list.entrySet()) {
            if (currentIndex == randomIndex) {
                return entry.getKey();
            }
            currentIndex++;
        }

        return null;
    }

}