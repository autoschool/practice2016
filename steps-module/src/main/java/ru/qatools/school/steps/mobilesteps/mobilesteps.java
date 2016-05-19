package ru.qatools.school.steps.mobilesteps;

import org.openqa.selenium.remote.RemoteWebDriver;
import ru.qatools.school.screens.MainScreen;
import ru.qatools.school.screens.SelectStationScreen;
import ru.yandex.qatools.allure.annotations.Step;
import ru.yandex.qatools.htmlelements.element.HtmlElement;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.hamcrest.Matchers.greaterThan;
import static org.junit.Assert.assertThat;

/**
 * @author arrumm 19.05.2016.
 */
public class MobileSteps {

    private RemoteWebDriver driver;

    public MobileSteps(RemoteWebDriver driver) {
        this.driver = driver;
    }

    @Step("Открываем главный экран")
    public void openMainScreen() {
        driver.getCurrentUrl();
    }

    @Step("Открываем экран с выбором станций")
    public void openStationScreen() {
        driver.getCurrentUrl();
    }

    private MainScreen onMainScreen() {
        return new MainScreen(driver);
    }

    private SelectStationScreen onSelectStationScreen() {
        return new SelectStationScreen(driver);
    }

    @Step("Тапаем элемент {0}")
    public void tapOn(HtmlElement element) {
        element.click();
    }

    @Step("Вводим текст {1} в элемент {0}")
    public void sendKeysTo(HtmlElement element, String keys) {
        element.sendKeys(keys);
    }

    @Step()
    public void shouldSeeGreaterThan(HtmlElement element, int time){

        int hours=0, minutes=0;
        String strTime = element.getText();
        String regex_h = "h";
        String regex_min = "min";

        Pattern p = Pattern.compile(regex_h);
        Matcher m = p.matcher(strTime);
        if (m.find()) {

            hours = Integer.parseInt(strTime.substring(0, m.start()-1).replaceAll( "[^\\d]", "" ));
            strTime = strTime.substring(m.end());
        }

        p = Pattern.compile(regex_min);
        m = p.matcher(strTime);
        if (m.find()) {
            minutes = Integer.parseInt(strTime.substring(0, m.start()-1).replaceAll( "[^\\d]", "" ));
        }

        assertThat("Value should be greater",
                hours*60+minutes,
                greaterThan(time));

    }


}
