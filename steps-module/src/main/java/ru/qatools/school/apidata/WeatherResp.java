package ru.qatools.school.apidata;

import java.util.List;

/**
 * @author onegines (Eugene Kirienko)
 */
public class WeatherResp {

    private String city;

    private List<Temperature> temperatures;

    public String getCity() {
        return city;
    }

    public List<Temperature> getTemperatures() {
        return temperatures;
    }
}
