package ru.qatools.school.pages.blocks.widgetblocks;

import org.openqa.selenium.Rectangle;
import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.annotations.Name;
import ru.yandex.qatools.htmlelements.element.HtmlElement;

import java.util.List;

/**
 * @author kurau
 * @author gladnik (Nikolai Gladkov)
 */
@Name("Текст виджета")
@FindBy(css = ".card-text")
public class WidgetText extends HtmlElement {

    @Name("Картинка текущей погоды")
    @FindBy(css = ".weather-image")
    private HtmlElement weatherImage;

    @Name("Температура")
    @FindBy(css = ".weather-temperature__digit")
    private HtmlElement temperatureValue;

    @Name("Единица измерения температуры")
    @FindBy(css = ".weather-temperature__unit")
    private HtmlElement temperatureUnit;

    @Name("Строка-разделитель")
    @FindBy(css = ".line.divider")
    private HtmlElement dividerLine;

    @Name("Список строк с дополнительными параметрами")
    private List<InfoLine> infoLines;


    public HtmlElement weatherImage() {
        return weatherImage;
    }

    public HtmlElement temperatureValue() {
        return temperatureValue;
    }

    public HtmlElement temperatureUnit() {
        return temperatureUnit;
    }

    public HtmlElement dividerLine() {
        return dividerLine;
    }

    public InfoLine sunriseInfo() {
        return infoLines.get(0);
    }

    public InfoLine sunsetInfo() {
        return infoLines.get(1);
    }

    public InfoLine windInfo() {
        return infoLines.get(2);
    }

    public InfoLine humidityInfo() {
        return infoLines.get(3);
    }

    public Rectangle getRect() {
        return null;
    }
}
