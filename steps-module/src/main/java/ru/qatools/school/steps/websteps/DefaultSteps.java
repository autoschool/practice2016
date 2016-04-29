package ru.qatools.school.steps.websteps;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.qatools.school.pages.MainPage;
import ru.yandex.qatools.allure.annotations.Step;

import static java.lang.String.format;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;
import static ru.yandex.qatools.htmlelements.matchers.WebElementMatchers.isDisplayed;

/**
 * Created by kurau.
 */
public class DefaultSteps {

    private static final String MAIN_PAGE = "http://weather.lanwen.ru/#?cities=%s";
    private static final String MAIN_PAGE_WITHOUT_PARAMETERS = "http://weather.lanwen.ru/";


    private WebDriver driver;

    public DefaultSteps(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Открываем главную страницу для города «{0}»")
    public void openMainPageWithCity(String city) {
        driver.get(format(MAIN_PAGE, city));
    }

    @Step("Открываем главную страницу без параметра»")
    public void openMainPageWithoutParameter() {
        driver.get(MAIN_PAGE_WITHOUT_PARAMETERS);
    }

    @Step("Должны видеть на странице элементы")
    public void shouldSee(WebElement... elements) {
        for (WebElement element : elements) {
            assertThat("Не отображается элемент: " + element.getAttribute("class") +
                            " and value: " + element.getAttribute("value"),
                    element, isDisplayed());
        }
    }

    @Step("Заголовок виджета должен совпадать с запрошенным городом")
    public void shouldSeeTitleWidgetEqualCity(String city) {
        assertThat("Заголовок города равен названию виджета",
                onMainPage().getPlaces().get(0).getText(), equalTo(city));
    }

    @Step("На главной странице без виджетов отображается кнопка добавить виджет")
    public void shouldSeeButtonAddWidgetOnMainPage() {
        shouldSee(onMainPage().getAddWidget());
    }

    @Step("На главной странице должна быть только кнопка добавления города")
    public void shouldSeeOnlyButtonAddWidget() {
        shouldSee(onMainPage().getAddWidget());
        assertThat("На главной странице есть виджеты",
                widgetSteps().getAllWidgets().size(), is(0));
    }

    @Step("Должны увидеть главную страницу с кнопкой добавить виджет")
    public void shouldSeeButtonOnlyAddWidgetOnMainPageWithoutParameter() {
        openMainPageWithoutParameter();
        assertThat("На главной странице не отображатся кнопка добавить виджет", onMainPage().getAddWidget(), isDisplayed());
    }

    @Step("На главной странице переименование название виджета")
    public void shouldSeeRenameWidget(String oldName, String newName) {
        widgetSteps().renameWidget(oldName, newName);
        shouldSee(widgetSteps().findElement(widgetSteps().getAllPlaces(), newName));
        assertThat("Новое название в списке названий виджетов не найдено", newName,
                equalTo(widgetSteps().getAllPlaces().get(0).getText()));
    }

    @Step("На главной странице виджеты добавляются")
    public void shouldSeeWidgetAdd() {
        int count = widgetSteps().countWidgets();
        widgetSteps().addWidget();
        assertThat("Количество виджетов не увеличилось",
                widgetSteps().getAllPlaces().size(), is(count + 1));
    }

    @Step("На главной странице виджет можно удалить")
    public void shouldSeeWidgetRemove() {
        int count = widgetSteps().countWidgets();
        onMainPage().getWeatherWidgets().get(0).getRemoveBtn().click();
        assertThat("Количество виджетов не уменьшилось после удаления одного виджета",
                widgetSteps().countWidgets(), is(count - 1));
    }

    @Step("На ввод не полного названия города должно срабатывать автозаполнение")
    public void shouldAutocompliteCity(String city) {
        widgetSteps().autoComplete(city);
        assertThat("Заголовок города равен названию набираемого города",
                widgetSteps().getAllPlaces().get(0).getText(), equalTo(city));
    }

    @Step("На главной странице меняется формат градусов")
    public void shouldSeeChangeFormatTemperature() {
        widgetSteps().shouldSeeTemperatureCelcium();
        widgetSteps().shouldSeeChangeFormatDegreeCelciumToKelvin();
        widgetSteps().shouldSeeChangeFormatDegreeKelvinToFarengeit();
        widgetSteps().shouldSeeChangeFormatDegreeFarengeitToKaif();
    }

    public void shouldSeeWidgetElements() {
        shouldSee(onMainPage().getTitleValues().
                toArray(new WebElement[onMainPage().getTitleValues().size()]));

        shouldSee(onMainPage().getHumidity(), onMainPage().getSunrise(),
                onMainPage().getSunset(), onMainPage().getWind());

        widgetSteps().shouldSeeFormatTimeSunrise();
        widgetSteps().shouldSeeFormatTimeSunset();
        widgetSteps().shouldSeeFormatSpeedWind();
        widgetSteps().shouldSeeFormatHumidity();
    }

    private MainPage onMainPage() {
        return new MainPage(driver);
    }

    private WidgetSteps widgetSteps() {
        return new WidgetSteps(driver);
    }
}
