package Common;

import Entities.Schedule;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.HashSet;

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

    public static ArrayList<WeekDate> getList() {
        return new ArrayList<WeekDate>(EnumSet.allOf(WeekDate.class));
    }

    public static HashMap<WeekDate, HashSet<String>> getHashSet() {
        HashMap<WeekDate, HashSet<String>> map = new HashMap<>(getList().size());

        for (WeekDate weekDate : EnumSet.allOf(WeekDate.class)) {
            map.put(weekDate, new HashSet<>());
        }

        return map;
    }
}
