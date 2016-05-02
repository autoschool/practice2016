package ru.qatools.school.pages.blocks.widgetblocks;

import org.openqa.selenium.Rectangle;
import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.annotations.Name;
import ru.yandex.qatools.htmlelements.element.HtmlElement;

/**
 * @author kormyshov
 */
public class WidgetTitle extends HtmlElement {

    @Name("Поле с названием города")
    @FindBy(css = ".card-title__primary")
    private HtmlElement cityName;

    public HtmlElement getCityName() {
        return cityName;
    }

    public Rectangle getRect() {
        return null;
    }
}
