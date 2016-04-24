package ru.qatools.school.pages.blocks.widgetblocks;

import org.openqa.selenium.Rectangle;
import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.annotations.Name;
import ru.yandex.qatools.htmlelements.element.HtmlElement;

/**
 * Created by vananos on 4/24/16.
 */
public class InfoLine extends HtmlElement {

    @Name("Заголовок")
    @FindBy(css = ".info-line__title")
    HtmlElement title;

    @Name("Значение")
    @FindBy(css = ".info-line__value")
    HtmlElement value;

    @Name("Картинка")
    @FindBy(css = ".info-line__image")
    HtmlElement image;

    public HtmlElement getValue() {
        return value;
    }

    public HtmlElement getImage() {
        return image;
    }

    public HtmlElement getTitle() {
        return title;
    }

    public String getTextRepresentation() {
        return title.getText() + " " + value.getText();
    }

    @Override
    public Rectangle getRect() {
        return null;
    }
}
