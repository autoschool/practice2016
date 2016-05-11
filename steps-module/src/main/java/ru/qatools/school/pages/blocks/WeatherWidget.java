package ru.qatools.school.pages.blocks;

import org.openqa.selenium.Rectangle;
import org.openqa.selenium.support.FindBy;
import ru.qatools.school.pages.blocks.widgetblocks.WidgetActions;
import ru.qatools.school.pages.blocks.widgetblocks.WidgetText;
import ru.qatools.school.pages.blocks.widgetblocks.WidgetTitle;
import ru.qatools.school.pages.blocks.widgetblocks.suggest.CitySuggest;
import ru.yandex.qatools.htmlelements.annotations.Name;
import ru.yandex.qatools.htmlelements.element.HtmlElement;

/**
 * @author kurau
 * @author gladnik (Nikolai Gladkov)
 */
@Name("Виджет погоды")
@FindBy(css = ".card.card_md")
public class WeatherWidget extends HtmlElement {

    private WidgetTitle widgetTitle;
    private WidgetText widgetText;
    private WidgetActions widgetActions;
    private CitySuggest suggest;


    public WidgetTitle widgetTitle() {
        return widgetTitle;
    }

    public WidgetText widgetText() {
        return widgetText;
    }

    public WidgetActions widgetActions() {
        return widgetActions;
    }

    public CitySuggest suggest() {
        return suggest;
    }

    public Rectangle getRect() {
        return null;
    }
}
