package org.example.core;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

// Base Test Class
public class BaseTest {
    @BeforeSuite(alwaysRun = true)
    public void globalSetup() {
        // Global setup like report initialization
    }

    @AfterSuite(alwaysRun = true)
    public void globalTeardown() {
        // Global cleanup
    }
}