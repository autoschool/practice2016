package ru.qatools.school.pages.blocks.widgetblocks;

import org.openqa.selenium.Rectangle;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.annotations.Name;
import ru.yandex.qatools.htmlelements.element.HtmlElement;

/**
 * Created by kurau.
 */
public class WidgetTitle extends HtmlElement {

    @Name("Город в заголовке виджета")
    @FindBy(css = ".inplace_displayed")
    private WebElement weatherCity;

    public WebElement getCity(){
        return this.weatherCity;
    }

    public Rectangle getRect() {
        return null;
    }
}
