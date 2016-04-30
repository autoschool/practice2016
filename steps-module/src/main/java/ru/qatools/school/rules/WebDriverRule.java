package ru.qatools.school.rules;

import org.junit.rules.ExternalResource;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.concurrent.TimeUnit;

/**
 * Created by kurau.
 */
public class WebDriverRule extends ExternalResource {

    private WebDriver driver;

    protected void before() throws Throwable {
        this.driver = new FirefoxDriver();
        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
    }

    protected void after() {
        driver.close();
        driver.quit();
    }

    public WebDriver getDriver() {
        return driver;
    }

}
