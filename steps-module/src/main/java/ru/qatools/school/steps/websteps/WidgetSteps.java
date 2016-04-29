package ru.qatools.school.steps.websteps;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.qatools.school.pages.MainPage;
import ru.qatools.school.pages.blocks.WeatherWidget;
import ru.yandex.qatools.allure.annotations.Step;

import java.util.List;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

/**
 * Created by merkushevio on 22.04.2016.
 */
public class WidgetSteps {
    private WebDriver driver;

    public WidgetSteps(WebDriver driver) {
        this.driver = driver;
        mainPage = new MainPage(driver);
    }

    private MainPage mainPage;

    @Step("Должны увидеть изменение формата вывода градусов")
    public void shouldSeeChangeFormatDegreeCelciumToKelvin() {
        getAllWidgets().get(0).getWeatherBlock().click();
        assertThat("Не изменился формат вывода с градусов цельсия в градусы кельвина",
                getAllWidgets().get(0).getWeatherType().getText(),
                equalTo(EXPECT_STRING.KELVIN.toString()));
    }

    @Step("Должны увидеть изменение формата вывода градусов")
    public void shouldSeeChangeFormatDegreeKelvinToFarengeit() {
        getAllWidgets().get(0).getWeatherBlock().click();
        assertThat("Не изменился формат вывода с градусов в кельвинах в градусы фаренгейта",
                getAllWidgets().get(0).getWeatherType().getText(),
                equalTo(EXPECT_STRING.FARINGEIT.toString()));
    }

    @Step("Должны увидеть изменение формата вывода градусов")
    public void shouldSeeChangeFormatDegreeFarengeitToKaif() {
        getAllWidgets().get(0).getWeatherBlock().click();
        assertThat("Не изменился формат вывода с градусов фаренгейта в градусы °Kaif",
                getAllWidgets().get(0).getWeatherType().getText(),
                equalTo(EXPECT_STRING.KAIF.toString()));
    }

    @Step("Начальное открытие виджета, единица измерений температуры Цельсий")
    public void shouldSeeTemperatureCelcium() {
        assertThat("Начальное значение температуры не в Цельсиях",
                getAllWidgets().get(0).getWeatherType().getText(),
                equalTo(EXPECT_STRING.CELCIUM.toString()));
    }

    @Step("Формат времени для значения поля рассвет должен быть 'xx:xx'")
    public void shouldSeeFormatTimeSunrise() {
        assertThat("Формат времени для значения поля рассвет не 'xx:xx'",
                mainPage.getInfoValues().get(0).getText(),
                WebDriverMatchers.stringMatcher(EXPECT_STRING.SUNRISE_TIME.toString()));
    }

    @Step("Формат времени для значения поля закат должен быть 'xx:xx'")
    public void shouldSeeFormatTimeSunset() {
        assertThat("Формат времени для значения поля закат не 'xx:xx'",
                mainPage.getInfoValues().get(1).getText(),
                WebDriverMatchers.stringMatcher(EXPECT_STRING.SUNSET_TIME.toString()));
    }

    @Step("Формат времени для значения поля скорость ветра должен быть 'xx m/s'")
    public void shouldSeeFormatSpeedWind() {
        assertThat("Формат времени для значения поля скорость ветра не 'xx m/s'",
                mainPage.getInfoValues().get(2).getText(),
                WebDriverMatchers.stringMatcher(EXPECT_STRING.WIND_SPEED.toString()));
    }


    @Step("Формат времени для значения поля влажность должен быть 'xx %'")
    public void shouldSeeFormatHumidity() {
        assertThat("Формат времени для значения поля влажность не 'xx %'",
                mainPage.getInfoValues().get(3).getText(),
                WebDriverMatchers.stringMatcher(EXPECT_STRING.HUMIDITY_VALUE.toString()));
    }

    public void addWidget() {
        mainPage.getAddWidget().click();
    }

    public WebElement findElement(List<WebElement> list, String name) {
        for (WebElement element : list) {
            if (element.getText().equals(name)) {
                return element;
            }
        }
        return null;
    }

    public void renameWidget(String oldName, String newName) {
        findElement(getAllPlaces(), oldName).click();
        mainPage.getEditPlace().clear();
        mainPage.getEditPlace().sendKeys(newName);
        mainPage.getEditPlace().sendKeys(Keys.RETURN);
    }

    public int countWidgets() {
        return mainPage.getWeatherWidgets().size();
    }

    public List<WeatherWidget> getAllWidgets() {
        return mainPage.getWeatherWidgets();
    }

    public List<WebElement> getAllPlaces() {
        return mainPage.getPlaces();
    }

    public List<WebElement> getTitleValues() {
        return mainPage.getTitleValues();
    }

    public void autoComplete(String city) {
        mainPage.getPlaces().get(0).click();
        mainPage.getEditPlace().clear();
        mainPage.getEditPlace().sendKeys(city.substring(0, city.length() / 2));
        WebElement element = null;
        List<WebElement> widget = mainPage.getAutoCompleteValues();
        for (WebElement elem : widget) {
            if (elem.getText().equals(city)) {
                element = elem;
            }
        }
        element.click();
    }



}
