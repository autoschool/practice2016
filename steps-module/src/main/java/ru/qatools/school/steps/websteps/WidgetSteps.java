package ru.qatools.school.steps.websteps;

import org.openqa.selenium.WebDriver;
import ru.qatools.school.pages.MainPage;
import ru.qatools.school.pages.MainPageMethods;
import ru.yandex.qatools.allure.annotations.Step;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

/**
 * Created by merkushevio on 22.04.2016.
 */
public class WidgetSteps {
    private WebDriver driver;

    private static final String CELCIUM = "°C";
    private static final String KELVIN = "°K";
    private static final String FARINGEIT = "°F";
    private static final String KAIF = "°Kaif";
    private static final String SUNRISE_TIME = "^[0-2][0-9]:[0-5][0-9]$";
    private static final String SUNSET_TIME = "^[0-2][0-9]:[0-5][0-9]$";
    private static final String WIND_SPEED = "^\\d m/s";
    private static final String HUMIDITY_VALUE = "^[0-9]+ %";

    public WidgetSteps(WebDriver driver) {
        this.driver = driver;
    }

    private MainPage onMainPage() {
        return new MainPage(driver);
    }

    private MainPageMethods mainPageMethods() {
        return new MainPageMethods(driver);
    }

    @Step("Должны увидеть изменение формата вывода градусов")
    public void shouldSeeChangeFormatDegreeCelciumToKelvin() {
        mainPageMethods().getAllWidgets().get(0).getWeatherBlock().click();
        assertThat("Не произошло преобразование градусов цельсия в градусы кельвина",
                mainPageMethods().getAllWidgets().get(0).getWeatherType().getText(), equalTo(KELVIN));
    }

    @Step("Должны увидеть изменение формата вывода градусов")
    public void shouldSeeChangeFormatDegreeKelvinToFarengeit() {
        mainPageMethods().getAllWidgets().get(0).getWeatherBlock().click();
        assertThat("Не произошло преобразование градусов в кельвинах в градусы фаренгейта",
                mainPageMethods().getAllWidgets().get(0).getWeatherType().getText(), equalTo(FARINGEIT));
    }

    @Step("Должны увидеть изменение формата вывода градусов")
    public void shouldSeeChangeFormatDegreeFarengeitToKaif() {
        mainPageMethods().getAllWidgets().get(0).getWeatherBlock().click();
        assertThat("Не произошло преобразование градусов фаренгейта в градусы °Kaif",
                mainPageMethods().getAllWidgets().get(0).getWeatherType().getText(), equalTo(KAIF));
    }

    @Step("Начальное открытие виджета, единица измерений температуры Цельсий")
    public void shouldSeeTemperatureCelcium() {
        assertThat("Начальное значение температуры не в Цельсиях",
                mainPageMethods().getAllWidgets().get(0).getWeatherType().getText(), equalTo(CELCIUM));
    }

    @Step("Формат времени для значения поля рассвет должен быть 'xx:xx'")
    public void shouldSeeFormatTimeSunrise() {
        assertThat("Формат времени для значения поля рассвет не 'xx:xx'",
                mainPageMethods().getMainPage().getInfoValues().get(0).getText(), mainPageMethods().stringMatcher(SUNRISE_TIME));
    }

    @Step("Формат времени для значения поля закат должен быть 'xx:xx'")
    public void shouldSeeFormatTimeSunset() {
        assertThat("Формат времени для значения поля закат не 'xx:xx'",
                onMainPage().getInfoValues().get(1).getText(), mainPageMethods().stringMatcher(SUNSET_TIME));
    }

    @Step("Формат времени для значения поля скорость ветра должен быть 'xx m/s'")
    public void shouldSeeFormatSpeedWind() {
        assertThat("Формат времени для значения поля скорость ветра не 'xx m/s'",
                onMainPage().getInfoValues().get(2).getText(), mainPageMethods().stringMatcher(WIND_SPEED));
    }


    @Step("Формат времени для значения поля влажность должен быть 'xx %'")
    public void shouldSeeFormatHumidity() {
        assertThat("Формат времени для значения поля влажность не 'xx %'",
                onMainPage().getInfoValues().get(3).getText(), mainPageMethods().stringMatcher(HUMIDITY_VALUE));
    }

}
