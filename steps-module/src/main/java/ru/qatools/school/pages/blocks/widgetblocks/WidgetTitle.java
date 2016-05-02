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
    @Name("Название города")
    @FindBy(css = ".card-title__primary .inplace_displayed")
    private WebElement cityName;

    public WebElement getCityName() {
        return cityName;
    }

    public Rectangle getRect() {
        return null;
    }
}
