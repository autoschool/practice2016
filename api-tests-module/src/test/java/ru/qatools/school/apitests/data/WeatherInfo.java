package ru.qatools.school.apitests.data;

import java.util.List;

/**
 * @author Gavrilov_IS
 */
public class WeatherInfo {
    private String city;
    private List<Temperature> temperatures;

    public String getCity(){
        return city;
    }

    public List<Temperature> getListOfTemperatures(){
        return temperatures;
    }
}
