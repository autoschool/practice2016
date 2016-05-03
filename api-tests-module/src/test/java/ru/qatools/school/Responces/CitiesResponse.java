package ru.qatools.school.Responces;

/**
 * @author ava1on
 */
public class CitiesResponse {
    private String country;
    private String name;
    private int uid;

    public CitiesResponse(String country, String name, int uid) {
        this.country = country;
        this.name = name;
        this.uid = uid;
    }

    public String getCountry() {
        return country;
    }

    public String getName() {
        return name;
    }

    public int getUid() {
        return uid;
    }
}
