package ru.qatools.school.screens;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import ru.qatools.school.pages.blocks.WeatherWidget;
import ru.yandex.qatools.htmlelements.annotations.Name;
import ru.yandex.qatools.htmlelements.element.HtmlElement;
import ru.yandex.qatools.htmlelements.loader.decorator.HtmlElementDecorator;
import ru.yandex.qatools.htmlelements.loader.decorator.HtmlElementLocatorFactory;

/**
 * Created by o.polyakov on 18.05.2016.
 */
public class MainScreen {
    public MainScreen(WebDriver driver) {
        PageFactory.initElements(new HtmlElementDecorator(new HtmlElementLocatorFactory(driver)), this);
    }

    @Name("Главный экран приложения")
    @FindBy(id = "tv_from_name")
    private HtmlElement fromStation;

    public HtmlElement getFromStation() {
        return fromStation;
    }

    @Name("Главный экран приложения")
    @FindBy(id = "tv_to_name")
    private HtmlElement toStation;

    public HtmlElement getToStation() {
        return fromStation;
    }
}
