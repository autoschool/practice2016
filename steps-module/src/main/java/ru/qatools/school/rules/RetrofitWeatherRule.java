package ru.qatools.school.rules;

import org.junit.rules.ExternalResource;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static ru.qatools.school.WebsiteData.MAIN_PAGE;

/**
 * @author gladnik (Nikolai Gladkov)
 */
public class RetrofitWeatherRule extends ExternalResource {

    private Retrofit retrofit;

    protected void before() {
        this.retrofit = new Retrofit.Builder()
                .baseUrl(MAIN_PAGE)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public Retrofit getRetrofit() {
        return retrofit;
    }

}
