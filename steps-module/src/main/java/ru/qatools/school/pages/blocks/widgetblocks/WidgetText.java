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

    @Name("Блок отображения температуры")
    @FindBy(css = ".weather-temperature")
    private WebElement temperature;

    @Name("Показание температуры")
    @FindBy(css = ".weather-temperature__digit")
    private WebElement digitTemperature;

    @Name("Единицы измерения температуры")
    @FindBy(css = ".weather-temperature__unit")
    private WebElement unitsTemperature;

    @Name("Список блоков infoline")
    @FindBy(css = ".line.info-line")
    private List<HtmlElement> infoLines;


    public WebElement digitTemperature(){
        return this.digitTemperature;
    }

    public WebElement unitsTemperature(){
        return this.unitsTemperature;
    }

    public WebElement sunriseLine(){
        return this.infoLines.get(0);
    }
    public WebElement sunsetLine(){
        return this.infoLines.get(1);
    }
    public WebElement windLine(){
        return this.infoLines.get(2);
    }

    public WebElement humidityLine(){
        return this.infoLines.get(3);
    }

    public WebElement temperature(){
        return this.temperature;
    }

    public WebElement getWeatherImage() {
        return weatherImage;
    }

    public Rectangle getRect() {
        return null;
    }
}
