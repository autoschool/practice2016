package ru.qatools.school.pages.blocks.widgetblocks;

import org.openqa.selenium.Rectangle;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.annotations.Name;
import ru.yandex.qatools.htmlelements.element.Button;
import ru.yandex.qatools.htmlelements.element.HtmlElement;

public class WidgetActions extends HtmlElement {

    @Name("Remove widget button")
    @FindBy(css = ".remove-card.btn.btn-default")
    private WebElement removeWidgetButton;

    public WebElement getRemoveWidgetButton() {
        return removeWidgetButton;
    }

    public Rectangle getRect() {
        return null;
    }
}