package ru.qatools.school.pages.blocks.widgetblocks;

import org.openqa.selenium.Rectangle;
import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.annotations.Name;
import ru.yandex.qatools.htmlelements.element.HtmlElement;

/**
 * @author kormyshov
 */
public class WidgetSuggestCity extends HtmlElement{

    @Name("Название города")
    @FindBy(css = ".city__name")
    private HtmlElement nameCity;

    @Name("Страна города")
    @FindBy(css = "city__country")
    private HtmlElement country;

    public HtmlElement getNameCity(){
        return nameCity;
    }

    public HtmlElement getCountry(){
        return country;
    }

    public Rectangle getRect(){
        return null;
    }

}
