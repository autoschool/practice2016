package ru.qatools.school.steps.websteps.util;

import org.hamcrest.Matchers;
import org.openqa.selenium.WebElement;
import ru.yandex.qatools.allure.annotations.Step;
import ru.yandex.qatools.htmlelements.element.HtmlElement;

import java.util.Collection;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.hamcrest.core.AllOf.allOf;
import static org.hamcrest.core.Every.everyItem;
import static ru.qatools.school.matchers.HasTextSafeMatcher.hasText;
import static ru.qatools.school.matchers.IsConvertibleToDouble.isDouble;
import static ru.yandex.qatools.htmlelements.matchers.MatcherDecorators.should;
import static ru.yandex.qatools.htmlelements.matchers.MatcherDecorators.timeoutHasExpired;
import static ru.yandex.qatools.htmlelements.matchers.WebElementMatchers.isDisplayed;
import static ru.yandex.qatools.htmlelements.matchers.common.DoesElementExistMatcher.exists;

/**
 * @author gladnik (Nikolai Gladkov)
 */
public class CheckingSteps {

    private static final int WAIT_TIMEOUT = 10;
    private static final int POLLING_INTERVAL = 100;

    @Step("Должны видеть элемент «{0}»")
    public static void shouldSee(HtmlElement element) {
        assertThat("Должны видеть элемент", element,
                should(allOf(exists(), notNullValue(), isDisplayed()))
                        .whileWaitingUntil(timeoutHasExpired(WAIT_TIMEOUT)
                                .withPollingInterval(POLLING_INTERVAL)));
    }

    @Step("Должны видеть каждый из «{0}»")
    public static void shouldSee(Collection<? extends WebElement> elements) {
        assertThat("Должны видеть элементы", elements,
                everyItem(allOf(exists(), notNullValue(), isDisplayed())));
    }

    @Step("Текст элемента «{0}» должен быть «{1}»")
    public static void shouldHaveText(HtmlElement element, String text) {
        assertThat("Неверный текст элемента", element,
                should(hasText(text))
                        .whileWaitingUntil(timeoutHasExpired(WAIT_TIMEOUT)
                                .withPollingInterval(POLLING_INTERVAL)));
    }

    @Step("Количество элементов «{0}» должно быть {1}")
    public static void shouldHaveSize(Collection<? extends WebElement> elements, int size) {
        assertThat("Неверное количество элементов", elements, hasSize(size));
    }

    @Step("Значение {0} должно находится в диапазоне от {1} до {2}")
    public static void shouldBeInRange(double value, double bot, double top) {
        assertThat("Значение вне допустимого диапазона", value,
                Matchers.allOf(greaterThanOrEqualTo(bot), lessThanOrEqualTo(top)));
    }

    @Step("Текст «{0}» должен заканчиваться на «{1}»")
    public static void shouldEndWith(String text, String tail) {
        assertThat("Текст заканчивается неверно", text, endsWith(tail));
    }

    @Step("Текст «{0}» должно быть можно сконвертировать в Double")
    public static void shouldBeConvertibleToDouble(String text) {
        assertThat("Текст не конвертируется в Double", text, isDouble());
    }
}
