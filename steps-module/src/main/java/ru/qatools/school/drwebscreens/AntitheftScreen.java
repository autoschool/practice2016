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
public class AntitheftScreen {

    public AntitheftScreen(WebDriver driver) {
        PageFactory.initElements(new HtmlElementDecorator(new HtmlElementLocatorFactory(driver)), this);
    }

    @Name("Выключатель Антивора")
    @FindBy(id = "switcher")
    private HtmlElement antitheftSwitcher;

    public HtmlElement getAntitheftSwitcher() {
        return antitheftSwitcher;
    }

    @Name("Кнопка назад")
    @FindBy(className = "android.widget.ImageButton")
    private HtmlElement backButton;

    public HtmlElement getBackButton() {
        return backButton;
    }

    @Name("Кнопка включения Антивора")
    @FindBy(id = "button")
    private HtmlElement antitheftEnableBatton;

    public HtmlElement getAntitheftEnableBatton() {
        return antitheftEnableBatton;
    }

    @Name("Крестик в окнах конструктора Антивора")
    @FindBy(className = "android.widget.ImageButton")
    private HtmlElement crossButton;

    public HtmlElement getCrossButton() {
        return crossButton;
    }

    @Name("Кнопка Назад в окнах конструктора Антивора")
    @FindBy(id = "button_back")
    private HtmlElement antitheftWizardBattonBack;

    public HtmlElement getAntitheftWizardBattonBack() {
        return antitheftWizardBattonBack;
    }

    @Name("Кнопка Вперед в окнах конструктора Антивора")
    @FindBy(id = "button_next")
    private HtmlElement antitheftWizardBattonContinue;

    public HtmlElement getAntitheftWizardBattonContinue() {
        return antitheftWizardBattonContinue;
    }

    @Name("Поле ввода пароля в окнах конструктора Антивора")
    @FindBy(id = "PasswordEdit")
    private HtmlElement passwordEditField;

    public HtmlElement getPasswordEditField() {
        return passwordEditField;
    }

    @Name("Кнопка Посмотреть пароль в окнах конструктора Антивора")
    @FindBy(id = "showPasswordButton")
    private HtmlElement showPasswordButton;

    public HtmlElement getShowPasswordButton() {
        return showPasswordButton;
    }

    @Name("Кнопка Добавить контакт в окнах конструктора Антивора")
    @FindBy(id = "fragment_list_add_button")
    private HtmlElement addContactButton;

    public HtmlElement getAddContactButton() {
        return addContactButton;
    }

    @Name("Список категорий откуда добавить контакт друга")
    @FindBy(className = "android.widget.RelativeLayout")
    private List<HtmlElement> addContactCategoriesList;

    public List<HtmlElement> getAddContactCategoriesList() {
        return addContactCategoriesList;
    }

    public HtmlElement newContactCategory() {
        return addContactCategoriesList.get(3);
    }

    @Name("Поле ввода номера телефона в окне добавления нового контакта друга")
    @FindBy(id = "PhoneNumberInfoEditorNumber")
    private HtmlElement phoneNumberEditorField;

    public HtmlElement getPhoneNumberEditorField() {
        return phoneNumberEditorField;
    }

    @Name("Кнопка Сохранить")
    @FindBy(id = "save")
    private HtmlElement saveBatton;

    public HtmlElement getSaveBatton() {
        return saveBatton;
    }

    @Name("Поле ввода адреса электронной почты")
    @FindBy(id = "emailEdit")
    private HtmlElement emailEditField;

    public HtmlElement getEmailEditField() {
        return emailEditField;
    }

    @Name("Поле ввода текста для экрана блокировки")
    @FindBy(id = "blockText")
    private HtmlElement blockTextField;

    public HtmlElement getBlockTextField() {
        return blockTextField;
    }

    @Name("Кнопка Отменить")
    @FindBy(id = "cancel_button")
    private HtmlElement cancelBatton;

    public HtmlElement getCancelBatton() {
        return cancelBatton;
    }

    /*@Name("Список категорий сайтов")
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
    @FindBy(id = "switcher")
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
    */

}
