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
 * Changed by onegines (Eugene Kirienko).
 */
public class MainPage {

    @Name("Тело страницы")
    @FindBy(tagName = "body")
    private HtmlElement body;

    @Name("Список виджетов")
    @FindBy(css = ".card.card_md")
    private List<WeatherWidget> weatherWidgets;

    @Name("Кнопка добавления виджета")
    @FindBy(css = ".new-card")
    private HtmlElement addWidgetButton;

    public MainPage(WebDriver driver) {
        PageFactory.initElements(new HtmlElementDecorator(new HtmlElementLocatorFactory(driver)), this);
    }

    public List<WeatherWidget> getWeatherWidgets() {
        return weatherWidgets;
    }

    public WeatherWidget getFirstWidget() {
        return getWeatherWidgets().get(0);
    }

    public HtmlElement getAddWidgetButton() {
        return addWidgetButton;
    }

    public HtmlElement getBody() {
        return body;
    }
}
