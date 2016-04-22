package ru.qatools.school.steps.websteps;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.qatools.school.pages.MainPage;
import ru.qatools.school.pages.MainPageMethods;
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

    public static final String MAIN_PAGE = "http://weather.lanwen.ru/#?cities=%s";
    private static final String MAIN_PAGE_WITHOUT_PARAMETERS = "http://weather.lanwen.ru/";
    private static final String NEW_WIDGET = "What a city?";

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

    @Step("Должны видеть на странице «{0}»")
    public void shouldSee(WebElement...elements) {
        for (WebElement element : elements) {
            assertThat("Не отображается элемент " + element.getText(), element, isDisplayed());
        }
    }

    @Step("Заголовок виджета должен совпадать с запрошенным городом")
    public void shouldSeeTitleWidgetEqualCity(String city) {
        assertThat("Заголовок города равен названию виджета",
                onMainPage().getPlaces().get(0).getText(), equalTo(city));
    }

    public void addWidgetOnMainPage(String city) {
        mainPageMethods().addWidget();
        mainPageMethods().renameWidget(NEW_WIDGET, city);
    }

    @Step("На главной странице без виджетов отображается кнопка добавить виджет")
    public void shouldSeeButtonAddWidgetOnMainPage() {
        shouldSee(mainPageMethods().getMainPage().getAddWidget());
    }

    @Step("На главной странице должна быть только кнопка добавления города")
    public void shouldSeeOnlyButtonAddWidget() {
        assertThat("На главной странице есть виджеты",
                mainPageMethods().getAllWidgets().size(), is(0));
    }

    @Step("Должны увидеть главную страницу с кнопкой добавить виджет")
    public void shouldSeeButtonOnlyAddWidgetOnMainPageWithoutParameter() {
        openMainPageWithoutParameter();
        assertThat("На главной странице не отображатся кнопка добавить виджет", onMainPage().getAddWidget(), isDisplayed());
    }

    @Step("На главной странице переименование название виджета")
    public void shouldSeeRenameWidget(String oldName, String newName) {
        mainPageMethods().renameWidget(oldName, newName);
        shouldSee(mainPageMethods().findElement(mainPageMethods().getAllPlaces(), newName));
        assertThat("Новый виджет в списке виджетов не найден", newName,
                mainPageMethods().hasItem(mainPageMethods().getAllPlaces()));
    }

    @Step("На главной странице виджеты добавляются")
    public void shouldSeeWidgetAdd(String city) {
        int count = mainPageMethods().countWidgets();
        addWidgetOnMainPage(city);
        assertThat("Widget has", city, is(mainPageMethods().hasItem(mainPageMethods().getAllPlaces())));
        assertThat("Количество виджетов не увеличилось", mainPageMethods().getAllWidgets().size(), is(count + 1));
    }

    @Step("На главной странице виджет можно удалить")
    public void shouldSeeWidgetRemove() {
        int count = mainPageMethods().countWidgets();
        mainPageMethods().clickOnElement(mainPageMethods().getAllWidgets().get(0).getRemoveBtn());
        assertThat("Количество виджетов не уменьшилось после удаления одного виджета",
                mainPageMethods().countWidgets(), is(count - 1));
    }

    @Step("На ввод не полного названия города должно срабатывать автозаполнение")
    public void shouldAutocompliteCity(String city) {
        mainPageMethods().autoComplete(city);
        assertThat("Заголовок города равен названию набираемого города",
                mainPageMethods().getAllPlaces().get(0).getText(), equalTo(city));
    }

    @Step("На главной странице меняется формат градусов")
    public void shouldSeeChangeFormatTemperature() {
        widgetSteps().shouldSeeTemperatureCelcium();
        widgetSteps().shouldSeeChangeFormatDegreeCelciumToKelvin();
        widgetSteps().shouldSeeChangeFormatDegreeKelvinToFarengeit();
        widgetSteps().shouldSeeChangeFormatDegreeFarengeitToKaif();
    }

    public void shouldSeeWidgetElements() {
        shouldSee(onMainPage().getTitleValues().toArray(new WebElement[onMainPage().getTitleValues().size()]));
        shouldSee(onMainPage().getHumidity());
        shouldSee(onMainPage().getSunrise());
        shouldSee(onMainPage().getSunset());
        shouldSee(onMainPage().getWind());
        widgetSteps().shouldSeeFormatTimeSunrise();
        widgetSteps().shouldSeeFormatTimeSunset();
        widgetSteps().shouldSeeFormatSpeedWind();
        widgetSteps().shouldSeeFormatHumidity();
    }

    private MainPage onMainPage() {
        return new MainPage(driver);
    }

    private MainPageMethods mainPageMethods() {
        return new MainPageMethods(driver);
    }

    private WidgetSteps widgetSteps() {
        return new WidgetSteps(driver);
    }
}
