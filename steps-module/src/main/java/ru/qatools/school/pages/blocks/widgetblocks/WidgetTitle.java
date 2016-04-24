package ru.qatools.school.pages.blocks.widgetblocks;

import org.openqa.selenium.Rectangle;
import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.annotations.Name;
import ru.yandex.qatools.htmlelements.element.HtmlElement;

import java.util.List;

/**
 * Created by kurau.
 */
public class WidgetTitle extends HtmlElement {

    @Name("Название город")
    @FindBy(css = ".inplace inplace_displayed")
    private HtmlElement cityName;

    @Name("Редактируемое поле города")
    @FindBy(css = ".inplace inplace_editable")
    private HtmlElement cityNameEditable;

    @Name("Время")
    @FindBy(css = ".card-title__secondary")
    private HtmlElement currentTime;

    @Name("Список автозаполнения")
    @FindBy(css = ".city__name")
    private List<HtmlElement> sugesstedCities;

    public HtmlElement getCityName(){
        return cityName;
    }

    public HtmlElement getCityNameEditable() {
        return cityNameEditable;
    }

    public HtmlElement getCurrentTime() {
        return currentTime;
    }

    public List<HtmlElement> getSugesstedCities() {
        return sugesstedCities;
    }

    public Rectangle getRect() {
        return null;
    }
}
