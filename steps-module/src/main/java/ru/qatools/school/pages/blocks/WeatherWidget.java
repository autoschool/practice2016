package ru.qatools.school.pages.blocks;

import org.openqa.selenium.Rectangle;
import org.openqa.selenium.support.FindBy;
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
    private HtmlElement widgetActions;

    public WidgetTitle widgetTitle() {
        return widgetTitle;
    }

    public WidgetText widgetText() {
        return widgetText;
    }

    public HtmlElement widgetActions() {
        return widgetActions;
    }

    public Rectangle getRect() {
        return null;
    }
}
