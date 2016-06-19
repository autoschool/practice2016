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
public class FirewallScreen {

    public FirewallScreen(WebDriver driver) {
        PageFactory.initElements(new HtmlElementDecorator(new HtmlElementLocatorFactory(driver)), this);
    }

    @Name("Выключатель Брандмауэра")
    @FindBy(id = "switcher")
    private HtmlElement firewallSwitcher;

    public HtmlElement getFirewallSwitcher() {
        return firewallSwitcher;
    }

    @Name("Кнопка назад")
    @FindBy(className = "android.widget.ImageButton")
    private HtmlElement backButton;

    public HtmlElement getBackButton() {
        return backButton;
    }

    @Name("Кнопка включения Брандмауэра")
    @FindBy(id = "button")
    private HtmlElement firewallEnableBatton;

    public HtmlElement getFirewallEnableBatton() {
        return firewallEnableBatton;
    }

    @Name("Чекбокс доверия приложению (VPN)")
    @FindBy(id = "com.android.vpndialogs:id/check")
    private HtmlElement vpnTrustCheckbox;

    public HtmlElement getVPNTrustCheckbox() {
        return  vpnTrustCheckbox;
    }

    @Name("Кнопка OK (VPN)")
    @FindBy(id = "android:id/button1")
    private HtmlElement vpnOkButton;

    public HtmlElement getVPNOkButton() {
        return vpnOkButton;
    }

    @Name("Кнопка Отмена (VPN)")
    @FindBy(id = "android:id/button2")
    private HtmlElement vpnCancelButton;

    public HtmlElement getVPNCancelButton() {
        return vpnCancelButton;
    }

    @Name("Список категорий сайтов")
    @FindBy(className = "android.support.v7.app.ActionBar$Tab")
    private List<HtmlElement> firewallTabList;

    public List<HtmlElement> getFirewallTabList() {
        return firewallTabList;
    }

}
