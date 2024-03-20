package Common;

public enum WeekDate {
    MONDAY("Segunda-feira", 1),
    TUESDAY("Terça-feira", 2),
    WEDNESDAY("Quarta-feira", 3),
    THURSDAY("Quinta-feira", 4),
    FRIDAY("Sexta-feira", 5);

    private final String key;
    private final Integer value;

    WeekDate(String key, Integer value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }
    public Integer getValue() {
        return value;
    }
}
