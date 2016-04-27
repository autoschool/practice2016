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
    private List<WebElement> places;

    @Name("Добавление нового виджета")
    @FindBy(css = ".new-card")
    private WebElement addWidget;

    @Name("Список элементов отображения температуры")
    @FindBy(css = "weather-temperature.md-12")
    private List<WebElement> listTemperatures;

    @Name("Редактируемое поле названия города")
    @FindBy(css = "input.inplace.inplace_editable")
    private WebElement editPlace;

    @Name("Выпадающий список значений автодополнений")
    @FindBy(css = "span.city__name")
    private List<WebElement> autoCompleteValues;

    @Name("Заголовки показателей")
    @FindBy(css = "span.info-line__title")
    private List<WebElement> titleValues;

    @Name("Данные показателей погоды")
    @FindBy(css = "span.info-line__value")
    private List<WebElement> infoValues;

    @Name("Строки отображения времени")
    @FindBy(css = ".card-title__secondary>div>div")
    private List<WebElement> timeOnWidget;

    @Name("Изображение рассвета")
    @FindBy(css = ".wi.wi-sunrise")
    private WebElement sunrise;

    @Name("Изображение заката")
    @FindBy(css = ".wi.wi-sunset")
    private WebElement sunset;

    @Name("Изображение скорости ветра")
    @FindBy(css = ".wi.wi-strong-wind")
    private WebElement wind;

    @Name("Изображение влажности")
    @FindBy(css = ".wi.wi-raindrops")
    private WebElement humidity;

    //Поиск элемента по маске
    @Name("Основное изображение на виджете")
    @FindBy(css = ".wi.weather-image")
    private WebElement mainImage;

    public List<WeatherWidget> getWeatherWidgets() {
        return weatherWidget;
    }

    public List<WebElement> getPlaces() {
        return places;
    }

    public WebElement getEditPlace() {
        return editPlace;
    }

    public WebElement getAddWidget() {
        return addWidget;
    }

    public List<WebElement> getAutoCompleteValues() {
        return autoCompleteValues;
    }

    public List<WebElement> getTitleValues() {
        return titleValues;
    }

    public List<WebElement> getTimeOnWidget() {
        return timeOnWidget;
    }

    public List<WebElement> getInfoValues() {
        return infoValues;
    }

    public List<WebElement> getListTemperatures() {
        return listTemperatures;
    }

    public WebElement getSunrise() {
        return sunrise;
    }

    public WebElement getSunset() {
        return sunset;
    }

    public WebElement getWind() {
        return wind;
    }

    public WebElement getHumidity() {
        return humidity;
    }

    public WebElement getMainImage() {
        return mainImage;
    }

}
