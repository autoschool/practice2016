package ru.qatools.school.pages.blocks.widgetblocks.suggest;

import org.openqa.selenium.Rectangle;
import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.annotations.Name;
import ru.yandex.qatools.htmlelements.element.HtmlElement;

/**
 * @author gladnik (Nikolai Gladkov)
 */
@Name("Город из подсказки")
@FindBy(css = ".city")
public class City extends HtmlElement {

    @Name("Название города")
    @FindBy(css = ".city__name")
    private HtmlElement cityName;
    @Name("Регион")
    @FindBy(css = ".city__country")
    private HtmlElement cityCountry;


    public HtmlElement cityName() {
        return cityName;
    }

    public HtmlElement cityCountry() {
        return cityCountry;
    }

    public Rectangle getRect() {
        return null;
    }
}
