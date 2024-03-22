package Entities;

import Common.WeekDate;

import java.util.ArrayList;

public class Schedule {
    private ArrayList<Discipline> disciplines;
    private WeekDate weekDate;

    public Schedule() {
    }

    public Schedule(ArrayList<Discipline> disciplines, WeekDate weekDate) {
        this.disciplines = disciplines;
        this.weekDate = weekDate;
    }

    public ArrayList<Discipline> getDisciplines() {
        return disciplines;
    }

    public void setDisciplines(ArrayList<Discipline> disciplines) {
        if (disciplines.size() > 2) {
            throw new RuntimeException("É permitido somente duas disciplinas por dia letivo.");
        }

        this.disciplines = disciplines;
    }

    public void addDiscipline(Discipline discipline) {
        if (this.disciplines.size() > 2) {
            throw new RuntimeException("É permitido somente duas disciplinas por dia letivo.");
        }
        this.disciplines.add(discipline);
    }

    public WeekDate getWeekDate() {
        return weekDate;
    }

    public void setWeekDate(WeekDate weekDate) {
        this.weekDate = weekDate;
    }

    @Override
    public String toString() {
        return "Schedule{" +
                ", discipline=" + disciplines.toString() +
                ", weekDate=" + weekDate +
                '}';
    }
}
