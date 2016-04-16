package ru.qatools.school.pages.blocks.widgetblocks;

import org.openqa.selenium.Rectangle;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.annotations.Name;
import ru.yandex.qatools.htmlelements.element.HtmlElement;

/**
 * Created by kurau.
 */
public class WidgetTitle extends HtmlElement {
    @Name("Картинка текущей погоды")
    @FindBy(css = ".weather-image")
    private WebElement weatherImage;

    @Name("Название города")
    @FindBy(css = ".card-title__primary")
    private WebElement cityName;

    public WebElement getCityName() {return cityName;}

    public WebElement getWeatherImage() {
        return weatherImage;
    }

    public Rectangle getRect() {
        return null;
    }
}
