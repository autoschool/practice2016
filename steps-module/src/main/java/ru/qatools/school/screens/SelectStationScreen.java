package ru.qatools.school.screens;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import ru.yandex.qatools.htmlelements.annotations.Name;
import ru.yandex.qatools.htmlelements.element.HtmlElement;
import ru.yandex.qatools.htmlelements.loader.decorator.HtmlElementDecorator;
import ru.yandex.qatools.htmlelements.loader.decorator.HtmlElementLocatorFactory;

import java.util.List;

/**
 * @author totallynotkate (Kate Kocijevska)
 */
public class SelectStationScreen {
    public SelectStationScreen(RemoteWebDriver driver){
        PageFactory.initElements(new HtmlElementDecorator(new HtmlElementLocatorFactory(driver)), this);
    }

    @Name("Поле ввода названия станции")
    @FindBy(id = "ru.yandex.metro:id/filterTextId")
    private HtmlElement stationNameInput;

    @Name("Список станций")
    @FindBy(id="ru.yandex.metro:id/txtStationName")
    private List<HtmlElement> stationsList;

    public HtmlElement stationNameInput() {
        return stationNameInput;
    }

    public List<HtmlElement> stationsList() {
        return stationsList;
    }
}
