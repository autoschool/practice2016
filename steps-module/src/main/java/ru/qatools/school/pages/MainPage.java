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
 * Created by kurau.
 */
public class MainPage {

    public MainPage(WebDriver driver) {
        PageFactory.initElements(new HtmlElementDecorator(new HtmlElementLocatorFactory(driver)), this);
    }

    @Name("Список виджетов")
    @FindBy(css = ".card.card_md")
    private List<WeatherWidget> weatherWidgetList;

    @Name("Первый виджет в списке виджетов")
    @FindBy(css = ".card.card_md")
    private WeatherWidget firstWidget;

    @Name("Кнопка добавления виджета")
    @FindBy(css = ".new-card")
    private HtmlElement newWidgetButton;

    public List<WeatherWidget> getWeatherWidgetList() {
        return weatherWidgetList;
    }

    public HtmlElement getNewWidgetButton() {
        return newWidgetButton;
    }

    public WeatherWidget getFirstWidget(){
        return firstWidget;
    }
}
