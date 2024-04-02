package Entities;

import java.util.ArrayList;

public class Period {
    private Integer order;
    private ArrayList<Schedule> schedules = new ArrayList<>();

    // Colocar as disciplinas do semestre aqui dentro

    public Period() {
    }

    public Period(Integer order) {
        this.order = order;
    }

    public Period(Integer order, ArrayList<Schedule> schedules) {
        this.order = order;
        this.schedules = schedules;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    public ArrayList<Schedule> getSchedules() {
        return schedules;
    }

    public void setSchedules(ArrayList<Schedule> schedules) {
        this.schedules = schedules;
    }

    public void addSchedule(Schedule schedule) {
        this.schedules.add(schedule);
    }

    @Override
    public String toString() {
        return "Entities.Semester{" +
                "order=" + order +
                ", schedules=" + schedules +
                '}';
    }
}
