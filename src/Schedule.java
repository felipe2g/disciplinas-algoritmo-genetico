import Common.WeekDate;

public class Schedule {
    private Teacher teacher;
    private Discipline discipline;
    private WeekDate weekDate;

    public Schedule() {
    }

    public Schedule(Teacher teacher, Discipline discipline, WeekDate weekDate) {
        this.teacher = teacher;
        this.discipline = discipline;
        this.weekDate = weekDate;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public Discipline getDiscipline() {
        return discipline;
    }

    public void setDiscipline(Discipline discipline) {
        this.discipline = discipline;
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
                "teacher=" + teacher +
                ", discipline=" + discipline +
                ", weekDate=" + weekDate +
                '}';
    }
}
