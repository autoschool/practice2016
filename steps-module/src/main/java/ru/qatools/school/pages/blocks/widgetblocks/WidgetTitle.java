package ru.qatools.school.pages.blocks.widgetblocks;

import org.openqa.selenium.Rectangle;
import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.annotations.Name;
import ru.yandex.qatools.htmlelements.element.HtmlElement;

/**
 * @author kurau
 * @author gladnik (Nikolai Gladkov)
 */
@Name("Заголовок виджета")
@FindBy(css = ".card-title")
public class WidgetTitle extends HtmlElement {

    @Name("Поле для названия текущего города")
    @FindBy(css = ".inplace")
    private HtmlElement cityName;

    @Name("Время, дата, часовой пояс")
    @FindBy(css = ".card-title__secondary")
    private HtmlElement dateTimeLine;


    public HtmlElement cityName() {
        return cityName;
    }

    public HtmlElement dateTimeLine() {
        return dateTimeLine;
    }

    public Rectangle getRect() {
        return null;
    }
}
