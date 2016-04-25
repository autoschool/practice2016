package ru.qatools.school.pages.blocks.widgetblocks;

import org.openqa.selenium.Rectangle;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.annotations.Name;
import ru.yandex.qatools.htmlelements.element.HtmlElement;

import java.util.List;


public class WidgetText extends HtmlElement {

    @Name("Картинка текущей погоды")
    @FindBy(css = ".weather-image")
    private WebElement weatherImage;

    @Name("Список инфолайн элементов")
    @FindBy(css = ".line.info-line")
    private List<InfoBlock> infoLineList;

    @Name("Значение текущей температуры")
    @FindBy(css = ".weather-temperature")
    private HtmlElement currentTemperature;

    public WebElement getWeatherImage() {
        return weatherImage;
    }

    public HtmlElement getCurrentTemperature() {
            return currentTemperature;
     }



    public InfoBlock getSunriseInfoLine() {
        return infoLineList.get(0);
    }

    public InfoBlock getSunsetInfoLine() {
        return infoLineList.get(1);
    }

    public InfoBlock getWindInfoLine() {
        return infoLineList.get(2);
    }
    public InfoBlock getHumidityInfoLine() {
        return infoLineList.get(3);
    }

    public Rectangle getRect() {
        return null;
    }
}
