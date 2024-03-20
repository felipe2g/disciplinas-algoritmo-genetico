public class Discipline {
    private String name;

    private Integer semester;

    public Discipline() {
    }

    public Discipline(String name, Integer semester) {
        this.name = name;
        this.semester = semester;
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

    @Override
    public String toString() {
        return "Discipline{" +
                "name='" + name + '\'' +
                ", semester=" + semester +
                '}';
    }
}
