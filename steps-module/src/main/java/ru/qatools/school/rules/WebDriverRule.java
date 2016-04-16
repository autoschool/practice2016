package ru.qatools.school.rules;

import org.junit.rules.ExternalResource;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;

import java.io.File;

/**
 * Created by kurau.
 * added by arrumm (Arkhipov Roman)
 */
public class WebDriverRule extends ExternalResource {

    private WebDriver driver;

    protected void before() throws Throwable {

        System.setProperty("webdriver.firefox.bin", "D:\\my Programs\\firefox\\firefox.exe");
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
