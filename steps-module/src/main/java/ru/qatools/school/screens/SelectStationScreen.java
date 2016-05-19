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
 * Created by aasx on 18.05.2016.
 */
public class SelectStationScreen {
    @Name("Enter city name text field")
    @FindBy(id = "ru.yandex.metro:id/filterTextId")
    private HtmlElement editText;

    @Name("Suggest List")
    @FindBy(id = "ru.yandex.metro:id/txtStationName")
    private List<HtmlElement> suggestList;

    public SelectStationScreen(WebDriver driver) {
        PageFactory.initElements(new HtmlElementDecorator(new HtmlElementLocatorFactory(driver)), this);
    }

    public HtmlElement getFirstCityFromSuggestList() {
        return suggestList.get(0);
    }

    public HtmlElement getEditText() {
        return editText;
    }

}
