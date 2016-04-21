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
    private static final String NEW_WIDGET = "What a city?";
    private static final String CELCIUM = "°C";
    private static final String KELVIN = "°K";
    private static final String FARINGEIT = "°F";
    private static final String KAIF = "°Kaif";
    private static final String SUNRISE = "Sunrise";
    private static final String SUNSET = "Sunset";
    private static final String WIND = "Wind";
    private static final String HUMIDITY = "Humidity";
    private static final String SUNRISE_TIME = "^[0-2][0-9]:[0-5][0-9]$";
    private static final String SUNSET_TIME = "^[0-2][0-9]:[0-5][0-9]$";
    private static final String WIND_SPEED = "^[0-9]+ m/s";
    private static final String HUMIDITY_VALUE = "^[0-9]+ %";


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

    public void addWidgetOnMainPage(String city) {
        mainPageMethods().addWidget();
        mainPageMethods().renameWidget(NEW_WIDGET, city);
    }

    @Step("На главной странице без виджетов отображается кнопка добавить виджет")
    public void shouldSeeButtonAddWidgetOnMainPage() {
        assertThat("На главной странице нет кнопки добавит виджет",
                mainPageMethods().getMainPage().getAddWidget(), isDisplayed());
    }

    @Step("На главной странице должна быть только кнопка добавления города")
    public void shouldSeeOnlyButtonAddWidget() {
        assertThat("На главной странице только кнопка добавления виджета",
                mainPageMethods().allWidgets().size(), is(0));
    }

    @Step("На главной странице переименование название виджета")
    public void shouldSeeRenameWidget(String oldName, String newName) {
        mainPageMethods().renameWidget(oldName, newName);
        assertThat("Widget has", newName, is(mainPageMethods().hasItem(mainPageMethods().getAllPlaces())));
    }


    @Step("На главной странице виджеты добавляются")
    public void shouldSeeWidgetAdd(String city) {
        int count = mainPageMethods().countWidgets();
        addWidgetOnMainPage(city);
        assertThat("Widget has", city, is(mainPageMethods().hasItem(mainPageMethods().getAllPlaces())));
        assertThat("Количество виджетов не увеличилось", mainPageMethods().allWidgets().size(), is(count + 1));
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
                mainPageMethods().getAllPlaces().get(0).getText(), equalTo(city));
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
        assertThat("Начальное значение температуры не в Цельсиях",
                mainPageMethods().allWidgets().get(0).getWeatherType().getText(), equalTo(CELCIUM));
    }

    @Step("Должны увидеть надпись показателя рассвета")
    public void shouldSeeTitleSunrise() {
        assertThat("Заголовка показателя рассвета нет на главной странице", SUNRISE,
                mainPageMethods().hasItem(mainPageMethods().getTitleValues()));
        assertThat("Заголовок показателя рассвета не отображается на главной странице",
                mainPageMethods().findElement(mainPageMethods().getTitleValues(), SUNRISE), isDisplayed());
    }

    @Step("Должны увидеть надпись показателя заката")
    public void shouldSeeTitleSunset() {
        assertThat("Заголовка показателя рассвета нет на главной странице", SUNSET,
                mainPageMethods().hasItem(mainPageMethods().getTitleValues()));
        assertThat("Заголовок показателя рассвета не отображается на главной странице",
                mainPageMethods().findElement(mainPageMethods().getTitleValues(), SUNSET), isDisplayed());
    }

    @Step("Должны увидеть надпись показателя скорости ветра")
    public void shouldSeeTitleWind() {
        assertThat("Заголовка показателя рассвета нет на главной странице", WIND,
                mainPageMethods().hasItem(mainPageMethods().getTitleValues()));
        assertThat("Заголовок показателя рассвета не отображается на главной странице",
                mainPageMethods().findElement(mainPageMethods().getTitleValues(), WIND), isDisplayed());
    }

    @Step("Должны увидеть надпись показателя влажности")
    public void shouldSeeTitleHumidity() {
        assertThat("Заголовка показателя рассвета нет на главной странице", HUMIDITY,
                mainPageMethods().hasItem(mainPageMethods().getTitleValues()));
        assertThat("Заголовок показателя рассвета не отображается на главной странице",
                mainPageMethods().findElement(mainPageMethods().getTitleValues(), HUMIDITY), isDisplayed());
    }

    @Step("Должны увидеть картинку для показателя рассвета")
    public void shouldSeeImageSunrise() {
        assertThat("Картинка показателя рассвета не отображается", mainPageMethods().getMainPage().getSunrise(), isDisplayed());
    }

    @Step("Должны увидеть картинку для показателя заката")
    public void shouldSeeImageSunset() {
        assertThat("Картинка показателя заката не отображается", mainPageMethods().getMainPage().getSunset(), isDisplayed());
    }

    @Step("Должны увидеть картинку для показателя скорости ветра")
    public void shouldSeeImageWind() {
        assertThat("Картинка показателя скорости ветра не отображается", mainPageMethods().getMainPage().getWind(), isDisplayed());
    }

    @Step("Должны увидеть картинку для показателя влажности")
    public void shouldSeeImageHumidity() {
        assertThat("Картинка показателя влажности не отображается", mainPageMethods().getMainPage().getHumidity(), isDisplayed());
    }

    @Step("Формат времени для значения поля рассвет должен быть 'xx:xx'")
    public void shouldSeeFormatTimeSunrise() {
        assertThat("Формат времени для значения поля рассвет не 'xx:xx'",
                mainPageMethods().getMainPage().getInfoValues().get(0).getText(), mainPageMethods().stringMatcher(SUNRISE_TIME));
    }

    @Step("Формат времени для значения поля закат должен быть 'xx:xx'")
    public void shouldSeeFormatTimeSunset() {
        assertThat("Формат времени для значения поля закат не 'xx:xx'",
                mainPageMethods().getMainPage().getInfoValues().get(1).getText(), mainPageMethods().stringMatcher(SUNSET_TIME));
    }

    @Step("Формат времени для значения поля скорость ветра должен быть 'xx m/s'")
    public void shouldSeeFormatSpeedWind() {
        assertThat("Формат времени для значения поля скорость ветра не 'xx m/s'",
                mainPageMethods().getMainPage().getInfoValues().get(2).getText(), mainPageMethods().stringMatcher(WIND_SPEED));
    }


    @Step("Формат времени для значения поля влажность должен быть 'xx %'")
    public void shouldSeeFormatHumidity() {
        assertThat("Формат времени для значения поля влажность не 'xx %'",
                mainPageMethods().getMainPage().getInfoValues().get(3).getText(), mainPageMethods().stringMatcher(HUMIDITY_VALUE));
    }

    private MainPage onMainPage() {
        return new MainPage(driver);
    }

    private MainPageMethods mainPageMethods() {
        return new MainPageMethods(driver);
    }
}
