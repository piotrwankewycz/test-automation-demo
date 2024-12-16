package org.example.api.tests;

import io.restassured.response.Response;
import org.example.core.APIBase;
import org.example.utils.LoggedAssert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class IssueTypeTests extends APIBase {
    private static final Logger log = LoggerFactory.getLogger(IssueTypeTests.class);
    private static final String ISSUE_TYPES_ENDPOINT = "/rest/api/3/issuetype";
    private String createdIssueTypeId;
    private String issueTypeDescription;
    private String issueTypeName;

    @BeforeClass
    public void setupTestData() {
        final String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
        issueTypeDescription = "test description " + timestamp;
        issueTypeName = "issuetype" + timestamp;
        log.info("Generated test data with timestamp: {}", timestamp);
    }

    @Test
    public void testCreateIssueType() {
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("description", issueTypeDescription);
        requestBody.put("hierarchyLevel", 0);
        requestBody.put("name", issueTypeName);

        Response response = requestSpec
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .post(ISSUE_TYPES_ENDPOINT)
                .then()
                .statusCode(201)
                .extract()
                .response();

        // Store ID for next test
        createdIssueTypeId = response.path("id");
        log.info("Created issue type with ID: {}", createdIssueTypeId);

        LoggedAssert.assertEquals(response.path("description"), issueTypeDescription,
                "Description should match");
        LoggedAssert.assertEquals(response.path("name"), issueTypeName,
                "Name should match");
        LoggedAssert.assertEquals(response.path("subtask"), false,
                "Subtask should be false");
        LoggedAssert.assertEquals(response.path("hierarchyLevel"), 0,
                "Hierarchy level should be 0");
    }

    @Test(dependsOnMethods = "testCreateIssueType")
    public void testGetCreatedIssueType() {
        Response response = requestSpec
                .when()
                .get(ISSUE_TYPES_ENDPOINT)
                .then()
                .statusCode(200)
                .extract()
                .response();

        List<Map<String, Object>> issueTypes = response.jsonPath().getList("");
        Map<String, Object> createdType = issueTypes.stream()
                .filter(type -> type.get("id").toString().equals(createdIssueTypeId))
                .findFirst()
                .orElseThrow(() -> new AssertionError("Created issue type not found"));

        LoggedAssert.assertEquals(createdType.get("description").toString(), issueTypeDescription,
                "Description should match");
        LoggedAssert.assertEquals(createdType.get("name").toString(), issueTypeName,
                "Name should match");
        LoggedAssert.assertEquals(createdType.get("subtask").toString(), "false",
                "Subtask should be false");
        LoggedAssert.assertEquals(createdType.get("hierarchyLevel").toString(), "0",
                "Hierarchy level should be 0");

        log.info("Successfully verified created issue type with ID: {}", createdIssueTypeId);
    }

    @AfterClass()
    public void teardownTestData() {
        requestSpec
                .when()
                .delete(ISSUE_TYPES_ENDPOINT + "/" + createdIssueTypeId)
                .then()
                .statusCode(204);
    }
}