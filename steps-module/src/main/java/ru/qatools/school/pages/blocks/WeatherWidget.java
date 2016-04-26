package ru.qatools.school.pages.blocks;

import org.openqa.selenium.Rectangle;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ru.qatools.school.pages.blocks.widgetblocks.WidgetActions;
import ru.qatools.school.pages.blocks.widgetblocks.WidgetText;
import ru.qatools.school.pages.blocks.widgetblocks.WidgetTitle;
import ru.yandex.qatools.htmlelements.annotations.Name;
import ru.yandex.qatools.htmlelements.element.HtmlElement;

/**
 * Created by kurau.
 */
public class WeatherWidget extends HtmlElement {

    @Name("Заголовок виджета")
    @FindBy(css = ".card-title")
    private WidgetTitle widgetTitle;

    @Name("Текст виджета")
    @FindBy(css = ".card-text")
    private WidgetText widgetText;

    @Name("Панель управления виджетом")
    @FindBy(css = ".card-actions")
    private WidgetActions actions;

    public WidgetText getWidgetText() {
        return widgetText;
    }

    public WidgetTitle getWidgetTitle() {
        return widgetTitle;
    }

    public WidgetActions getWidgetActions() {
        return actions;
    }

    public Rectangle getRect() {
        return null;
    }
}
