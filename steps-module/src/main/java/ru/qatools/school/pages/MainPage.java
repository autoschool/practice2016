package ru.qatools.school.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import ru.qatools.school.pages.blocks.WeatherWidget;
import ru.yandex.qatools.htmlelements.annotations.Name;
import ru.yandex.qatools.htmlelements.element.HtmlElement;
import ru.yandex.qatools.htmlelements.loader.decorator.HtmlElementDecorator;
import ru.yandex.qatools.htmlelements.loader.decorator.HtmlElementLocatorFactory;

import java.util.List;

/**
 * @author kurau
 * @author gladnik (Nikolai Gladkov)
 */
public class MainPage {

    @Name("Список виджетов")
    private List<WeatherWidget> weatherWidgets;

    @Name("Первый виджет")
    private WeatherWidget firstWeatherWidget;

    @Name("Кнопка добавления виджета")
    @FindBy(css = ".new-card")
    private HtmlElement addWidgetButton;


    public MainPage(WebDriver driver) {
        PageFactory.initElements(new HtmlElementDecorator(new HtmlElementLocatorFactory(driver)), this);
    }

    public List<WeatherWidget> weatherWidgets() {
        return weatherWidgets;
    }

    public WeatherWidget firstWidget() {
        firstWeatherWidget = weatherWidgets.get(0);
        return firstWeatherWidget;
    }

    public HtmlElement addWidgetButton() {
        return addWidgetButton;
    }
}
