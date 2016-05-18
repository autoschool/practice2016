package ru.qatools.school.screens;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import ru.yandex.qatools.htmlelements.annotations.Name;
import ru.yandex.qatools.htmlelements.loader.decorator.HtmlElementDecorator;
import ru.yandex.qatools.htmlelements.loader.decorator.HtmlElementLocatorFactory;

/**
 * @author onegines (Eugene Kirienko)
 */
public class MainScreen {

    @Name("Поле ввода начальной станции")
    @FindBy(id = "layout_from")
    private WebElement fromField;

    @Name("Поле ввода конечной станции")
    @FindBy(id = "layout_to")
    private WebElement toField;

    @Name("Время в пути")
    @FindBy(id = "tv_time")
    private WebElement timeField;

    public MainScreen(WebDriver driver) {
        PageFactory.initElements(new HtmlElementDecorator(new HtmlElementLocatorFactory(driver)), this);
    }

    public WebElement fromField() {
        return fromField;
    }

    public WebElement toField() {
        return toField;
    }

    public WebElement timeField() {
        return timeField;
    }
}
