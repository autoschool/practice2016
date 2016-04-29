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

    @Name("Пиктограмма текущей погоды")
    @FindBy(css = ".weather-image")
    private HtmlElement weatherImage;

    @Name("Строка с текущей температурой")
    @FindBy(css = ".weather-temperature")
    private HtmlElement weatherTemperature;

    @Name("Полоса, отделяющая строку с температурой от инфоблоков")
    @FindBy(css = ".line.divider")
    private HtmlElement dividerLine;

    @Name("Список инфолайн элементов")
    @FindBy(css = ".line.info-line")
    private List<HtmlElement> infoLineList;

    public Rectangle getRect() {
        return null;
    }

    public HtmlElement getWeatherImage() {
        return weatherImage;
    }

    public HtmlElement getTemperature() {
        return weatherTemperature;
    }

    public HtmlElement getDividerLine() {
        return dividerLine;
    }

    public HtmlElement getSunriseInfoLine() {
        return infoLineList.get(0);
    }

    public HtmlElement getSunsetInfoLine() {
        return infoLineList.get(1);
    }

    public HtmlElement getWindInfoLine() {
        return infoLineList.get(2);
    }

    public HtmlElement getHumidityInfoLine() {
        return infoLineList.get(3);
    }
}
