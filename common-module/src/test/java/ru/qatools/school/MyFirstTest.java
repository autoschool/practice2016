package test.java.ru.qatools.school;

import static main.java.ru.qatools.school.steps.UserSteps.user;

/**
 * @author lanwen (Merkushev Kirill)
 */
public class MyFirstTest {
    
    public void test() {
        user().goTo(null); 
    }
    
}
