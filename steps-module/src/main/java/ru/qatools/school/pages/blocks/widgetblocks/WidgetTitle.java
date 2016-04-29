package ru.qatools.school.pages.blocks.widgetblocks;

import org.openqa.selenium.Rectangle;
import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.annotations.Name;
import ru.yandex.qatools.htmlelements.element.HtmlElement;

import java.util.List;

public class WidgetTitle extends HtmlElement {

    @Name("Город")
    @FindBy(css = ".inplace_displayed")
    private HtmlElement city;

    @Name("Поле ввода города")
    @FindBy(css = ".inplace_editable")
    private HtmlElement enterCity;

    @Name("Выпадающий список городов")
    @FindBy(css = ".city‐suggest .city")
    private List<HtmlElement> citiesSuggestList;

    public Rectangle getRect() {
        return null;
    }

    public HtmlElement getCity(){
        return city;
    }

    public HtmlElement getEnterCity(){
        return enterCity;
    }

    public List<HtmlElement> getCitiesSuggestList() { return citiesSuggestList; }
}
