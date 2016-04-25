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
    private WebElement labelWidget;

    @Name("Иконка")
    @FindBy(css = ".info-line__image")
    private WebElement imageWidget;

    @Name("Значение")
    @FindBy(css = ".info-line__value")
    private WebElement valueWidget;

    public WebElement getValueWidget() {
        return valueWidget;
    }

    public WebElement getImageWidget() {
        return imageWidget;
    }

    public WebElement getLabelWidget() {
        return labelWidget;
    }

    @Override
    public Rectangle getRect() {
        return null;
    }
}
