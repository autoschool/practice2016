package ru.qatools.school.pages.blocks;

import org.openqa.selenium.Rectangle;
import org.openqa.selenium.WebElement;
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
    private WebElement actions;

    @Name("Кнопка удаления виджета")
    @FindBy(css = ".remove-card.btn.btn-default")
    private WebElement removeButton;

    @Name("Блок температуры")
    @FindBy(css = ".weather-temperature")
    private TemperatureBlock tempBlock;

    public WidgetText getWidgetText() {
        return widgetText;
    }

    public WidgetTitle getWidgetTitle() {
        return widgetTitle;
    }

    public WebElement getActions() {
        return actions;
    }

    public WebElement getRemoveButton(){
        return removeButton;
    }

    public TemperatureBlock getTempBlock() {
        return tempBlock;
    }

    public Rectangle getRect() {
        return null;
    }
}
