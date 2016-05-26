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
 * @author arrumm 19.05.2016.
 */
public class SelectStationScreen {

    public SelectStationScreen(RemoteWebDriver driver) {
        PageFactory.initElements(new HtmlElementDecorator(new HtmlElementLocatorFactory(driver)), this);
    }

    @Name("Поле ввода названия станции")
    @FindBy(id = "filterTextId")
    private HtmlElement stationNameField;

    @Name("Список результатов поиска станции для выбора")
    @FindBy(id = "txtStationName")
    private List<HtmlElement> stationSuggestResults;

    public HtmlElement stationNameField(){
        return stationNameField;
    }

    public List<HtmlElement> stationSuggestResults(){
        return stationSuggestResults;
    }

    public HtmlElement getFirstStation() {
        return stationSuggestResults.get(0);
    }
}
