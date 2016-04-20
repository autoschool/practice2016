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

    @Name("Название города в виджете")
    @FindBy(css = ".inplace.inplace_displayed")
    private WebElement place;

    @Name("Кнопка удаления виджета")
    @FindBy(css = ".remove-card.btn.btn-default")
    private WebElement removeBtn;

    @Name("Блок отображения температуры")
    @FindBy(css = ".weather-temperature.md-12")
    private WebElement weatherBlock;

    @Name("Единица измерения температуры")
    @FindBy(css = ".weather-temperature__unit")
    private WebElement weatherType;

    public WidgetText getWidgetText() {
        return widgetText;
    }

    public WidgetTitle getWidgetTitle() {
        return widgetTitle;
    }

    public WebElement getActions() {
        return actions;
    }

    public WebElement getPlace() {
        return place;
    }

    public WebElement getRemoveBtn() {
        return removeBtn;
    }

    public WebElement getWeatherBlock() {
        return weatherBlock;
    }

    public WebElement getWeatherType() {
        return weatherType;
    }

    public Rectangle getRect() {
        return null;
    }
}
