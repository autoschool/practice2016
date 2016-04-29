package ru.qatools.school.steps.websteps;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.qatools.school.pages.MainPage;
import ru.qatools.school.pages.blocks.WeatherWidget;

import java.util.List;

public class MainPageMethods {

    private MainPage mainPage;

    public MainPageMethods(WebDriver driver) {
        mainPage = new MainPage(driver);
    }


    public MainPage getMainPage() {
        return mainPage;
    }
}
