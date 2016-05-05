package ru.qatools.school.pages.blocks.widgetblocks.suggestblocks;

import org.openqa.selenium.Rectangle;
import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.annotations.Name;
import ru.yandex.qatools.htmlelements.element.HtmlElement;

/**
 * Created by onegines (Eugene Kirienko).
 */
public class CitySuggest extends HtmlElement {

    @Name("Название города")
    @FindBy(css = ".city__name")
    private HtmlElement cityName;

    @Name("Регион")
    @FindBy(css = ".city__country")
    private HtmlElement countryName;

    public Rectangle getRect() {
        return null;
    }

    public HtmlElement getCityName() {
        return cityName;
    }

    public HtmlElement getCountryName() {
        return countryName;
    }
}
