package ru.qatools.school.rules;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.URL;

/**
 * @author ava1on
 */
public class MobileDriverRule {
    private WebDriver driver;

    protected void before() throws Throwable{
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability("platformName", "Android");
        desiredCapabilities.setCapability("deviceName", "Android");
        desiredCapabilities.setCapability("app", "http://autoschool.github.io/files/ya-metro.apk");
        desiredCapabilities.setCapability("appWaitActivity", "ru.yandex.metro.MainActivity");
        driver = new RemoteWebDriver(new URL("http://127.0.0.1:4723/wd/hub"), desiredCapabilities);
    }

    private void after(){
        driver.close();
        driver.quit();
    }

    public WebDriver getDriver() {
        return driver;
    }
}
