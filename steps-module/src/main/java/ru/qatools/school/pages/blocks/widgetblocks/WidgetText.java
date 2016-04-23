package ru.qatools.school.pages.blocks.widgetblocks;

import org.openqa.selenium.Rectangle;
import org.openqa.selenium.support.FindBy;
import ru.qatools.school.pages.blocks.widgetblocks.infoline.InfoLine;
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

    @Name("Цифра градусов")
    @FindBy(css = ".weather-temperature__digit")
    private HtmlElement weatherTemperatureDigit;

    @Name("Условная единица градусов")
    @FindBy(css = ".weather-temperature__unit")
    private HtmlElement weatherTemperatureUnit;

    @Name("Строки дополнительной информации")
    @FindBy(css = ".line.info-line")
    private List<InfoLine> infoLines;

    public HtmlElement getWeatherImage() {
        return weatherImage;
    }

    public HtmlElement getWeatherTemperatureDigit() {
        return weatherTemperatureDigit;
    }

    public HtmlElement getWeatherTemperatureUnit() {
        return weatherTemperatureUnit;
    }

    public List<InfoLine> getInfoLines() {
        return infoLines;
    }

    public InfoLine getSunriseLine() {
        return getInfoLines().get(0);
    }

    public InfoLine getSunsetLine() {
        return getInfoLines().get(1);
    }

    public InfoLine getWindLine() {
        return getInfoLines().get(2);
    }

    public InfoLine getHumidityLine() {
        return getInfoLines().get(3);
    }

    public Rectangle getRect() {
        return null;
    }
}
