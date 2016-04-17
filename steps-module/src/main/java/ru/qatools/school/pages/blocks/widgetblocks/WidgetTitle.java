package ru.qatools.school.pages.blocks.widgetblocks;

import org.openqa.selenium.Rectangle;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.annotations.Name;
import ru.yandex.qatools.htmlelements.element.HtmlElement;

/**
 * Created by kurau.
 * Changed by onegines (Eugene Kirienko).
 */
public class WidgetTitle extends HtmlElement {

    @Name("Название города в заголовке")
    @FindBy(css = ".inplace")
    private WebElement cityNameInTitle;

    public Rectangle getRect() {
        return null;
    }

    public WebElement getCityNameInTitle() {
        return cityNameInTitle;
    }
}
