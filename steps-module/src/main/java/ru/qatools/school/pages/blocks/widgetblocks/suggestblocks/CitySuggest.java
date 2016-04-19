package ru.qatools.school.pages.blocks.widgetblocks.suggestblocks;

import org.openqa.selenium.Rectangle;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.annotations.Name;
import ru.yandex.qatools.htmlelements.element.HtmlElement;

/**
 * Created by kurau.
 * Changed by onegines (Eugene Kirienko).
 */
public class CitySuggest extends HtmlElement {

    @Name("Предложенный город")
    @FindBy(css = ".city")
    private WebElement citySuggest;

    public Rectangle getRect() {
        return null;
    }

    public WebElement getCitySuggest() {
        return citySuggest;
    }
}
