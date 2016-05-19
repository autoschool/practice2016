package ru.qatools.school.screens;

import org.openqa.selenium.By;
import org.openqa.selenium.Rectangle;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.yandex.qatools.htmlelements.element.HtmlElement;

/**
 * Created by merkushevio on 19.05.2016.
 */
public class SelectStationScreen extends HtmlElement{

    private WebDriver driver;

    public SelectStationScreen(WebDriver driver) {
        this.driver = driver;
    }

    public WebElement getNameStation() {
        return driver.findElement(By.id("ru.yandex.metro:id/filterTextId"));
    }

    public WebElement getFirstFoundStation() {
        return driver.findElement(By.id("ru.yandex.metro:id/txtStationName"));
    }

    @Override
    public Rectangle getRect() {
        return null;
    }
}
