package ru.qatools.school.screens;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import ru.yandex.qatools.htmlelements.annotations.Name;
import ru.yandex.qatools.htmlelements.element.HtmlElement;
import ru.yandex.qatools.htmlelements.loader.decorator.HtmlElementDecorator;
import ru.yandex.qatools.htmlelements.loader.decorator.HtmlElementLocatorFactory;

/**
 * @author raipc (Anton Rybochkin)
 */

public class MainScreen {
    public MainScreen(RemoteWebDriver driver){
        PageFactory.initElements(new HtmlElementDecorator(new HtmlElementLocatorFactory(driver)), this);
    }

    @Name("Поле From")
    @FindBy(id = "ru.yandex.metro:id/tv_from_name")
    private HtmlElement fromField;

    @Name("Поле To")
    @FindBy(id = "ru.yandex.metro:id/tv_to_name")
    private HtmlElement toField;

    @Name("Верхняя панель")
    @FindBy(id="ru.yandex.metro:id/getTopPanel")
    private TopPanel topPanel;

    public HtmlElement getFromField(){
        return fromField;
    }

    public HtmlElement getToField() {
        return toField;
    }

    public TopPanel getTopPanel() {
        return topPanel;
    }
}
