package org.diana;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage extends BasePage{

    public static final String HOMEPAGE_SUFIX = "/posts/all";

    //ELEMENTS

    //when logging in
    @FindBy(xpath = "//div[contains(@aria-label, \"Successful login!\")]")
    private WebElement successfulLoginToastMsg;

    @FindBy(xpath = "//div[@aria-label=\"Successful register!\"]")
    private WebElement successfulRegistrationMsg;

    //CONSTRUCTOR
    public HomePage(WebDriver driver, Logger log) {
        super(driver, log);
        PageFactory.initElements(driver, this);
    }

    public void navigateToHomePage() {
        navigateTo(HOMEPAGE_SUFIX);
    }

    //METHODS

    //BOOLEANS
    public boolean isSuccessfulLoginToastMsgShown() {
        return isElementPresented(successfulLoginToastMsg);
    }

    public boolean isSuccessfulRegistrationToastMsgShown() {
        return isElementPresented(successfulRegistrationMsg);
    }

}
