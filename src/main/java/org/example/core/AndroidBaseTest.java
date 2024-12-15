package org.example.core;

import io.appium.java_client.android.AndroidDriver;
import org.example.utils.DriverManager;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class AndroidBaseTest extends BaseTest {
    protected AndroidDriver driver;

    @BeforeClass
    public void setUp() {
        driver = DriverManager.getAndroidDriver();
    }

    @AfterClass
    public void tearDown() {
        DriverManager.quitAndroidDriver();
    }
}
