package org.example.android.screens;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.example.core.BaseScreen;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CreateIssueScreen extends BaseScreen {
    private static final Logger log = LoggerFactory.getLogger(CreateIssueScreen.class);

    @AndroidFindBy(id = "com.atlassian.android.jira.core:id/issueTypeDropDownArrowIv")
    private WebElement issueTypeDropdown;

    public CreateIssueScreen(AndroidDriver driver) {
        super(driver);
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    public IssueTypeScreen openIssueTypeDropdown() {
        wait.until(ExpectedConditions.elementToBeClickable(issueTypeDropdown)).click();
        log.info("Clicked arrow button opening issue types");
        return new IssueTypeScreen(driver);
    }
}
