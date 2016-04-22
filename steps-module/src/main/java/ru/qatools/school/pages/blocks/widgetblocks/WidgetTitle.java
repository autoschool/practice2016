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

    @Name("Название города в заголовке")
    @FindBy(css = ".inplace")
    private HtmlElement cityName;

    @Name("Список предложенных городов")
    @FindBy(css = ".city-suggest")
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

    public CitySuggest getFirstSuggest() {
        return getCitySuggests().get(0);
    }
}
