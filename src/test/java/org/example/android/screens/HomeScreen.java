package org.example.android.screens;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.example.core.BaseScreen;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HomeScreen extends BaseScreen {
    private static final Logger log = LoggerFactory.getLogger(HomeScreen.class);

    @AndroidFindBy(accessibility = "create issue")
    private WebElement createIssueButton;

    public HomeScreen(AndroidDriver driver) {
        super(driver);
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    public HomeScreen clickHomeButton() {
        wait.until(ExpectedConditions.presenceOfElementLocated(
                AppiumBy.accessibilityId("Home")
        )).click();
        log.info("Clicked Home button");
        return this;
    }

    public CreateIssueScreen clickCreateIssue() {
        wait.until(ExpectedConditions.elementToBeClickable(createIssueButton)).click();
        log.info("Clicked + (create issue) button");
        return new CreateIssueScreen(driver);
    }
}
