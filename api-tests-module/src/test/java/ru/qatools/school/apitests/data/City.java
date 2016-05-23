package ru.qatools.school.apitests.data;

/**
 * Created by Gavrilov_IS on 11.05.2016.
 */
public class City {
    private int UID;
    private String name;
    private String country;

    public String getName(){
        return name;
    }

    public int getUID(){
        return UID;
    }

    public String getCountry(){
        return country;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setUID(int UID){
        this.UID = UID;
    }
}
