package ru.qatools.school.pages.blocks.widgetblocks;

import org.openqa.selenium.Rectangle;
import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.annotations.Name;
import ru.yandex.qatools.htmlelements.element.HtmlElement;

/**
 * Created by kurau.
 */
public class WidgetTitle extends HtmlElement {

    @Name("Город")
    @FindBy(css = ".card-title__primary")
    private HtmlElement primaryTitle;

    public HtmlElement getPrimaryTitle(){
        return primaryTitle;
    }

    public Rectangle getRect() {
        return null;
    }
}
