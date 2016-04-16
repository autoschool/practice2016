package ru.qatools.school.pages.blocks.widgetblocks;

import org.openqa.selenium.Rectangle;
import ru.yandex.qatools.htmlelements.annotations.Name;
import ru.yandex.qatools.htmlelements.element.HtmlElement;
import org.openqa.selenium.support.FindBy;

/**
 * Created by kurau.
 */
public class WidgetTitle extends HtmlElement {

    @Name("Город заголовка")
    @FindBy(css = ".inplace.inplace_displayed")
    private HtmlElement city;

    public String getCity(){
        return city.getText();
    }

    public Rectangle getRect() {
        return null;
    }
}
