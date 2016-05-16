package ru.qatools.school.retrofit;


import com.tngtech.java.junit.dataprovider.DataProvider;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;
import org.apache.http.HttpStatus;
import org.hamcrest.Matchers;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import ru.qatools.school.ApiMatchers;
import ru.qatools.school.WebApiInterface;
import ru.qatools.school.entity.City;
import ru.qatools.school.entity.Widget;
import ru.qatools.school.tp.TPInformerRule;
import ru.yandex.qatools.allure.annotations.Title;
import java.io.IOException;
import java.util.List;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.core.Is.is;


@RunWith(DataProviderRunner.class)
public class WeatherWebApiRetrofit {
    private static final String MAIN_PAGE = "http://weather.lanwen.ru/api/";
    private static WebApiInterface webApiInterface;

    //@Rule
    //public TPInformerRule tms = new TPInformerRule("merkushevio");

    @DataProvider
    public static Object[][] limitPositive() {
        return Parameters.limitsPositive();
    }

    @DataProvider
    public static Object[][] limitNegative() {
        return Parameters.limitsNegative();
    }

    @DataProvider
    public static Object[][] suggest() {
        return Parameters.queryString();
    }

    @DataProvider
    public static List<List<Object>> weather() {
        return Parameters.cityRegionPositive();
    }

    @BeforeClass
    public static void getWebApiInterface() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(MAIN_PAGE)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        webApiInterface = retrofit.create(WebApiInterface.class);
    }


    @Test
    @Title("Получаем список городов используя метод /cities без параметров")
    public void shouldSeeListCities() throws IOException {
        Call<List<City>> listCities = webApiInterface.getCities();
        Response<List<City>> response = listCities.execute();
        assertThat("Не получили список городов", response.body().size(), greaterThan(0));
    }

    @Test
    @UseDataProvider("limitPositive")
    @Title("Получаем список городов используя метод /cities с параметром limit корректный ввод")
    public void shouldSeeLimitCity(String limit, int result, int pageCode) throws IOException {
        Call<List<City>> city = webApiInterface.getCitiesLimit(limit);
        Response<List<City>> response = city.execute();
        assertThat("Статус страницы не соответствует ожидаемому " + pageCode +
                ". \n Запрашиваемое количество городов равно " + limit, response.code(), is(pageCode));
        assertThat("Не получили ожидаемый список, запрашивали " + limit + " количество городов",
                response.body().size(), is(result));
    }
    @Test
    @UseDataProvider("limitNegative")
    @Title("Получаем список городов используя метод /cities с параметром limit не корректный ввод")
    public void shouldSeePageNotFound(String limit, int pageCode) throws IOException {
        Call<List<City>> city = webApiInterface.getCitiesLimit(limit);
        Response<List<City>> response = city.execute();
        assertThat("Статус страницы не соответствует ожидаемому " + pageCode +
                ". \n Запрашиваемое количество городов равно " + limit, response.code(), is(pageCode));
    }

    @Test
    @Title("Получаем список городов используя метод /init без параметров")
    public void shouldSeeListInit() throws IOException {
        Call<List<City>> init = webApiInterface.getInit();
        Response<List<City>> response = init.execute();
        assertThat("Полученный список городов пуст", response.body().size(), greaterThan(0));
    }


    @Test
    @UseDataProvider("suggest")
    @Title("Получаем список городов используя метод /suggest с параметром query")
    public void shouldSeeSuggestCity(String query, String result, int pageCode) throws IOException{
        Call<List<City>> listCall = webApiInterface.getListSuggest(query);
        Response<List<City>> response = listCall.execute();
        assertThat("Статус страницы не соответствует ожидаемому " + pageCode +
                ". \n Параметр запроса: " + query, response.code(), is(pageCode));
        assertThat("Вернулось не верное значение города", result,
                anyOf(is(ApiMatchers.findInList(response.body())), is(nullValue())));
    }

    @Test
    @UseDataProvider("weather")
    @Title("Получаем данные по городу используя метод /weather с параметрами city и region")
    public void shouldSeeWeatherCity(String city, String region, int pageCode) throws IOException{
        Call<Widget> call = webApiInterface.getWeatherWidget(city, region);
        Response<Widget> response = call.execute();
        assertThat("Статус страницы не соответствует ожидаемому " + pageCode +
                ". \n Параметр запроса: " + city, response.code(), is(pageCode));
    }

}
