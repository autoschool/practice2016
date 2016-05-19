package ru.qatools.school.pages.blocks.widgetblocks;

import org.openqa.selenium.Rectangle;
import org.openqa.selenium.support.FindBy;
import ru.qatools.school.pages.blocks.widgetblocks.suggestblocks.CitySuggest;
import ru.yandex.qatools.htmlelements.annotations.Name;
import ru.yandex.qatools.htmlelements.element.HtmlElement;

import java.util.List;

/**
 * Created by kurau.
 * Changed by onegines (Eugene Kirienko).
 */
public class WidgetTitle extends HtmlElement {

    @Name("Название города")
    @FindBy(css = ".inplace")
    private HtmlElement cityName;

    @Name("Текущее время и дата")
    @FindBy(css = ".card-title__secondary > div > div")
    private HtmlElement currentTimeAndDate;

    @Name("Блок предложенных городов")
    @FindBy(css = ".city-suggest")
    private HtmlElement citySuggestBlock;

    @Name("Список предложенных городов")
    @FindBy(css = ".city")
    private List<CitySuggest> citySuggests;

    public Rectangle getRect() {
        return null;
    }

    public HtmlElement getCityName() {
        return cityName;
    }

    public List<CitySuggest> getCitySuggests() {
        return citySuggests;
    }

    public HtmlElement getCurrentTimeAndDate() {
        return currentTimeAndDate;
    }

    public CitySuggest getFirstSuggest() {
        return getCitySuggests().get(0);
    }

    public HtmlElement getCitySuggestBlock() {
        return citySuggestBlock;
    }
}
