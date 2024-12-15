package org.example.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class TestConfig {
    private static final Logger log = LoggerFactory.getLogger(TestConfig.class);
    private static final Properties props = new Properties();
    private static final String CONFIG_DIR = "src/test/resources/config/";

    static {
        loadAllProperties();
    }

    private static void loadAllProperties() {
        try {
            // Load properties in specific order
            loadPropertiesFile("base.properties");        // Base properties
            loadPropertiesFile("android.properties");     // Android specific

            // Log loaded properties
            logConfiguration();

        } catch (IOException e) {
            //log.error("Failed to load properties", e);
            throw new RuntimeException("Failed to load configuration files", e);
        }
    }

    private static void loadPropertiesFile(String fileName) throws IOException {
        File file = new File(CONFIG_DIR + fileName);
        if (file.exists()) {
            try (FileInputStream fis = new FileInputStream(file)) {
                props.load(fis);
                log.info("Loaded properties from: {}", fileName);
            }
        } else {
            log.warn("Properties file not found: {}", fileName);
        }
    }

    // Get property with default value
    public static String getProperty(String key, String defaultValue) {
        log.info("Getting property: {}", key);
        return props.getProperty(key, defaultValue);
    }

    // Get required property - throws exception if not found
    public static String getRequiredProperty(String key) {
        String value = props.getProperty(key);
        if (value == null) {
            throw new RuntimeException("Required property not found: " + key);
        }
        return value;
    }

    public static boolean getRequiredBooleanProperty(String key) {
        String value = getRequiredProperty(key);
        return Boolean.parseBoolean(value);
    }

    private static void logConfiguration() {
        log.info("Current configuration:");
        props.stringPropertyNames().stream()
                .sorted()
                .forEach(key -> {
                    // Mask sensitive values
                    String value = isSensitive(key) ? "****" : props.getProperty(key);
                    log.info("{} = {}", key, value);
                });
    }

    private static boolean isSensitive(String key) {
        return key.toLowerCase().contains("password") ||
                key.toLowerCase().contains("secret") ||
                key.toLowerCase().contains("token");
    }
}
