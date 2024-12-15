package org.example.config;

import io.appium.java_client.android.options.UiAutomator2Options;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AndroidConfig {
    private static final Logger log = LoggerFactory.getLogger(AndroidConfig.class);

    private AndroidConfig() {
    }

    // Get configurations using TestConfig
    public static String getDeviceName() {
        return TestConfig.getRequiredProperty("android.deviceName");
    }

    public static String getAppPath() {
        return TestConfig.getProperty( "android.appPath", null);
    }

    public static String getAppPackage() {
        return TestConfig.getRequiredProperty("android.appPackage");
    }

    public static String getAppActivity() {
        return TestConfig.getRequiredProperty("android.appActivity");
    }

    public static String getPlatformVersion() {
        return TestConfig.getRequiredProperty("android.platformVersion");
    }

    public static String getAutomationName() {
        return TestConfig.getRequiredProperty("android.automationName");
    }

    public static boolean getNoReset() {
        return TestConfig.getRequiredBooleanProperty("android.noReset");
    }

    public static UiAutomator2Options getCapabilities() {
        log.info("Setting up Appium capabilities");
        UiAutomator2Options options = new UiAutomator2Options()
                .setDeviceName(getDeviceName())
                .setPlatformVersion(getPlatformVersion())
                .setAutomationName(getAutomationName())
                .setAppPackage(getAppPackage())
                .setAppActivity(getAppActivity())
                .setNoReset(getNoReset());

        // Only set app path if it exists in properties - not needed for already installed app
        String appPath = getAppPath();
        if (appPath != null && !appPath.isEmpty()) {
            options.setApp(appPath);
        }

        return options;
    }
}