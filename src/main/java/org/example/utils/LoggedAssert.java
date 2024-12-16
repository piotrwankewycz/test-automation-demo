package org.example.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;

public class LoggedAssert {
    private static final Logger log = LoggerFactory.getLogger(LoggedAssert.class);

    private LoggedAssert() {
    }

    private static void assertEqualsGeneric(Object actual, Object expected, String message) {
        try {
            Assert.assertEquals(actual, expected, message);
            log.info("Assertion PASSED: {} - Expected: '{}', Actual: '{}'", message, expected, actual);
        } catch (AssertionError e) {
            log.error("Assertion FAILED: {} - Expected: '{}', Actual: '{}'", message, expected, actual);
            throw e;
        }
    }

    public static void assertEquals(String actual, String expected, String message) {
        assertEqualsGeneric(actual, expected, message);
    }

    public static void assertEquals(Boolean actual, Boolean expected, String message) {
        assertEqualsGeneric(actual, expected, message);
    }

    public static void assertEquals(Number actual, Number expected, String message) {
        assertEqualsGeneric(actual, expected, message);
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