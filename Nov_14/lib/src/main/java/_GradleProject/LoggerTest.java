package _GradleProject;

import org.apache.log4j.Logger;

public class LoggerTest {
    private static final Logger logger = Logger.getLogger(LoggerTest.class);

    public static void main(String[] args) {
        logger.debug("Debug message");
        logger.info("Info message");
        logger.warn("Warning message");
        logger.error("Error message");
    }
}
