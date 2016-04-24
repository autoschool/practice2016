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

    @Name("Название города в виджете")
    @FindBy(css = ".inplace.inplace_displayed")
    private WebElement inPlaceText;

    @FindBy(css = ".inplace.inplace_editable")
    private WebElement editableInPlace;

    public WebElement text() {
        return inPlaceText;
    }

    public WebElement inputPlace() {
        return editableInPlace;
    }

    public Rectangle getRect() {
        return null;
    }
}
