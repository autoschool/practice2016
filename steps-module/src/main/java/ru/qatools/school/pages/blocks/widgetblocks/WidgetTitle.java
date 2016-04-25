package ru.qatools.school.pages.blocks.widgetblocks;

import org.openqa.selenium.Rectangle;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.annotations.Name;
import ru.yandex.qatools.htmlelements.element.HtmlElement;

import java.util.List;

/**
 * Created by kurau.
 */
public class WidgetTitle extends HtmlElement {

    @Name("Название города")
    @FindBy(css = ".card-title__primary .inplace")
    private WebElement cityName;

    @Name("Время и часовой пояс")
    @FindBy(css = ".card-title__secondary")
    private HtmlElement timeText;

    public WebElement getCityName(){
        return cityName;
    }

    @Name("Suggets")
    @FindBy(css = ".city-suggest city city__name")
    private List<HtmlElement> suggestedCitiesList;

    public HtmlElement getTitleTimeBlock() {
    return timeText;
    }

    public List<HtmlElement> getSuggestedCitiesList() {
    return suggestedCitiesList;
    }

    public Rectangle getRect() {
        return null;
    }
}
