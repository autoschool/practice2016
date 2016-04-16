package ru.qatools.school.pages.blocks.widgetblocks;

import org.openqa.selenium.Rectangle;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.annotations.Name;
import ru.yandex.qatools.htmlelements.element.HtmlElement;

/**
 * Created by kurau.
 * added by arrumm (Arkhipov Roman)
 */
public class WidgetTitle extends HtmlElement {

    @Name("Название города в заголовке")
    @FindBy(css = ".card-title__primary")
    private WebElement cityName;

    public WebElement getNameOfCity() {
        return cityName;
    }

    public Rectangle getRect() {
        return null;
    }
}
