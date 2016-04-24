package ru.qatools.school.pages.blocks.widgetblocks;

import org.openqa.selenium.Rectangle;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.annotations.Name;
import ru.yandex.qatools.htmlelements.element.HtmlElement;

/**
 * Created by onodee on 24.04.2016.
 */
public class WidgetActions extends HtmlElement {

    @Name("Удалить виджет")
    @FindBy(css = ".remove-card.btn.btn-default")
    private WebElement removeCard;

    public WebElement getRemoveCard() {
        return removeCard;
    }

    @Override
    public Rectangle getRect() {
        return null;
    }
}
