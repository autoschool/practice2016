package ru.qatools.school.pages.blocks.widgetblocks;

import org.openqa.selenium.Rectangle;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.annotations.Name;
import ru.yandex.qatools.htmlelements.element.HtmlElement;

import java.util.List;

/**
 * Created by kurau.
 */
public class WidgetText extends HtmlElement {

    @Name("Картинка текущей погоды")
    @FindBy(css = ".weather-image")
    private WebElement weatherImage;

    @Name("Поля с дополнительной информацией")
    @FindBy(css = ".info-line")
    private List<InfoLine> infoLines;

    @Name("Блок температуры")
    @FindBy(css = ".weather-temperature")
    private TemperatureBlock tempBlock;

    public WebElement getWeatherImage() {
        return weatherImage;
    }

    public List<InfoLine> getInfoLines() {
        return infoLines;
    }

    public TemperatureBlock getTempBlock() {
        return tempBlock;
    }

    public Rectangle getRect() {
        return null;
    }
}
