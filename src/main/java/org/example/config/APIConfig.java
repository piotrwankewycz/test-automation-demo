package org.example.config;

public class APIConfig {

    private APIConfig() {
    }

    public static String getBaseUrl() {
        return TestConfig.getRequiredProperty("api.baseUrl");
    }

    public static String getEmail() {
        return TestConfig.getRequiredProperty("api.email");
    }

    public static String getToken() {
        return TestConfig.getRequiredProperty("api.token");
    }
}
