package ru.qatools.school.screens;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import ru.yandex.qatools.htmlelements.annotations.Name;
import ru.yandex.qatools.htmlelements.element.HtmlElement;
import ru.yandex.qatools.htmlelements.loader.decorator.HtmlElementDecorator;
import ru.yandex.qatools.htmlelements.loader.decorator.HtmlElementLocatorFactory;

/**
 * @author arrumm 19.05.2016.
 */
public class MainScreen {

    public MainScreen(RemoteWebDriver driver) {
        PageFactory.initElements(new HtmlElementDecorator(new HtmlElementLocatorFactory(driver)), this);
    }

    @Name("Поле ввода начальной станции")
    @FindBy(id = "tv_from_name")
    private HtmlElement fromStationField;

    @Name("Поле ввода конечной станции")
    @FindBy(id = "tv_to_name")
    private HtmlElement toStationField;

    @Name("Поле ожидаемое время поездки")
    @FindBy(id = "tv_time")
    private HtmlElement timeField;

    public HtmlElement fromStationField() {
        return fromStationField;
    }

    public HtmlElement toStationField() {
        return toStationField;
    }

    public HtmlElement timeField() {
        return timeField;
    }

}
