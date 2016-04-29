package ru.qatools.school.pages.blocks.widgetblocks;

import org.openqa.selenium.Rectangle;
import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.annotations.Name;
import ru.yandex.qatools.htmlelements.element.HtmlElement;

/**
 * @author gladnik (Nikolai Gladkov)
 */
public class WidgetTitle extends HtmlElement {

    @Name("Поле для названия текущего города")
    @FindBy(css = ".inplace")
    private HtmlElement cityName;

    public HtmlElement cityName() {
        return cityName;
    }

    public Rectangle getRect() {
        return null;
    }
}
