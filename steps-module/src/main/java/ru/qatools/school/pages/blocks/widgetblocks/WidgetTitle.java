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

    @Name("Название города")
    @FindBy(css = ".inplace")
    private HtmlElement cityName;

    @Name("Время и дата")
    @FindBy(css = ".card-title__secondary")
    private HtmlElement currentTime;

    @Name("Список автозаполнения")
    @FindBy(css = ".city-suggest")
    private HtmlElement suggestedCitiesList;

    @Name("Элементы списка автозаполнения")
    @FindBy(css = ".city__name")
    private List<HtmlElement> suggestedCities;

    public HtmlElement getCityName(){
        return cityName;
    }

    public HtmlElement getCurrentTime() {
        return currentTime;
    }

    public HtmlElement getSuggestedCitiesList() {
        return suggestedCitiesList;
    }

    public List<HtmlElement> getSuggestedCities() {
        return suggestedCities;
    }

    public Rectangle getRect() {
        return null;
    }
}
