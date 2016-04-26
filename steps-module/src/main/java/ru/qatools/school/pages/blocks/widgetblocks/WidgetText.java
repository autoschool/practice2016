package ru.qatools.school.pages.blocks.widgetblocks;

import org.openqa.selenium.Rectangle;
import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.annotations.Name;
import ru.yandex.qatools.htmlelements.element.HtmlElement;

import java.util.List;

/**
 * @author kormyshov
 */
public class WidgetText extends HtmlElement {

    @Name("Картинка текущей погоды")
    @FindBy(css = ".weather-image")
    private HtmlElement weatherImage;

    @Name("Список инфолайнов")
    @FindBy(css = ".line.info-line")
    private List<HtmlElement> infoLines;

    public HtmlElement getWeatherImage() {
        return weatherImage;
    }

    public HtmlElement getSunriseInfoLine(){
        return infoLines.get(0);
    }

    public HtmlElement getSunsetInfoLine(){
        return infoLines.get(1);
    }

    public HtmlElement getWindInfoLine(){
        return infoLines.get(2);
    }

    public HtmlElement getHumidityInfoLine(){
        return infoLines.get(3);
    }

    public Rectangle getRect() {
        return null;
    }
}
