package ru.qatools.school.steps.websteps;

import ru.qatools.school.pages.blocks.WeatherWidget;
import ru.yandex.qatools.allure.annotations.Step;

import static org.hamcrest.core.StringStartsWith.startsWith;
import static org.junit.Assert.assertThat;

/**
 * @author gladnik (Gladkov Nikolai)
 */
public class WidgetSteps {

    @Step("Открываем главную страницу для города «{0}»")
    public static void expectTitle(WeatherWidget wiget, String city) {
        assertThat("City should match widgetTitle!", wiget.getWidgetTitle().getText(), startsWith(city));
    }
}
