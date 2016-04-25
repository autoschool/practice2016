package ru.qatools.school.pages.blocks.widgetblocks;

import org.openqa.selenium.Rectangle;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.annotations.Name;
import ru.yandex.qatools.htmlelements.element.HtmlElement;

/**
 * Created by kurau.
 */
public class WidgetText extends HtmlElement {

    @Name("Картинка текущей погоды")
    @FindBy(css = ".weather-image")
    private WebElement weatherImage;

    @Name("Блок температуры")
    @FindBy(css = ".weather-temperature")
    private WidgetTemperature weatherTemperatureWidget;

    @Name("Блок информации о восходе солнца")
    @FindBy(css = ".line.info-line:nth-last-of-type(4)")
    private WeatherInfo sunriseInfo;

    @Name("Блок информации о заходе солнца")
    @FindBy(css = ".line.info-line:nth-last-of-type(3)")
    private WeatherInfo sunsetInfo;

    @Name("Блок информации о ветре")
    @FindBy(css = ".line.info-line:nth-last-of-type(2)")
    private WeatherInfo windInfo;

    @Name("Блок информации о влажности")
    @FindBy(css = ".line.info-line:nth-last-of-type(1)")
    private WeatherInfo humidityInfo;

    public WebElement getWeatherImage() {
        return weatherImage;
    }

    public WidgetTemperature getWeatherTemperatureWidget() {
        return weatherTemperatureWidget;
    }

    public WeatherInfo getWindInfo() {
        return windInfo;
    }

    public WeatherInfo getSunriseInfo() {
        return sunriseInfo;
    }

    public WeatherInfo getSunsetInfo() {
        return sunsetInfo;
    }

    public WeatherInfo getHumidityInfo() {
        return humidityInfo;
    }

    public Rectangle getRect() {
        return null;
    }
}
