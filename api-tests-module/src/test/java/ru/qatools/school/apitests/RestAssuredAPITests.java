package ru.qatools.school.apitests;

import org.apache.http.HttpStatus;
import org.apache.http.protocol.HTTP;
import org.junit.Test;
import ru.qatools.school.Responses.CitiesResponse;
import ru.qatools.school.Responses.WeatherResponse;
import ru.yandex.qatools.allure.annotations.Title;

import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.closeTo;
import static org.hamcrest.Matchers.is;
import static ru.qatools.school.data.Constants.*;

/**
 * @author ava1on
 */
public class RestAssuredAPITests {

    @Test
    @Title("Должны получить заданное в параметре количество городов в ответе")
    public void shouldReturnRequestedNumberOfCities(){
        CitiesResponse[] citiesArray =
                given().
                        baseUri(BASE_URL).
                        param(LIMIT_PARAMETER, LIMIT_VALUE).
                when().
                        get(CITIES_REQUEST).
                then().statusCode(HttpStatus.SC_OK).
                        and().extract().
                        body().as(CitiesResponse[].class);
        assertThat("Неверное количество городов в ответе", citiesArray.length, is(LIMIT_VALUE));
    }

    @Test
    @Title("Значение температуры по шкале Фаренгейта должно соответствовать значению по шкале Цельсия")
    public void shouldReturnCompatibleTemperatureValuesForCelsiusAndFahrenheit(){
        WeatherResponse weatherResponse =
                given().
                        baseUri(BASE_URL).
                        param(CITY_PARAMETER, CITY_VALUE).
                when().
                        get(WEATHER_REQUEST).
                then().statusCode(HttpStatus.SC_OK).
                        and().extract().
                        body().as(WeatherResponse.class);
        assertThat("Значение температуры не правильно переведено из шкалы Цельсия в шкалу Фаренгейта",
                weatherResponse.getTemperatures()[2].getValue(),
                is(closeTo(weatherResponse.recalculateCelsiusToFahrenheit(), TEMPERATURE_ERROR)));
    }
}