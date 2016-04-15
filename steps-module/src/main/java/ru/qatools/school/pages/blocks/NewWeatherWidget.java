package ru.qatools.school.pages.blocks;

import org.openqa.selenium.Rectangle;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ru.qatools.school.pages.blocks.widgetblocks.WidgetText;
import ru.qatools.school.pages.blocks.widgetblocks.WidgetTitle;
import ru.yandex.qatools.htmlelements.annotations.Name;
import ru.yandex.qatools.htmlelements.element.HtmlElement;

/**
 * Created by Gregory on 4/15/2016.
 */


public class NewWeatherWidget extends HtmlElement {

    @Name("New widget button")
    @FindBy(css = ".new-card__symbol")
    private WebElement newWeatherWidgetButton;

    public WebElement getNewWeatherWidgetButton() {
        return newWeatherWidgetButton;
    }


    public Rectangle getRect() {
        return null;
    }
}
