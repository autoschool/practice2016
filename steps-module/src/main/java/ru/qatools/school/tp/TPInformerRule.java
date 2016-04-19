package ru.qatools.school.tp;

import org.junit.AssumptionViolatedException;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import ru.qatools.school.tp.client.TPClient;
import ru.yandex.qatools.allure.annotations.TestCaseId;

import static com.google.common.collect.Lists.newArrayList;
import static java.lang.System.currentTimeMillis;

/**
 * @author lanwen (Merkushev Kirill)
 */
public class TPInformerRule extends TestWatcher {

    private String project;
    private long start;

    public TPInformerRule(String project) {
        this.project = project;
    }

    @Override
    protected void starting(Description description) {
        start = currentTimeMillis();
        updateWithStatus("STARTED", description);
    }

    @Override
    protected void skipped(AssumptionViolatedException e, Description description) {
        updateWithStatus("SKIPPED", description);
    }

    @Override
    protected void failed(Throwable e, Description description) {
        if (e instanceof AssertionError) {
            updateWithStatus("FAILED", description); 
        } else {
            updateWithStatus("BROKEN", description);
        }
    }

    @Override
    protected void succeeded(Description description) {
        updateWithStatus("PASSED", description);
    }

    private void updateWithStatus(String status, Description description) {
        if (description.getAnnotation(TestCaseId.class) != null) {
            TPClient.callUpdate(project,
                    newArrayList(
                            new TPClient.TCaseStatus(new TPClient.TCase(idFrom(description)), 
                                    status, 
                                    currentTimeMillis() - start
                            )));
        }
    }

    private String idFrom(Description description) {
        return description.getAnnotation(TestCaseId.class).value();
    }

}
