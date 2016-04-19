package ru.qatools.school.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import ru.qatools.school.pages.blocks.WeatherWidget;
import ru.yandex.qatools.htmlelements.annotations.Name;
import ru.yandex.qatools.htmlelements.loader.decorator.HtmlElementDecorator;
import ru.yandex.qatools.htmlelements.loader.decorator.HtmlElementLocatorFactory;

import java.util.List;

/**
 * Created by kurau.
 */
public class MainPage {

    public MainPage(WebDriver driver) {
        PageFactory.initElements(new HtmlElementDecorator(new HtmlElementLocatorFactory(driver)), this);
    }

    @Name("Список виджетов")
    @FindBy(css = ".card.card_md")
    private List<WeatherWidget> weatherWidget;

    @Name("Список городов")
    @FindBy(css = ".inplace.inplace_displayed")
    private List<WeatherWidget> inplace;

    @Name("Добавление нового виджета")
    @FindBy(css = ".new-card")
    private WebElement addWidget;

    @Name("Редактируемое поле названия города")
    @FindBy(css = "input.inplace.inplace_editable")
    private WeatherWidget editInplace;

    @Name("Кнопка удаления виджета")
    @FindBy(css = ".remove-card.btn.btn-default")
    private WeatherWidget removeBtn;

    @Name("Список элементов отображения температуры")
    @FindBy(css = "weather-temperature.md-12")
    private List<WebElement> listTemperatures;

    public List<WeatherWidget> getWeatherWidget() {
        return weatherWidget;
    }

    public List<WeatherWidget> getPlaces() {
        return inplace;
    }

    public WebElement getAddWidget() {
        return addWidget;
    }

    public WeatherWidget getEditInplace() {
        return editInplace;
    }

    public WeatherWidget getRemoveBtn() {
        return removeBtn;
    }

    public List<WebElement> getListTemperatures() {
        return listTemperatures;
    }

}
