package org.diana;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends BasePage {

    //CONSTANTS
    public static final String LOGIN_PAGE_SUFIX = "/users/login";

    private static final String VALID_USERNAME = "userValid";
    private static final String VALID_PASS = "123456V";
    private static final String INVALID_PASS = "123456I";

    //ELEMENTS
    @FindBy(xpath = "//p[contains(text(), \"Sign in\")]")
    private WebElement titleLoginForm;

    @FindBy(id = "defaultLoginFormUsername")
    private WebElement usernameOrEmailInput;

    @FindBy(id = "defaultLoginFormPassword")
    private WebElement passwordInput;

    @FindBy(xpath = "//input[contains(@formcontrolname, \"rememberMe\")]")
    private WebElement rememberMeCheckbox;

    @FindBy(id = "sign-in-button")
    private WebElement signInBtn;

    @FindBy(xpath = "//a[contains(@href, \"/users/register\")]")
    private WebElement registerLink;

    //when login is unsuccessful
    @FindBy(xpath = "//div[contains(@aria-label, \"Wrong username or password!\")]")
    private WebElement unsuccessfulLoginToastMsg;

    //CONSTRUCTOR
    public LoginPage(WebDriver driver, Logger log) {
        super(driver, log);
        PageFactory.initElements(driver, this);
    }

    //NAVIGATE TO
    public void navigateToLoginPage() {
        navigateTo(LOGIN_PAGE_SUFIX);
    }

    //USER ACTIONS
    public void provideUsername(String txt) {
        typeTextIn(usernameOrEmailInput, txt);
    }

    public void provideValidUsername() {
        provideUsername(VALID_USERNAME);
    }

    public void providePassword(String txt) {
        typeTextIn(passwordInput, txt);
    }

    public void provideValidPassword() {
        providePassword(VALID_PASS);
    }

    public void provideInvalidPassword() {
        providePassword(INVALID_PASS);
    }

    public void clickOnRememberMeCheckbox() {
        clickOn(rememberMeCheckbox);
    }

    public void clickOnSignInBtn() {
        clickOn(signInBtn);
    }

    public void clickOnRegisterLink() {
        clickOn(registerLink);
    }

    public void loginAsValidUser() {
        //navigateToLoginPage();
        provideValidUsername();
        provideValidPassword();
        clickOnSignInBtn();

    }
    //SUPPORT METHODS
    public String getTitle() {
        return getElementText(titleLoginForm);
    }

    public String getUsernameOrEmailInputPlaceholder() {
        return getElementPlaceholderValue(usernameOrEmailInput);
    }

    public String getPasswordInputPlaceholder() {
        return getElementPlaceholderValue(passwordInput);
    }

    public String getSignInBtnText() {
        return getElementText(signInBtn);
    }

    //BOOLEANS
    public boolean isTitleShown() {
        return isElementPresented(titleLoginForm);
    }

    public boolean isUsernameOrEmailInputShown() {
        return isElementPresented(usernameOrEmailInput);
    }

    public boolean isPasswordInputShown() {
        return isElementPresented(passwordInput);
    }

    public boolean isRememberMeCheckboxShown() {
        return isElementPresented(rememberMeCheckbox);
    }

    public boolean isSignInBtnShown() {
        return isElementPresented(signInBtn);
    }

    public boolean isRegisterLinkShown() {
        return isElementPresented(registerLink);
    }

    public boolean isUnsuccessfulToastMsgShown() {
        return isElementPresented(unsuccessfulLoginToastMsg);
    }
}
