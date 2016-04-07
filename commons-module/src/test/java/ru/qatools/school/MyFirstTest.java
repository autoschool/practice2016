package ru.qatools.school;

import org.junit.Test;
import ru.qatools.school.data.Place;

import static org.junit.Assert.assertThat;
import static org.junit.*;
import static ru.qatools.school.steps.UserSteps.user;

/**
 * @author lanwen (Merkushev Kirill)
 */
public class MyFirstTest {


    @Test
    public void test() {
        assertThat(user().goTo(Place.HOME), );
    }
    
}
