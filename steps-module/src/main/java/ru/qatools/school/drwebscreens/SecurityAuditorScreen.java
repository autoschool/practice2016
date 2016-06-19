package ru.qatools.school.drwebscreens;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import ru.yandex.qatools.htmlelements.annotations.Name;
import ru.yandex.qatools.htmlelements.element.HtmlElement;
import ru.yandex.qatools.htmlelements.loader.decorator.HtmlElementDecorator;
import ru.yandex.qatools.htmlelements.loader.decorator.HtmlElementLocatorFactory;

import java.util.List;

/**
 * Created by o.polyakov on 18.05.2016.
 */
public class SecurityAuditorScreen {

    public SecurityAuditorScreen(WebDriver driver) {
        PageFactory.initElements(new HtmlElementDecorator(new HtmlElementLocatorFactory(driver)), this);
    }

    @Name("Список типов проверки")
    @FindBy(className = "android.widget.LinearLayout")
    private List<HtmlElement> problemsCategoriesList;

    public List<HtmlElement> getProblemsCategoriesList() {
        return problemsCategoriesList;
    }

    @Name("Кнопка назад")
    @FindBy(className = "android.widget.ImageButton")
    private HtmlElement backButton;

    public HtmlElement getBackButton() {
        return backButton;
    }

}
