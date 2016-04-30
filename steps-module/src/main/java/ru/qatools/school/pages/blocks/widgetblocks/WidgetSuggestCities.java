package ru.qatools.school.pages.blocks.widgetblocks;

import org.openqa.selenium.Rectangle;
import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.annotations.Name;
import ru.yandex.qatools.htmlelements.element.HtmlElement;

import java.util.List;

/**
 * @author kormyshov
 */
public class WidgetSuggestCities extends HtmlElement {

    @Name("Предлагаемые города")
    @FindBy(css = ".city")
    private List<WidgetSuggestCity> list;

    public List<WidgetSuggestCity> getList(){
        return list;
    }

    public Rectangle getRect(){
        return null;
    }
}
