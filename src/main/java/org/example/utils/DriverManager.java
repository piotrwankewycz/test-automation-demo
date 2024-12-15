package org.example.utils;

import io.appium.java_client.android.AndroidDriver;
import org.example.config.AndroidConfig;
import org.example.config.TestConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URI;

public class DriverManager {
    private static final Logger log = LoggerFactory.getLogger(DriverManager.class);
    private static final ThreadLocal<AndroidDriver> androidDriver = new ThreadLocal<>();

    private DriverManager() {
    }

    public static AndroidDriver getAndroidDriver() {
        log.info("Getting AndroidDriver");
        AndroidDriver driver = androidDriver.get();
        if (driver == null) {
            driver = createAndroidDriver();
            androidDriver.set(driver);
        }
        return driver;
    }

    private static AndroidDriver createAndroidDriver() {
        log.info("Creating AndroidDriver");
        try {
            return new AndroidDriver(
                    new URI(TestConfig.getRequiredProperty("appium.url")).toURL(),
                    AndroidConfig.getCapabilities()
            );
        } catch (Exception e) {
            throw new SetupException("Failed to create Android driver", e);
        }
    }

    public static void quitAndroidDriver() {
        log.info("Closing AndroidDriver");
        AndroidDriver driver = androidDriver.get();
        if (driver != null) {
            driver.quit();
            androidDriver.remove();
        }
    }
}
