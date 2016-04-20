package ru.qatools.school.steps.websteps;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.qatools.school.pages.MainPage;
import ru.qatools.school.pages.MainPageMethods;
import ru.qatools.school.pages.blocks.WeatherWidget;
import ru.yandex.qatools.allure.annotations.Step;
import java.util.List;
import static java.lang.String.format;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;
import static ru.yandex.qatools.htmlelements.matchers.WebElementMatchers.isDisplayed;

/**
 * Created by kurau.
 */
public class DefaultSteps {

    public static final String MAIN_PAGE = "http://weather.lanwen.ru/#?cities=%s";
    private static final String NEW_WIDGET = "What a city?";
    private static final String CELCIUM = "°C";
    private static final String KELVIN = "°K";
    private static final String FARINGEIT = "°F";
    private static final String KAIF = "°Kaif";


    private WebDriver driver;

    public DefaultSteps(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Открываем главную страницу для города «{0}»")
    public void openMainPageWithCity(String city) {
        driver.get(format(MAIN_PAGE, city));
    }

    @Step("Должны видеть на странице «{0}»")
    public void shouldSee(WebElement element) {
        assertThat("Должны видеть элемент", element, isDisplayed());
    }

    @Step("Заголовок виджета должен совпадать с запрошенным городом")
    public void shouldSeeTitleWidgetEqualCity(String city) {
        assertThat("Заголовок города равен названию виджета",
                onMainPage().getPlaces().get(0).getText(), equalTo(city));
    }

    private void addWidgetOnMainPage(String city) {
        mainPageMethods().addWidget();
        mainPageMethods().renameWidget(NEW_WIDGET, city);
    }

    @Step("На главной странице виджеты добавляются")
    public void shouldSeeWidgetAdd(String city) {
        addWidgetOnMainPage(city);
        assertThat("Widget has", city, is(mainPageMethods().hasItem(city)));
    }

    @Step("На главной странице виджет можно удалить")
    public void shouldSeeWidgetRemove() {
        int count = mainPageMethods().countWidgets();
        mainPageMethods().clickOnElement(mainPageMethods().allWidgets().get(0).getRemoveBtn());
        assertThat("Количество виджетов не уменьшилось после удаления одного виджета",
                mainPageMethods().countWidgets(), is(count - 1));
    }

    @Step("На ввод не полного названия города должно срабатывать автозаполнение")
    public void shouldAutocompliteCity(String city) {
        mainPageMethods().autoComplete(city);
        assertThat("Заголовок города равен названию набираемого города",
                mainPageMethods().findWidget(city).getText(), equalTo(city));
    }

    @Step("Должны меняться форматы вывода градуса")
    public void shouldSeeChangeFormatDegree() {
        mainPageMethods().allWidgets().get(0).getWeatherBlock().click();
        assertThat("Не произошло преобразование градусов цельсия в градусы кельвина",
                mainPageMethods().allWidgets().get(0).getWeatherType().getText(), equalTo(KELVIN));
        mainPageMethods().allWidgets().get(0).getWeatherBlock().click();
        assertThat("Не произошло преобразование градусов в кельвинах в градусы фаренгейта",
                mainPageMethods().allWidgets().get(0).getWeatherType().getText(), equalTo(FARINGEIT));
        mainPageMethods().allWidgets().get(0).getWeatherBlock().click();
        assertThat("Не произошло преобразование градусов фаренгейта в градусы °Kaif",
                mainPageMethods().allWidgets().get(0).getWeatherType().getText(), equalTo(KAIF));
    }

    @Step("Начальное открытие виджета, единица измерений температуры Цельсий")
    public void shouldSeeTemperatureCelcium() {
        assertThat("Начальное значение температуры не в Цельсиях", mainPageMethods().allWidgets().get(0).getWeatherType().getText(), equalTo(CELCIUM));
    }

    private MainPage onMainPage() {
        return new MainPage(driver);
    }

    private MainPageMethods mainPageMethods() {
        return new MainPageMethods(driver);
    }
}
