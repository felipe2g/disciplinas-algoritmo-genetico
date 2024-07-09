package Entities;

import java.util.UUID;

public class Teacher {
    private UUID id = UUID.randomUUID();
    private String name;
    private String color;

    public Teacher() {
    }

    public Teacher(String name) {
        this.name = name;
    }

    public Teacher(String name, String color) {
        this.name = name;
        this.color = color;
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

    public void setId(UUID id) {
        this.id = id;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return "Entities.Teacher{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
