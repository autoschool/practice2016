package ru.qatools.school.steps.mobilesteps;

import org.openqa.selenium.WebDriver;
import ru.qatools.school.screens.MainScreen;
import ru.qatools.school.screens.SelectStationScreen;
import ru.yandex.qatools.allure.annotations.Step;
import ru.yandex.qatools.htmlelements.element.HtmlElement;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;


/**
 * @author ava1on
 */
public class MobileSteps {

    private WebDriver driver;

    public MobileSteps(WebDriver driver){
        this.driver = driver;
    }

    @Step("Тапаем по элементу {0}")
    private void tap(HtmlElement element){
        element.click();
    }

    @Step("Вводим {0} в {1}")
    private void enterText(String text, HtmlElement field){
        field.sendKeys(text);
    }

    @Step("Заполнить поле {0} значением {1}")
    public void fillStationField(HtmlElement direction, String station){
        tap(direction);
        enterText(station, onSelectionStationScreen().getSearchField());
        tap(onSelectionStationScreen().getFirst());
    }

    @Step("Время поездки должно быть больше {0}")
    public void travelTimeShouldBeGreaterThen(int limit){
        int travelTime = 0;
        String[] divide = onMainScreen().getTravelTime().getText().trim().split(" ");
        if (divide.length == 2){
            travelTime = Integer.parseInt(divide[0]);
        }
        if (divide.length == 4){
            travelTime = Integer.parseInt(divide[0]) * 60 + Integer.parseInt(divide[2]);
        }
        assertThat("Время в пути меньше заданного", travelTime, greaterThan(limit));
    }

    private MainScreen onMainScreen(){
        return new MainScreen(driver);
    }

    private SelectStationScreen onSelectionStationScreen(){
        return new SelectStationScreen(driver);
    }
}
