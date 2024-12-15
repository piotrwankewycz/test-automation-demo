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

// IssueTypeScreen.java
public class IssueTypeScreen extends BaseScreen {
    private static final Logger log = LoggerFactory.getLogger(IssueTypeScreen.class);

    @AndroidFindBy(id = "com.atlassian.android.jira.core:id/title")
    private WebElement title;

    @AndroidFindBy(xpath = "//android.widget.TextView[@resource-id='com.atlassian.android.jira.core:id/mainTitle' and @text='Task']")
    private WebElement taskOption;

    @AndroidFindBy(xpath = "//android.widget.TextView[@resource-id='com.atlassian.android.jira.core:id/mainTitle' and @text='Epik']")
    private WebElement epikOption;

    public IssueTypeScreen(AndroidDriver driver) {
        super(driver);
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    public String getTitle() {
        String titleText = wait.until(ExpectedConditions.visibilityOf(title)).getText();
        log.info("Extracted text from Issue type popup Title: {}", titleText);
        return titleText;
    }

    public boolean isTaskOptionDisplayed() {
        return wait.until(ExpectedConditions.visibilityOf(taskOption)).isDisplayed();
    }

    public boolean isEpikOptionDisplayed() {
        return wait.until(ExpectedConditions.visibilityOf(epikOption)).isDisplayed();
    }
}