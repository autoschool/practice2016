package ru.qatools.school.apitests;

import com.jayway.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.Before;
import org.junit.Test;
import com.jayway.restassured.builder.RequestSpecBuilder;
import com.jayway.restassured.specification.RequestSpecification;
import ru.qatools.school.apitests.data.City;
import ru.qatools.school.apitests.data.WeatherInfo;
import ru.yandex.qatools.allure.annotations.Title;

import java.io.IOException;

import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * @author Gavrilov_IS
 */
public class RestAssureAPITest {
    private static final String MAIN_PAGE = "http://weather.lanwen.ru/";
    private static final String BASE_PATH = "api";
    private final String CITY = "Moscow";
    private final int CITY_LIMIT = 5;

    private RequestSpecification reqSpec;

    @Before
    public void init() {
        reqSpec = new RequestSpecBuilder().build()
                .baseUri(MAIN_PAGE)
                .basePath(BASE_PATH);
    }

    @Test
    @Title("Проверяем, что запрос обработан и город в ответе соответствует запрошенному")
    public void shouldGetInfoForCityInRequest() throws IOException {
        WeatherInfo response = given().spec(reqSpec)
                .param("city", CITY)
                .expect().statusCode(HttpStatus.SC_OK)
                .get("weather")
                .as(WeatherInfo.class);
        assertThat("Город в ответе должен соответствовать запрошенному", response.getCity(), is(CITY));

    }

    @Test
    @Title("Запрос на /cities с параметром limit=CITY_LIMIT вернул указанное количество записей")
    public void shouldGetExpectedCountOfCities() throws IOException {
        Response response = given().spec(reqSpec)
                .param("limit", 5)
                .get("cities");
        response.then().assertThat()
                .statusCode(HttpStatus.SC_OK);
        City[] cities = response.as(City[].class);
        assertThat("Количество городов должно соответствовать указанному лимиту", cities.length, is(CITY_LIMIT));
    }
}
