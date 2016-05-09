package ru.qatools.school.pages.blocks.widgetblocks;

import org.openqa.selenium.Rectangle;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.annotations.Name;
import ru.yandex.qatools.htmlelements.element.HtmlElement;

/**
 * @author raipc
 */
public class WidgetTemperature extends HtmlElement {
    @Name("Значение температуры")
    @FindBy(css = ".weather-temperature__digit")
    private WebElement weatherTemperatureDigit;

    @Name("Единица измерения")
    @FindBy(css = ".weather-temperature__unit")
    private WebElement weatherTemperatureUnit;

    public WebElement getWeatherTemperatureUnit() {
        return weatherTemperatureUnit;
    }

    public WebElement getWeatherTemperatureDigit() {
        return weatherTemperatureDigit;
    }

    @Override
    public Rectangle getRect() {
        return null;
    }
}
