package ru.qatools.school.screens;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import ru.yandex.qatools.htmlelements.annotations.Name;
import ru.yandex.qatools.htmlelements.element.HtmlElement;
import ru.yandex.qatools.htmlelements.loader.decorator.HtmlElementDecorator;
import ru.yandex.qatools.htmlelements.loader.decorator.HtmlElementLocatorFactory;

/**
 * @author ava1on
 */
public class MainScreen {

    public MainScreen(WebDriver driver){
        PageFactory.initElements(new HtmlElementDecorator(new HtmlElementLocatorFactory(driver)),this);
    }

    @Name("Поле From")
    @FindBy(id = "ru.yandex.metro:id/tv_from_name")
    private HtmlElement from;

    @Name("Поле To")
    @FindBy(id = "ru.yandex.metro:id/tv_to_name")
    private HtmlElement to;

    @Name("Время в пути")
    @FindBy(id = "ru.yandex.metro:id/tv_time")
    private HtmlElement travelTime;

    public HtmlElement getFrom() {
        return from;
    }

    public HtmlElement getTo() {
        return to;
    }

    public HtmlElement getTravelTime() {
        return travelTime;
    }
}
