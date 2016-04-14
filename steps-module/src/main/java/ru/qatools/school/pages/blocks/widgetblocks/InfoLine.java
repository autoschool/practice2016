package ru.qatools.school.pages.blocks.widgetblocks;

import org.openqa.selenium.Rectangle;
import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.annotations.Name;
import ru.yandex.qatools.htmlelements.element.HtmlElement;

import java.util.Arrays;
import java.util.List;

/**
 * @author gladnik (Nikolai Gladkov)
 */
@Name("Строка дополнительной информации")
@FindBy(css = ".line.info-line")
public class InfoLine extends HtmlElement {

    @Name("Название параметра")
    @FindBy(css = ".info-line__title")
    private HtmlElement title;

    @Name("Значение параметра")
    @FindBy(css = ".info-line__value")
    private HtmlElement value;

    @Name("Иконка")
    @FindBy(css = ".info-line__image")
    private HtmlElement image;


    public HtmlElement title() {
        return title;
    }

    public HtmlElement value() {
        return value;
    }

    public HtmlElement image() {
        return image;
    }

    public List<HtmlElement> allElements() {
        return Arrays.asList(title, value, image);
    }

    public Rectangle getRect() {
        return null;
    }
}
