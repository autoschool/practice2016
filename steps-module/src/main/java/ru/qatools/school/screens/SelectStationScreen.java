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
 * Created by o.polyakov on 18.05.2016.
 */
public class SelectStationScreen {
	
    public SelectStationScreen(WebDriver driver) {
        PageFactory.initElements(new HtmlElementDecorator(new HtmlElementLocatorFactory(driver)), this);
    }

    @Name("Главный экран приложения")
    @FindBy(id = "filterTextId")
    private HtmlElement stationNameField;

    public HtmlElement getStationNameField() {
        return stationNameField;
    }
	
	@Name("Список результатов поиска станций")
    @FindBy(id = "txtStationName")
    private List<HtmlElement> stationsNameList;
	
	public List<HtmlElement> getStationsNameList() {
        return stationsNameList;
    }

}