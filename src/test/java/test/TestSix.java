package test;

import org.junit.jupiter.api.Test;

public class TestSix extends TestBase {

    @Test
    public void firstTest() throws InterruptedException {
        main.goTo()
                .chooseTask("6");
        taskSix.fillInLogin("tester")
                .fillInPassword("123-xyz")
                .loginButtonClick()
                .isLoginCorrect();
    }
}
