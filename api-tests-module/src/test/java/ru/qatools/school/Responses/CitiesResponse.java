package ru.qatools.school.Responses;

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
}
