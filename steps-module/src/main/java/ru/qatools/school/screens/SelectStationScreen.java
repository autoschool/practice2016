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
 * @author ava1on
 */
public class SelectStationScreen {

    public SelectStationScreen (WebDriver driver){
        PageFactory.initElements(new HtmlElementDecorator(new HtmlElementLocatorFactory(driver)), this);
    }

    @Name("Поле ввода для поиска")
    @FindBy(id = "ru.yandex.metro:id/filterTextId")
    private HtmlElement searchField;

    @Name("Список подходящих станций")
    @FindBy(id = "ru.yandex.metro:id/txtStationName")
    private List<HtmlElement> stations;

    public HtmlElement getSearchField() {
        return searchField;
    }

    public List<HtmlElement> getStations() {
        return stations;
    }

    public HtmlElement getFirst(){
        return getStations().get(0);
    }
}
