package ru.qatools.school.rules;

import org.junit.rules.ExternalResource;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * @author arrumm 19.05.2016.
 */
public class MobileDriverRule extends ExternalResource {

    private RemoteWebDriver driver;

    protected void before() throws MalformedURLException {

        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability("platformName", "Android");
        desiredCapabilities.setCapability("deviceName", "Android");
        desiredCapabilities.setCapability("app", "http://autoschool.github.io/files/ya-metro.apk");
        desiredCapabilities.setCapability("appWaitActivity", "ru.yandex.metro.MainActivity");

        this.driver = new RemoteWebDriver(new URL("http://127.0.0.1:4723/wd/hub"), desiredCapabilities);
    }

    protected void after() {
        driver.quit();
    }

    public RemoteWebDriver getDriver() {
        return driver;
    }

}
