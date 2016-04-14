package ru.qatools.school.pages.blocks.widgetblocks;

import org.openqa.selenium.By;
import org.openqa.selenium.Rectangle;
import ru.yandex.qatools.htmlelements.element.HtmlElement;

/**
 * Created by kurau.
 */
public class WidgetTitle extends HtmlElement {

    public String getCity(){
        return findElement(By.cssSelector(".inplace.inplace_displayed")).getText();
    }

    public Rectangle getRect() {
        return null;
    }
}
