package org.example.core;

import io.restassured.specification.RequestSpecification;
import org.example.config.APIConfig;
import org.testng.annotations.BeforeClass;

import static io.restassured.RestAssured.given;

public class APIBase extends BaseTest {
    protected RequestSpecification requestSpec;

    @BeforeClass
    public void setUp() {
        requestSpec = given()
                .auth()
                .preemptive()
                .basic(APIConfig.getEmail(), APIConfig.getToken())
                .header("Accept", "application/json")
                .log().ifValidationFails();
    }
}