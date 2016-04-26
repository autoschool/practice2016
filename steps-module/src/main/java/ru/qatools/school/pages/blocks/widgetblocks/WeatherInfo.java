package ru.qatools.school.pages.blocks.widgetblocks;

import org.openqa.selenium.Rectangle;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.annotations.Name;
import ru.yandex.qatools.htmlelements.element.HtmlElement;

/**
 * @author raipc
 */
public class WeatherInfo extends HtmlElement {
    @Name("Параметр")
    @FindBy(css = ".info-line__title")
    private WebElement label;

    @Name("Иконка")
    @FindBy(css = ".info-line__image")
    private WebElement image;

    @Name("Значение")
    @FindBy(css = ".info-line__value")
    private WebElement value;

    public WebElement getValue() {
        return value;
    }

    public WebElement getImage() {
        return image;
    }

    public WebElement getLabel() {
        return label;
    }

    @Override
    public Rectangle getRect() {
        return null;
    }
}
