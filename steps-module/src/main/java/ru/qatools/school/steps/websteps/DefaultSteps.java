package ru.qatools.school.steps.websteps;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.qatools.school.pages.MainPage;
import ru.qatools.school.pages.blocks.TemperatureBlock;
import ru.qatools.school.pages.blocks.widgetblocks.WidgetTitle;
import ru.yandex.qatools.allure.annotations.Step;
import static java.lang.String.format;
import static org.junit.Assert.assertThat;
import static ru.yandex.qatools.htmlelements.matchers.WebElementMatchers.hasText;
import static ru.yandex.qatools.htmlelements.matchers.WebElementMatchers.isDisplayed;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNot.not;

/**
 * Created by kurau.
 */
public class DefaultSteps {

    public static final String MAIN_PAGE = "http://weather.lanwen.ru/#?cities=%s";

    private WebDriver driver;

    private class TempState{
        public String temperature, temperatureFormat;
        public int n;
    }

    private TempState tempState = new TempState();


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

    @Step("Заголовок должен быть «{1}»")
    public void cityIntitleShouldBe(WidgetTitle widgetTitle, String title) {
        assertThat("Город должен быть",widgetTitle.getTitleCityElement(), hasText(title));
    }

    @Step("Количество виджетов должно быть «{0}»")
    public void widgetsAmountShouldBe(int n) {
        assertThat("Количество виджетов должно быть",onMainPage().getWeatherWidget().size(), is(n));
    }

    @Step("Жмем кнопку добавить виджет «{0}» раз")
    public void clickAddWidgetButtonNTimes(int n) {
        while(n-- > 0){
            onMainPage().getNewWidgetButton().click();
        }
    }

    @Step("Жмем кнопку удалить виджет для виджета № «{0}»")
    public void clickRemoveWidgetButton(int n) {
            onMainPage().getWeatherWidget().get(n).getRemoveButton().click();
    }

    @Step("Жмем на блок температуры")
    public void clickTemperatureBlock(int n) {
        onMainPage().getWeatherWidget().get(n).getTempBlock().click();
    }

    @Step("Запоминаем состояние температуры")
    public void saveTemperatureState(int n) {
        TemperatureBlock tb = onMainPage().getWeatherWidget().get(n).getTempBlock();
        tempState.temperature = tb.getTemperatureValueBlock().getText();
        tempState.temperatureFormat = tb.getTemperatureFormatBlock().getText();
        tempState.n = n;
    }

    @Step("Проверяем изменилась ли температура")
    public void checkThatTemperatureStateWasChanged() {
        TemperatureBlock tb = onMainPage().getWeatherWidget().get(tempState.n).getTempBlock();
        assertThat("Значение температуры должно не быть"
                ,tb.getTemperatureValueBlock().getText(),is(not(tempState.temperature)));
        assertThat("Формат температуры должен не быть"
                ,tb.getTemperatureFormatBlock().getText(),is(not(tempState.temperatureFormat)));
    }

    @Step("Удаляем содержимое поля Город в заголовке")
    public void emptyCityFieldInTitle(int n) {
        onMainPage().getWeatherWidget().get(n).getWidgetTitle().sendKeys("HELLOO!!!");

    }

    @Step("Кликаем по элементу, что бы получить фокус на нем")
    public void clickCityField(int n){
        onMainPage().getWeatherWidget().get(n).getWidgetTitle().click();
    }

    @Step("Проверяем что рамзеры поля города не равны 0px")
    public void cityTitleFieldShouldNotDisappear(int n) {
        assertThat("Высота поля не должна быть нулевой",
                onMainPage().getWeatherWidget().get(n).getWidgetTitle().getSize().getHeight(),
                is(not(0))
        );
        assertThat("Ширина поля не должна быть нулевой",
                onMainPage().getWeatherWidget().get(n).getWidgetTitle().getSize().getWidth(),
                is(not(0))
        );
    }

    private MainPage onMainPage() {
        return new MainPage(driver);
    }
}




