package ru.qatools.school.screens;

import org.openqa.selenium.By;
import org.openqa.selenium.Rectangle;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.yandex.qatools.htmlelements.element.HtmlElement;

/**
 * Created by merkushevio on 19.05.2016.
 */

public class MainScreen extends HtmlElement {

    private WebDriver driver;

    public MainScreen(WebDriver driver) {
        this.driver = driver;
    }

    public WebElement getFromStation() {
        return driver.findElement(By.id("ru.yandex.metro:id/tv_from_name"));
    }

    public WebElement getToStation() {
        return driver.findElement(By.id("ru.yandex.metro:id/tv_to_name"));
    }

    public WebElement getTimeToGo() {
        return driver.findElement(By.id("ru.yandex.metro:id/tv_time"));
    }

    @Override
    public Rectangle getRect() {
        return null;
    }
}

