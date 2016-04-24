package ru.qatools.school.rules;

import org.junit.rules.ExternalResource;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 * Created by kurau.
 */
public class WebDriverRule extends ExternalResource {

    private WebDriver driver;

    protected void before() throws Throwable {
        this.driver = new FirefoxDriver();
    }

    protected void after() {
        driver.close();
        driver.quit();
    }

    public WebDriver getDriver() {
        return driver;
    }

}
