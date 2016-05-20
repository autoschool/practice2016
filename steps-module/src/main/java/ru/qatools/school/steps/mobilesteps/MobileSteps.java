package ru.qatools.school.steps.mobilesteps;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.qatools.school.screens.MainScreen;
import ru.qatools.school.screens.SelectStationScreen;
import ru.yandex.qatools.allure.annotations.Step;
import ru.yandex.qatools.htmlelements.element.HtmlElement;

/**
 * Created by aasx on 18.05.2016.
 */
public class MobileSteps {
    private WebDriver driver;

    public MobileSteps(WebDriver driver) {
        this.driver = driver;
    }


    @Step("Кликаем по элементу {0} ")
    public void clickOn(HtmlElement element) {
        element.click();
    }

    @Step("Вводим текст «{1}» в элемент «{0}»")
    public void enterText(HtmlElement element, String text) {
        element.sendKeys(text);
    }

    private MainScreen onMainScreen() {return new MainScreen(driver);}
    private SelectStationScreen onSelectStationScreen(){return new SelectStationScreen(driver);}


}
