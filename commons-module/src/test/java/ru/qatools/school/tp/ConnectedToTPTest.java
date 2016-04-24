package ru.qatools.school.tp;

import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import ru.yandex.qatools.allure.annotations.TestCaseId;

import java.io.IOException;

import static java.util.concurrent.TimeUnit.SECONDS;
import static junit.framework.TestCase.fail;
import static org.hamcrest.Matchers.is;
import static org.junit.Assume.assumeThat;

public class ConnectedToTPTest {
    @Rule
    public TPInformerRule tms = new TPInformerRule("ruletest");


    @Test
    @Ignore
    @TestCaseId("1")
    public void shouldMarkCaseStatusAsPassed() throws IOException, InterruptedException {
        SECONDS.sleep(15);
    }


    @Test
    @Ignore
    @TestCaseId("2")
    public void shouldFail() throws IOException, InterruptedException {
        SECONDS.sleep(5);
        fail();
    }

    @Test
    @Ignore
    @TestCaseId("3")
    public void shouldBroke() throws IOException, InterruptedException {
        SECONDS.sleep(5);
        throw new RuntimeException();
    }

    @Test
    @Ignore
    @TestCaseId("4")
    public void shouldBeSkipped() throws IOException, InterruptedException {
        SECONDS.sleep(5);
        assumeThat(true, is(false));
    }
}

