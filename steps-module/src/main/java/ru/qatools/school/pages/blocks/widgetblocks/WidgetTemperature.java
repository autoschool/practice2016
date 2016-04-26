package ru.qatools.school.pages.blocks.widgetblocks;

import org.openqa.selenium.Rectangle;
import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.annotations.Name;
import ru.yandex.qatools.htmlelements.element.HtmlElement;

/**
 * Created by kormyshov
 */
public class WidgetTemperature extends HtmlElement {

    @Name("Значение температуры")
    @FindBy(css = ".weather-temperature__digit")
    private HtmlElement value;

    @Name("Мера измерения температуры")
    @FindBy(css = ".weather-temperature__unit")
    private HtmlElement measure;

    public HtmlElement getValue(){
        return value;
    }

    public HtmlElement getMeasure(){
        return measure;
    }

    public String toString(){
        return value.getText() + " " + measure.getText();
    }

    public Rectangle getRect(){
        return null;
    }
}
