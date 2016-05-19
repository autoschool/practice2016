package ru.qatools.school.screens;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import ru.yandex.qatools.htmlelements.annotations.Name;
import ru.yandex.qatools.htmlelements.loader.decorator.HtmlElementDecorator;
import ru.yandex.qatools.htmlelements.loader.decorator.HtmlElementLocatorFactory;

import java.util.List;

/**
 * @author onegines (Eugene Kirienko)
 */
public class SelectStationScreen {

    @Name("Поле ввода названия станции")
    @FindBy(id = "filterTextId")
    private WebElement stationNameField;

    @Name("Поля результата поиска названия станции")
    @FindBy(id = "txtStationName")
    private List<WebElement> stationNameResults;

    public SelectStationScreen(WebDriver driver) {
        PageFactory.initElements(new HtmlElementDecorator(new HtmlElementLocatorFactory(driver)), this);
    }

    public WebElement stationNameField() {
        return stationNameField;
    }

    public WebElement firstResult() {
        return stationNameResults.get(0);
    }
}
