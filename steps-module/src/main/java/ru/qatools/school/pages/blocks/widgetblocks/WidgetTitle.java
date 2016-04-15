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

    @Name("Поле с названием города")
    @FindBy(css = "span.inplace")
    private WebElement cityTitle;

    public Rectangle getRect() {
        return null;
    }

    public WebElement getCity(){
        return cityTitle;
    }
}
