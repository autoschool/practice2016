package ru.qatools.school.rules;

import org.junit.rules.ExternalResource;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Created by kurau.
 */
public class WebDriverRule extends ExternalResource {

    private WebDriver driver;
    private WebDriverWait webDriverWait;

    protected void before() throws Throwable {
        this.driver = new FirefoxDriver();
        this.webDriverWait = new WebDriverWait(driver, 5);
    }

    protected void after() {
        driver.close();
        driver.quit();
    }

    public WebDriver getDriver() {
        return driver;
    }

}
