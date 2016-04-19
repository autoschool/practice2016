package ru.qatools.school.rules;

import org.junit.rules.ExternalResource;
import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 * Created by kurau.
 */
public class WebDriverRule extends ExternalResource {

    private WebDriver driver;

    //конструктор
   public WebDriverRule(WebDriver ddriver){
        driver=ddriver;
    }

    protected void before() throws Throwable {
        driver.findElement(By.cssSelector("body")).sendKeys(Keys.CONTROL+"t");
    }

    protected void after() {
        driver.findElement(By.cssSelector("body")).sendKeys(Keys.CONTROL+"w");
    }

    public WebDriver getDriver() {
        return driver;
    }

}
