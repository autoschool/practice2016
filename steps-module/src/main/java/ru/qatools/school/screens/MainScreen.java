package ru.qatools.school.screens;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import ru.yandex.qatools.htmlelements.annotations.Name;
import ru.yandex.qatools.htmlelements.element.HtmlElement;
import ru.yandex.qatools.htmlelements.loader.decorator.HtmlElementDecorator;
import ru.yandex.qatools.htmlelements.loader.decorator.HtmlElementLocatorFactory;

/**
 * @author gladnik (Nikolai Gladkov)
 */
public class MainScreen {

    @Name("Поле ввода начальной станции")
    @FindBy(id = "ru.yandex.metro:id/tv_from_name")
    private HtmlElement tvFromName;

    @Name("Поле ввода конечной станции")
    @FindBy(id = "ru.yandex.metro:id/tv_to_name")
    private HtmlElement tvToName;

    @Name("Время в пути")
    @FindBy(id = "ru.yandex.metro:id/tv_time")
    private HtmlElement tvTime;


    public MainScreen(WebDriver driver) {
        PageFactory.initElements(new HtmlElementDecorator(new HtmlElementLocatorFactory(driver)), this);
    }

    public HtmlElement tvFromName() {
        return tvFromName;
    }

    public HtmlElement tvToName() {
        return tvToName;
    }

    public HtmlElement tvTime() {
        return tvTime;
    }

}
