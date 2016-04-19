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

    @Name("Название города в заголовке виджета")
    @FindBy(css = ".card-title__primary")
    private WebElement weatherTitle;

    public WebElement getWeatherTitle() {
        return weatherTitle;
    }

    public Rectangle getRect() {
        return null;
    }
}
