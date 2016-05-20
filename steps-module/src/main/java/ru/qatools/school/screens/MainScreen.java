package ru.qatools.school.screens;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import ru.yandex.qatools.htmlelements.annotations.Name;
import ru.yandex.qatools.htmlelements.element.HtmlElement;
import ru.yandex.qatools.htmlelements.loader.decorator.HtmlElementDecorator;
import ru.yandex.qatools.htmlelements.loader.decorator.HtmlElementLocatorFactory;

/**
 * Created by aasx on 18.05.2016.
 */
public class MainScreen {
    @Name("Station FROM")
    @FindBy(id = "ru.yandex.metro:id/tv_from_name")
    private HtmlElement StationFrom;


    @Name("Station TO")
    @FindBy(id = "ru.yandex.metro:id/tv_to_name")
    private HtmlElement StationTo;

    @Name("Time in way")
    @FindBy(id = "ru.yandex.metro:id/tv_time")
    private HtmlElement timeNeeded;

    public MainScreen(WebDriver driver) {
        PageFactory.initElements(new HtmlElementDecorator(new HtmlElementLocatorFactory(driver)), this);
    }

    public HtmlElement getTimeNeeded() {
        return timeNeeded;
    }

    public int getTimeNeededInMinutes() {
        int time = Integer.parseInt(timeNeeded.getText().substring(1, 3));
        return time;
    }

    public HtmlElement getStationFrom() {
        return StationFrom;
    }

    public HtmlElement getStationTo() {
        return StationTo;
    }


}
