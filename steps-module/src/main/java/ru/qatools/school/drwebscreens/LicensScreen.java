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
public class LicensScreen {

    public LicensScreen(WebDriver driver) {
        PageFactory.initElements(new HtmlElementDecorator(new HtmlElementLocatorFactory(driver)), this);
    }

    @Name("Send statistic Checkbox")
    @FindBy(id = "statCheckbox")
    private HtmlElement statisticCheckbox;

    public HtmlElement getStatisticCheckbox() {
        return statisticCheckbox;
    }

    @Name("Licens accept button")
    @FindBy(id = "button1")
    private HtmlElement licensAcceptButton;

    public HtmlElement getLicensAcceptButton() {
        return licensAcceptButton;
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
