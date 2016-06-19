package ru.qatools.school.drwebscreens;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
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

    @Name("Scaner")
    @FindBy(id = "component_scaner")
    private HtmlElement scaner;

    public HtmlElement getScaner() {
        return scaner;
    }

    @Name("Antispam")
    @FindBy(id = "component_antispam")
    private HtmlElement antispam;

    public HtmlElement getAntispam() {
        return antispam;
    }

    @Name("URL filtr")
    @FindBy(id = "component_urlfiltr")
    private HtmlElement urlFilter;

    public HtmlElement getUrlFilter() {
        return urlFilter;
    }

    @Name("Antitheft")
    @FindBy(id = "component_antitheft")
    private HtmlElement antitheft;

    public HtmlElement getAntitheft() {
        return antitheft;
    }

    @Name("Firewall")
    @FindBy(id = "component_firewall")
    private HtmlElement firewall;

    public HtmlElement getFirewall() {
        return firewall;
    }

    @Name("Security Auditor")
    @FindBy(id = "component_helper")
    private HtmlElement auditor;

    public HtmlElement getAuditor() {
        return auditor;
    }


    /*@Name("���� ����� ��������� ������� - From")
    @FindBy(id = "tv_from_name")
    private HtmlElement fromStationField;

    public HtmlElement getFromStationField() {
        return fromStationField;
    }

    @Name("���� ����� �������� ������� - To")
    @FindBy(id = "tv_to_name")
    private HtmlElement toStationField;

    public HtmlElement getToStationField() {
        return toStationField;
    }

    @Name("�������������� ����� � ����")
    @FindBy(id = "tv_time")
    private HtmlElement timeField;

    public HtmlElement getTimeField() {
        return timeField;
    }*/
}
