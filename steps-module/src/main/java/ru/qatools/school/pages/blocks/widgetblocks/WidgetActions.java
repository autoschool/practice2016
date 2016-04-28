package ru.qatools.school.pages.blocks.widgetblocks;

import org.openqa.selenium.Rectangle;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.annotations.Name;
import ru.yandex.qatools.htmlelements.element.HtmlElement;

/**
 * Created by Gavrilov_IS on 23.04.2016.
 */
public class WidgetActions  extends HtmlElement {

    @Name("Кнопка удаления виджета")
    @FindBy(css = ".remove-card .btn")
    private WebElement removeBtn;

    public WebElement removeBtn(){
        return this.removeBtn;
    }

    @Override
    public Rectangle getRect() {
        return null;
    }

}
