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
    @FindBy(css = ".inplace")
    private HtmlElement cityName;

    @Name("Дата")
    @FindBy(css = ".card-title__secondary")
    private HtmlElement date;

    @Name("Блок предлагаемых городов")
    @FindBy(css = ".city-suggest")
    private WidgetSuggestCities citySuggest;

    public HtmlElement getCityName() {
        return cityName;
    }

    public HtmlElement getDate(){
        return date;
    }

    public WidgetSuggestCities getCitySuggest(){
        return citySuggest;
    }

    public Rectangle getRect() {
        return null;
    }
}
