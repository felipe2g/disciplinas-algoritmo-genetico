package Entities;

import Common.Initializers.DisciplineInitializer;
import Common.Initializers.FullCourse;
import Common.Initializers.TeacherInitializer;

import java.util.ArrayList;

public class Individual {
    private ArrayList<Schedule> course = new ArrayList<Schedule>();

    private Double rate = 0.0;

    public Individual() {
        ArrayList<Teacher> teachers = TeacherInitializer.initializeTeachers();
        ArrayList<Discipline> disciplines = DisciplineInitializer.initializeDisciplines();

        this.course = FullCourse.generateRandomFullCourseLine(teachers, disciplines);
    }

    public Individual(ArrayList<Schedule> course, Double rate) {
        this.course = course;
        this.rate = rate;
    }

    public ArrayList<Schedule> getCourse() {
        return course;
    }

    public void setCourse(ArrayList<Schedule> course) {
        this.course = course;
    }

    public Double getRate() {
        return rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }

    @Override
    public String toString() {
        return "Individual{" +
                "course=" + course +
                ", rate=" + rate +
                '}';
    }
}
