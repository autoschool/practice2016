package ru.qatools.school.webapi.restassured;

import com.jayway.restassured.RestAssured.*;
import com.jayway.restassured.matcher.RestAssuredMatchers.*;
import org.hamcrest.Matchers.*;
import org.junit.Test;
import ru.yandex.qatools.allure.annotations.Title;


public class WeatherWebApiRest {

    @Test
    @Title("Проверяем GET /cities")
    public void shouldSeeAllCitiesRecords() {

    }

}


