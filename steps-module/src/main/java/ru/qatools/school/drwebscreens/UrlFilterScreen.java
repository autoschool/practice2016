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
public class UrlFilterScreen {

    public UrlFilterScreen(WebDriver driver) {
        PageFactory.initElements(new HtmlElementDecorator(new HtmlElementLocatorFactory(driver)), this);
    }

    @Name("Список категорий сайтов")
    @FindBy(id = "ListItemMain")
    private List<HtmlElement> urlCategoriesList;

    public List<HtmlElement> getUrlCategoriesList() {
        return urlCategoriesList;
    }

    @Name("Кнопка назад")
    @FindBy(className = "android.widget.ImageButton")
    private HtmlElement backButton;

    public HtmlElement getBackButton() {
        return backButton;
    }

    @Name("Выключатель URL-фильтра")
    @FindBy(id = "switcher")
    private HtmlElement urlSwitcher;

    public HtmlElement getUrlSwitcher() {
        return urlSwitcher;
    }

    @Name("Кнопка включения URL-фильтра")
    @FindBy(id = "button")
    private HtmlElement urlFilterEnableBatton;

    public HtmlElement getUrlFilterEnableBatton() {
        return urlFilterEnableBatton;
    }

    @Name("Список команд в поп-апе: запретить доступ и разреить доступ")
    @FindBy(id = "title")
    private List<HtmlElement> urlPermissionList;

    public HtmlElement getRestrictAccess() {
        return urlPermissionList.get(0);
    }

    public HtmlElement getAllowAccess() {
        return urlPermissionList.get(1);
    }

}
