package ru.qatools.school.pages.blocks.widgetblocks;

import org.openqa.selenium.Rectangle;
import ru.yandex.qatools.htmlelements.element.HtmlElement;
import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.annotations.Name;
import ru.yandex.qatools.htmlelements.element.HtmlElement;

/**
 * Created by kurau.
 * added by arrumm (Arkhipov Roman)
 */
public class WidgetTitle extends HtmlElement {

    @Name("Название города в заголовке")
    @FindBy(css = ".inplace")
    private WidgetTitle cityNameElement;


    public HtmlElement getCityNameElement() {
        return cityNameElement;
    }

    public Rectangle getRect() {
        return null;
    }
}
