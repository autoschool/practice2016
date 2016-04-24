package ru.qatools.school.webtests.pagetest;

import org.junit.Test;
import ru.qatools.school.webtests.BaseWeatherAppTest;
import ru.yandex.qatools.allure.annotations.TestCaseId;
import ru.yandex.qatools.allure.annotations.Title;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNot.not;


public class PageIsNotEmptyTest extends BaseWeatherAppTest {

    @TestCaseId("6")
    @Test
    @Title("При открытии страницы с пустым параметром cites на странице должна быть кнопка добавления виджета")
    public void addWidgetbuttonShouldBeOnPage() {
        defaultSteps.openMainPage();
        System.out.println(onMainPage());
        assertThat(onMainPage().getNewWidgetButton(), is(not(null)));
    }

}