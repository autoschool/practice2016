package ru.qatools.school.screens;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import ru.yandex.qatools.htmlelements.annotations.Name;
import ru.yandex.qatools.htmlelements.element.HtmlElement;
import ru.yandex.qatools.htmlelements.loader.decorator.HtmlElementDecorator;
import ru.yandex.qatools.htmlelements.loader.decorator.HtmlElementLocatorFactory;

import java.util.List;

/**
 * @author gladnik (Nikolai Gladkov)
 */
public class SelectStationScreen {

    @Name("Поле ввода для поиска станции")
    @FindBy(id = "ru.yandex.metro:id/filterTextId")
    private HtmlElement filterTextId;

    @Name("Названия найденных станций")
    @FindBy(id = "ru.yandex.metro:id/txtStationName")
    private List<HtmlElement> foundStations;

    @Name("Название первой из найденных станций")
    private HtmlElement firstStation;


    public SelectStationScreen(WebDriver driver) {
        PageFactory.initElements(new HtmlElementDecorator(new HtmlElementLocatorFactory(driver)), this);
    }

    public HtmlElement filterTextId() {
        return filterTextId;
    }

    public List<HtmlElement> foundStations() {
        return foundStations;
    }

    public HtmlElement firstStation() {
        firstStation = foundStations.get(0);
        return firstStation;
    }
}
