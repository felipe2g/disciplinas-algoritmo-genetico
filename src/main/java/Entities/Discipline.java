package Entities;

public class Discipline {

    private Teacher teacher;
    private String name;
    private Integer semester;
    private Boolean scheduleConflict = false;

    public Discipline() {
    }

    public Discipline(String name, Integer semester) {
        this.name = name;
        this.semester = semester;
    }

    public Discipline(Teacher teacher, String name, Integer semester) {
        this.teacher = teacher;
        this.name = name;
        this.semester = semester;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSemester() {
        return semester;
    }

    public void setSemester(Integer semester) {
        this.semester = semester;
    }

    public Boolean getScheduleConflict() {
        return scheduleConflict;
    }

    public void setScheduleConflict(Boolean scheduleConflict) {
        this.scheduleConflict = scheduleConflict;
    }

    @Override
    public String toString() {
        return "Discipline{" +
                "teacher=" + teacher +
                ", name='" + name + '\'' +
                ", semester=" + semester +
                ", scheduleConflict=" + scheduleConflict +
                '}';
    }
}
