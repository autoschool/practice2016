package ru.qatools.school.pages.blocks.widgetblocks;

import org.openqa.selenium.Rectangle;
import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.annotations.Name;
import ru.yandex.qatools.htmlelements.element.HtmlElement;

/**
 * Created by kurau.
 */
public class WidgetTitle extends HtmlElement {
    @Name("Картинка текущей погоды")
    @FindBy(css = ".weather-image")
    private HtmlElement weatherImage;

    @Name("Название города")
    @FindBy(css = ".card-title__primary")
    private HtmlElement cityName;

    public HtmlElement getCityName() {
        return cityName;
    }

    public HtmlElement getWeatherImage() {
        return weatherImage;
    }

    public Rectangle getRect() {
        return null;
    }
}
