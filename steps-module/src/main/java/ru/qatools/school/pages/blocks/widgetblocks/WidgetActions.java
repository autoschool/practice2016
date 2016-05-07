package ru.qatools.school.pages.blocks.widgetblocks;

import org.openqa.selenium.Rectangle;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.annotations.Name;
import ru.yandex.qatools.htmlelements.element.HtmlElement;

/**
 * @author raipc
 */
public class WidgetActions extends HtmlElement {
    @Name("Кнопка удаления виджета")
    @FindBy(css = ".remove-card")
    private WebElement removingWidgetButton;

    @Override
    public Rectangle getRect() {
        return null;
    }

    public WebElement getRemovingWidgetButton() {
        return removingWidgetButton;
    }
}