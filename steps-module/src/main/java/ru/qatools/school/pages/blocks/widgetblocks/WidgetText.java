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

    public WebElement getWeatherImage() {
        return weatherImage;
    }

    @Name("Температура")
    @FindBy(css = ".weather-temperature")
    private WebElement temperature;

    public WebElement getTemperature() {
        return temperature;
    }


    /*@Name("Восход")
    @FindBy(css = ".info-line")
    private WebElement sunrise;

    public WebElement getSunrise() {
        return sunrise;
    }*/

    @Name("Список info-line элементов")
    @FindBy(css = ".line.info-line")
    private List<HtmlElement> infoLineList;

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

    /*@Name("Кнопка удаления виджета")
    @FindBy(name = "remove-card btn btn-default")
    private WebElement removeButton;

    public WebElement getRemoveButton() {
        return removeButton;
    }*/

    public Rectangle getRect() {
        return null;
    }
}
