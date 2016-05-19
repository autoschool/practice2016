package ru.qatools.school.rules;

import org.junit.rules.ExternalResource;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.URL;

/**
 * Created by aasx on 18.05.2016.
 */
public class MobileDriverRule extends ExternalResource {
    private WebDriver driver;
    private DesiredCapabilities desiredCapabilities;

    protected void before() throws Throwable {
        desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability("platformName", "Android");
        desiredCapabilities.setCapability("deviceName", "Android");
        desiredCapabilities.setCapability("app", "http://autoschool.github.io/files/ya-metro.apk");
        desiredCapabilities.setCapability("appWaitActivity", "ru.yandex.metro.MainActivity");
        desiredCapabilities.setCapability("unicodeKeyboard", "true");
        this.driver = new RemoteWebDriver(new URL("http://127.0.0.1:4723/wd/hub"), desiredCapabilities);
    }

    public WebDriver getDriver() {
        return driver;
    }

    protected void after() {
        desiredCapabilities.setCapability("resetKeyboard", "true");
        driver.quit();
    }
}
