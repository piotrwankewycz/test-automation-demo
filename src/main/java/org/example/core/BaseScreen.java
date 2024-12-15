package org.example.core;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public abstract class BaseScreen {
    protected AndroidDriver driver;
    protected WebDriverWait wait;

    public BaseScreen(AndroidDriver driver) {
        this.driver = driver;
        // Selenium's WebDriverWait for explicit waits
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }
}
