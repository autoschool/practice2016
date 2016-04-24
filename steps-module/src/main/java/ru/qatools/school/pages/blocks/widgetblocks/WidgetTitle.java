package ru.qatools.school.pages.blocks.widgetblocks;

import org.openqa.selenium.Rectangle;
import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.annotations.Name;
import ru.yandex.qatools.htmlelements.element.HtmlElement;

/**
 * Created by kurau.
 */
public class WidgetTitle extends HtmlElement {

    @Name("Город заголовка")
    @FindBy(css = ".inplace.inplace_displayed")
    private HtmlElement cityElement;

    @Name("Дата заголовка")
    @FindBy(css = ".card-title__secondary")
    private HtmlElement dateTime;

    public HtmlElement getTitleCityElement() {
        return cityElement;
    }

    public HtmlElement getDateTime() {
        return dateTime;
    }

    public Rectangle getRect() {
        return null;
    }
}
