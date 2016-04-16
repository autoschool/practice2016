package ru.qatools.school.data;

/**
 * Created by onodee on 16.04.2016.
 */
public enum City {
    MOSCOW("Moscow"), PITER("Saint-Petersburg"), KALUGA("Калуга");

    private String city;

    City(String city){
        this.city = city;
    }

    public String getText(){
        return city;
    }
}
