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

    public Rectangle getRect() {
        return null;
    }

    @Name("Город")
    @FindBy(css = ".card-title__primary .inplace")
    private HtmlElement city;

    @Name("Время и часовой пояс виджета")
    @FindBy(css = ".card-title__secondary")
    private HtmlElement titleTimeBlock;

    @Name("Suggets")
    @FindBy(css = ".city-suggest city city__name")
    private List<HtmlElement> suggestedCitiesList;

    public HtmlElement getTitleTimeBlock() {
        return titleTimeBlock;
    }

    public HtmlElement getCity() {
        return city;
    }

    public List<HtmlElement> getSuggestedCitiesList() {
        return suggestedCitiesList;
    }
}
