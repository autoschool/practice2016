package ru.qatools.school.pages.blocks.widgetblocks;

import org.openqa.selenium.Rectangle;
import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.annotations.Name;
import ru.yandex.qatools.htmlelements.element.HtmlElement;

/**
 * Created by onegines (Eugene Kirienko).
 */
public class WidgetActions extends HtmlElement {

    @Name("Кнопка удаления виджета")
    @FindBy(css = ".remove-card")
    private HtmlElement removeWidgetButton;

    public HtmlElement getRemoveWidgetButton() {
        return removeWidgetButton;
    }

    public Rectangle getRect() {
        return null;
    }
}
