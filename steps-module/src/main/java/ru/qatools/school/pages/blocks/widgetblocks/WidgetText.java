package ru.qatools.school.pages.blocks.widgetblocks;

import org.openqa.selenium.Rectangle;
import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.annotations.Name;
import ru.yandex.qatools.htmlelements.element.HtmlElement;

/**
 * Created by kurau.
 */
public class WidgetText extends HtmlElement {

    @Name("Картинка текущей погоды")
    @FindBy(css = ".weather-image")
    private HtmlElement weatherImage;

    @Name("Обозначение системы измерения градусов")
    @FindBy(css = ".weather-temperature__unit")
    private HtmlElement tempUnits;

    @Name("Градусы")
    @FindBy(css = ".weather-temperature__digit")
    private HtmlElement tempDigits;

    public HtmlElement getWeatherImage() {
        return weatherImage;
    }

    public HtmlElement getTempUnits() { return tempUnits; }

    public HtmlElement getTempDigits() { return tempDigits; }

    public Rectangle getRect() {
        return null;
    }
}
