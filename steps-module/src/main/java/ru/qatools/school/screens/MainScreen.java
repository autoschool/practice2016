package ru.qatools.school.screens;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import ru.yandex.qatools.htmlelements.annotations.Name;
import ru.yandex.qatools.htmlelements.element.HtmlElement;
import ru.yandex.qatools.htmlelements.loader.decorator.HtmlElementDecorator;
import ru.yandex.qatools.htmlelements.loader.decorator.HtmlElementLocatorFactory;

/**
 * @author totallynotkate (Kate Kocijevska)
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

    @Name("Выезжающая панель")
    @FindBy(id="ru.yandex.metro:id/topPanel")
    private TopPanel topPanel;

    public HtmlElement fromField(){
        return fromField;
    }

    public HtmlElement toField() {
        return toField;
    }

    public TopPanel topPanel() {
        return topPanel;
    }
}
