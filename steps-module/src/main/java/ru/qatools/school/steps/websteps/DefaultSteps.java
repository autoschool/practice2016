package ru.qatools.school.steps.websteps;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import ru.qatools.school.pages.MainPage;
import ru.yandex.qatools.allure.annotations.Step;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import static org.junit.Assert.assertTrue;
import static ru.yandex.qatools.htmlelements.matchers.WebElementMatchers.hasText;
import static org.hamcrest.Matchers.*;
import static java.lang.String.format;
import static org.junit.Assert.assertThat;
import static ru.yandex.qatools.htmlelements.matchers.WebElementMatchers.isDisplayed;

/**
 * Created by kurau.
 */
public class DefaultSteps {

    public static final String MAIN_PAGE = "http://weather.lanwen.ru/#?cities=%s";

    private WebDriver driver;
    private File screenShot;

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

    @Step("Должны видеть на странице город «{0}»")
    public void shouldSeeCity(String city){
        assertThat(onMainPage().getWeatherCities(), hasItem(hasText(city)));
    }

    @Step("Нажимаем на «{0}»")
    public void clickOn(WebElement element){
        element.click();
    }

    @Step("Должны видеть количество виджетов - «{0}»")
    public void shouldSeeCountWidget(int expectedCountWidget){
        int actualCountWidget = onMainPage().getWeatherWidgets().size();
        assertThat("Должны видеть верное количество виджетов ", actualCountWidget, is(expectedCountWidget));
    }

    @Step("Должны видеть температуру «{0}»")
    public void shouldSeeTemperatureIn(String unit){
        assertThat(onMainPage().getWeatherWidgets(), hasItem(hasText(containsString(unit))));
    }

    @Step("Ввести текст в «{0}»")
    public void inputText(String text){
        WebElement inputPlace = onMainPage().getWeatherWidgets().get(0).getWidgetTitle().inputPlace();
        inputPlace.clear();
        inputPlace.sendKeys(text, Keys.ENTER);
    }

    @Step("Получить скриншот")
    public void takeScreenshot(){
        screenShot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(screenShot, new File("screen.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Step("Должны увидить соответствие скриншотов")
    public void shouldSeeEqualsScreenShots(){
        int[][] expectedScreen = getArrayRGB(getImage(new File("etalon_screen.png")));
        int[][] actualScreen = getArrayRGB(getImage(screenShot));

        assertTrue("Скриншоты должны быть идентичны", Arrays.deepEquals(actualScreen, expectedScreen));
    }

    private BufferedImage getImage(File file){
        BufferedImage image = null;
        try {
            image = ImageIO.read(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return image;
    }

    private int[][] getArrayRGB(BufferedImage image){
        int w = image.getWidth();
        int h = image.getHeight();

        int[][] screen = new int[w][h];

        for( int i = 0; i < w; i++ ){
            for( int j = 0; j < h; j++ ){
                screen[i][j] = image.getRGB( i, j );
            }
        }
        return screen;
    }

    private MainPage onMainPage() {
        return new MainPage(driver);
    }
}
