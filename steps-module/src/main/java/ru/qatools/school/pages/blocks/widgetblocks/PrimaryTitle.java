package ru.qatools.school.pages.blocks.widgetblocks;

import org.openqa.selenium.Rectangle;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.annotations.Name;
import ru.yandex.qatools.htmlelements.element.HtmlElement;

/**
 * Created by sergey.petrov on 15.04.2016.
 */
public class PrimaryTitle extends HtmlElement{

    @Name("Название города")
    @FindBy(css = ".inplace")
    private WebElement city;

    public String getCityName(){
        return city.getText();
    }

    public Rectangle getRect(){
        return null;
    }


}
