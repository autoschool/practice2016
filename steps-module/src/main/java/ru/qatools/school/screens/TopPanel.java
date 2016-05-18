package ru.qatools.school.screens;

import org.openqa.selenium.Rectangle;
import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.annotations.Name;
import ru.yandex.qatools.htmlelements.element.HtmlElement;

/**
 * @author totallynotkate (Kate Kocijevska)
 */
public class TopPanel extends HtmlElement{

    @Name("Время поездки")
    @FindBy(id="ru.yandex.metro:id/tv_time")
    private HtmlElement rideTimeString;

    public HtmlElement rideTimeString() {
        return rideTimeString;
    }

    @Override
    public Rectangle getRect() {
        return null;
    }
}
