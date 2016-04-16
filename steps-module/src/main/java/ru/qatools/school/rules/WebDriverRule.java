package ru.qatools.school.rules;

import org.junit.rules.ExternalResource;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;

import java.io.File;

/**
 * Created by kurau.
 */
public class WebDriverRule extends ExternalResource {

    private WebDriver driver;

    protected void before() throws Throwable {

        //привет, костыли
        File pathBinary = new File("D:\\my Programs\\firefox\\firefox.exe");
        FirefoxBinary Binary = new FirefoxBinary(pathBinary);
        FirefoxProfile firefoxPro = new FirefoxProfile();
        this.driver = new FirefoxDriver(Binary,firefoxPro);
        //this.driver = new FirefoxDriver();
    }

    protected void after() {
        driver.close();
        driver.quit();
    }

    public WebDriver getDriver() {
        return driver;
    }

}
