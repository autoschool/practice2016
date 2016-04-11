package ru.qatools.school;

import org.junit.Assert;
import org.junit.Test;
import ru.qatools.school.data.Place;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static ru.qatools.school.steps.UserSteps.user;

/**
 * @author lanwen (Merkushev Kirill)
 */
public class MyFirstTest {

    @Test
    public void shouldSeeNull() {
        Assert.assertThat(user().goTo(null).getPlace(), is(equalTo(null)));
    }

    @Test
    public void shouldSeeHome() {
        Assert.assertThat(user().goTo(Place.HOME).getPlace(), is(equalTo(Place.HOME)));
    }

    @Test
    public void shouldSeeYandex() {
        Assert.assertThat(user().goTo(Place.AT_YANDEX).getPlace(), is(equalTo(Place.AT_YANDEX)));
    }
    
}
