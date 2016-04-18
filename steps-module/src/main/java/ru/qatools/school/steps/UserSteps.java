package ru.qatools.school.steps;

import ru.qatools.school.data.Place;
import ru.yandex.qatools.allure.annotations.Features;
import ru.yandex.qatools.allure.annotations.Step;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;


/**
 * @author lanwen (Merkushev Kirill)
 * @author gladnik (Gladkov Nikolai)
 */
public class UserSteps {

    private Place place;

    private UserSteps() {
    }

    public static UserSteps user() {
        return new UserSteps();
    }

    @Step("Идем в место {0}")
    public UserSteps goTo(Place place) {
        this.place = place;
        return this;
    }

    @Step("Должны находиться в месте {0}")
    public UserSteps shouldBeAtPlace(Place expectedPlace) {
        assertThat("Places should match!", this.place, is(expectedPlace));
        return this;
    }
}
