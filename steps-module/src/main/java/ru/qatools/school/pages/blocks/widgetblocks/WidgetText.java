package ru.qatools.school.pages.blocks.widgetblocks;

import org.openqa.selenium.Rectangle;
import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.annotations.Name;
import ru.yandex.qatools.htmlelements.element.HtmlElement;

import java.util.List;

/**
 * Created by kurau.
 */
public class WidgetText extends HtmlElement {

    @Name("Картинка текущей погоды")
    @FindBy(css = ".weather-image")
    private HtmlElement weatherImage;

    @Name("Температура")
    @FindBy(css = ".weather-temperature.md-12")
    private HtmlElement temperature;

    @Name("Погодные данные")
    @FindBy(css = ".line.info-line")
    private List<HtmlElement> weatherData;

    public HtmlElement getWeatherImage() {
        return weatherImage;
    }

    public HtmlElement getTemperature() {
        return temperature;
    }

    public List<HtmlElement> getWeatherData() {
        return weatherData;
    }

    public Rectangle getRect() {
        return null;
    }
}
