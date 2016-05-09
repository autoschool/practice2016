package ru.qatools.school.rules;

import org.junit.rules.ExternalResource;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import ru.qatools.school.api.weather.WeatherInterface;

import static ru.qatools.school.WebsiteData.MAIN_PAGE;

/**
 * @author gladnik (Nikolai Gladkov)
 */
public class RetrofitWeatherRule extends ExternalResource {

    private WeatherInterface weatherInterface;

    protected void before() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(MAIN_PAGE)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        weatherInterface = retrofit.create(WeatherInterface.class);
    }

    public WeatherInterface getWeatherInterface() {
        return weatherInterface;
    }

}
