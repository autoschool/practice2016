package ru.qatools.school.pages.blocks.widgetblocks;

import org.openqa.selenium.Rectangle;
import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.annotations.Name;
import ru.yandex.qatools.htmlelements.element.HtmlElement;



public class TemperatureBlock extends HtmlElement {

    @Name("Значение температуры")
    @FindBy(css = ".weather-temperature__digit")
    private HtmlElement tempVal;

    @Name("Мера измерения температуры")
    @FindBy(css = ".weather-temperature__unit")
    private HtmlElement tempFormat;


    public HtmlElement getTemperatureValueBlock() {
        return tempVal;
    }

    public HtmlElement getTemperatureFormatBlock() {
        return tempFormat;
    }

    public String getRepresentation() {
        return tempVal.getText() + " " + tempFormat.getText();
    }

    public Rectangle getRect() {
        return null;
    }
}
