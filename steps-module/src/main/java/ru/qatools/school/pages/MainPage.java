package ru.qatools.school.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import ru.qatools.school.pages.blocks.WeatherWidget;
import ru.qatools.school.pages.blocks.NewWeatherWidget;
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

    @Name("Widgets list")
    @FindBy(css = ".card.card_md")
    private List<WeatherWidget> weatherWidgets;

    @Name("New weather widget")
    @FindBy(css = ".new-card")
    private List<NewWeatherWidget> newWeatherWidget;

    public List<NewWeatherWidget> getNewWeatherWidget() {
        return newWeatherWidget;
    }

    public List<WeatherWidget> getWeatherWidgets() {
        return weatherWidgets;
    }

}
