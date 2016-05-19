package ru.qatools.school.entity;


import lombok.Getter;

@Getter
public class City {

    public City(int id, long uid, String name, String country) {
        this.id = id;
        this.uid = uid;
        this.name = name;
        this.country = country;
    }

    private int id;

    private long uid;

    private String name;

    private String country;

}
