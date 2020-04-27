package openapi.autotest;

import io.qameta.allure.Allure;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class TestBase {
    private static Logger logger = LoggerFactory.getLogger(TestBase.class);

    @BeforeEach
    public void beforeTest(TestInfo testInfo) {
        logger.info("--------------- Start Test ---------------");
        logger.info("TEST NAME: " + testInfo.getDisplayName());
        Allure.description(testInfo.getDisplayName());
    }

    @AfterEach
    public void afterTest() {
        logger.info("--------------- End Test ---------------\n");
    }

    /**
     * Stops execution for seconds
     *
     * @param seconds - sleeping seconds
     */
    public static void sleep(int seconds) {
        try {
            logger.info("Waiting " + seconds + " second(s)...");
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
