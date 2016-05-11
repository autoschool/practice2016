package ru.qatools.school.pages.blocks.widgetblocks.suggest;

import org.openqa.selenium.Rectangle;
import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.annotations.Name;
import ru.yandex.qatools.htmlelements.element.HtmlElement;

import java.util.List;

/**
 * @author gladnik (Nikolai Gladkov)
 */
@Name("Подсказка с городами")
@FindBy(css = ".city-suggest")
public class CitySuggest extends HtmlElement {

    @Name("Список городов из подсказки")
    private List<City> cities;


    public List<City> cities() {
        return cities;
    }

    public Rectangle getRect() {
        return null;
    }
}
