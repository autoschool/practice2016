package ru.qatools.school.data;

/**
 * Created by onodee on 16.04.2016.
 */
public enum City {
    MOSCOW("Moscow"),
    PITER("Saint-Petersburg"),
    KALUGA("Калуга");

    private String name;

    City(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }
}
