package Entities;

import java.util.UUID;

public class Teacher {
    private UUID id = UUID.randomUUID();
    private String name;

    public Teacher() {
    }

    public Teacher(String name) {
        this.name = name;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Entities.Teacher{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
