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

    @Name("Надпись sunrise")
    @FindBy(xpath = "")
    private WebElement sunriseText;

    public WebElement getWeatherImage() {
        return weatherImage;
    }

    public WebElement getSunriseText(){return sunriseText;}

    public Rectangle getRect() {
        return null;
    }
}
