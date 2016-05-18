package ru.qatools.school.pages.blocks.widgetblocks.infoline;

import org.openqa.selenium.Rectangle;
import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.annotations.Name;
import ru.yandex.qatools.htmlelements.element.HtmlElement;

/**
 * Created by onegines (Eugene Kirienko).
 */
public class InfoLine extends HtmlElement {

    @Name("Параметр")
    @FindBy(css = ".info-line__title")
    private HtmlElement title;

    @Name("Значение")
    @FindBy(css = ".info-line__value")
    private HtmlElement value;

    @Name("Иконка")
    @FindBy(css = ".info-line__image")
    private HtmlElement image;

    public Rectangle getRect() {
        return null;
    }

    public HtmlElement getTitle() {
        return title;
    }

    public HtmlElement getValue() {
        return value;
    }

    public HtmlElement getImage() {
        return image;
    }
}
