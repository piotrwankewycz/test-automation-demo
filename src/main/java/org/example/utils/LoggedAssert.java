package org.example.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;

public class LoggedAssert {
    private static final Logger log = LoggerFactory.getLogger(LoggedAssert.class);

    private LoggedAssert() {
    }

    public static void assertEquals(String actual, String expected, String message) {
        try {
            Assert.assertEquals(actual, expected, message);
            log.info("Assertion PASSED: {} - Expected: '{}', Actual: '{}'", message, expected, actual);
        } catch (AssertionError e) {
            log.error("Assertion FAILED: {} - Expected: '{}', Actual: '{}'", message, expected, actual);
            throw e;
        }
    }

    public static void assertTrue(boolean condition, String message) {
        try {
            Assert.assertTrue(condition, message);
            log.info("Assertion PASSED: {}", message);
        } catch (AssertionError e) {
            log.error("Assertion FAILED: {}", message);
            throw e;
        }
    }
}