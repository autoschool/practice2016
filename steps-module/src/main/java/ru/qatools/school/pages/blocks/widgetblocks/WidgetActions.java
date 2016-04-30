package ru.qatools.school.pages.blocks.widgetblocks;

import org.openqa.selenium.Rectangle;
import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.annotations.Name;
import ru.yandex.qatools.htmlelements.element.HtmlElement;

/**
 * Created by kormyshov
 */
public class WidgetActions extends HtmlElement {

    @Name("Кнопка удаления виджета")
    @FindBy(css = ".remove-card.btn.btn-default")
    private HtmlElement removeButton;

    public HtmlElement getRemoveButton(){
        return removeButton;
    }

    @Override
    public Rectangle getRect(){
        return null;
    }
}
