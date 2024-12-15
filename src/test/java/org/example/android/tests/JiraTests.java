package org.example.android.tests;

import org.example.android.screens.HomeScreen;
import org.example.android.screens.IssueTypeScreen;
import org.example.core.AndroidBaseTest;
import org.example.utils.LoggedAssert;
import org.testng.annotations.*;

public class JiraTests extends AndroidBaseTest {

    private HomeScreen homeScreen;

    @BeforeMethod
    public void navigateToHome() {
        // Get back to home screen before each test if needed
        homeScreen = new HomeScreen(driver);
        homeScreen.clickHomeButton();
    }

    @Test
    public void testCreateIssue() {
        homeScreen = new HomeScreen(driver);

        IssueTypeScreen issueTypeScreen = homeScreen
                .clickCreateIssue()
                .openIssueTypeDropdown();

        LoggedAssert.assertEquals(issueTypeScreen.getTitle(), "Issue type",
                "Title should be 'Issue type'");
        LoggedAssert.assertTrue(issueTypeScreen.isTaskOptionDisplayed(),
                "Task option should be displayed");
        LoggedAssert.assertTrue(issueTypeScreen.isEpikOptionDisplayed(),
                "Epik option should be displayed");
    }
}