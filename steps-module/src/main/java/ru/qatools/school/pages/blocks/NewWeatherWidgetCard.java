package ru.qatools.school.pages.blocks;

import org.openqa.selenium.Rectangle;
import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.annotations.Name;
import ru.yandex.qatools.htmlelements.element.Button;
import ru.yandex.qatools.htmlelements.element.HtmlElement;

/**
 * Created by Gregory on 4/15/2016.
 */


public class NewWeatherWidgetCard extends HtmlElement {

    @Name("Add widget button")
    @FindBy(css = ".new-card__symbol")
    private Button addWidgetButton;

    public Button getAddWidgetButton() {
        return addWidgetButton;
    }


    public Rectangle getRect() {
        return null;
    }
}
