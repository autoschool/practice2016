package ru.qatools.school.apitests;

import org.apache.http.HttpStatus;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import com.jayway.restassured.builder.RequestSpecBuilder;
import com.jayway.restassured.specification.RequestSpecification;
import ru.qatools.school.apitests.data.City;
import ru.qatools.school.apitests.data.WeatherInfo;
import ru.yandex.qatools.allure.annotations.Title;

import java.io.IOException;
import java.util.List;

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
    @Title("Запрос на /cities с параметром limit=5 вернул 5 записей")
    public void shouldGetExpectedCountOfCities() throws IOException {
       List<City> list = given().spec(reqSpec)
                .param("limit", 5)
                .get("cities")
                .then().assertThat()
                .statusCode(HttpStatus.SC_OK)
                .extract().path("");
    }
}
