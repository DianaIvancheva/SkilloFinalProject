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
    private WebElement usernameOrEmailInputLoginForm;

    @FindBy(id = "defaultLoginFormPassword")
    private WebElement passwordInputLoginForm;

    @FindBy(xpath = "//input[contains(@formcontrolname, \"rememberMe\")]")
    private WebElement rememberMeCheckboxLoginForm;

    @FindBy(id = "sign-in-button")
    private WebElement signInBtnLoginForm;

    @FindBy(xpath = "//a[contains(@href, \"/users/register\")]")
    private WebElement registerLinkLoginForm;

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
    public void provideUsernameLoginForm(String txt) {
        typeTextIn(usernameOrEmailInputLoginForm, txt);
    }

    public void provideValidUsernameLoginForm() {
        provideUsernameLoginForm(VALID_USERNAME);
    }

    public void providePasswordLoginForm(String txt) {
        typeTextIn(passwordInputLoginForm, txt);
    }

    public void provideValidPasswordLoginForm() {
        providePasswordLoginForm(VALID_PASS);
    }

    public void provideInvalidPasswordLoginForm() {
        providePasswordLoginForm(INVALID_PASS);
    }

    public void clickOnRememberMeCheckboxLoginForm() {
        clickOn(rememberMeCheckboxLoginForm);
    }

    public void clickOnSignInBtnLoginForm() {
        clickOn(signInBtnLoginForm);
    }

    public void clickOnRegisterLinkLoginForm() {
        clickOn(registerLinkLoginForm);
    }

    public void loginAsValidUser() {
        provideValidUsernameLoginForm();
        provideValidPasswordLoginForm();
        clickOnSignInBtnLoginForm();

    }
    //SUPPORT METHODS
    public String getTitleLoginPage() {
        return getElementText(titleLoginForm);
    }

    public String getUsernameOrEmailInputPlaceholderLoginForm() {
        return getElementPlaceholderValue(usernameOrEmailInputLoginForm);
    }

    public String getPasswordInputPlaceholderLoginForm() {
        return getElementPlaceholderValue(passwordInputLoginForm);
    }

    public String getSignInBtnTextLoginForm() {
        return getElementText(signInBtnLoginForm);
    }

    //BOOLEANS
    public boolean isTitleLoginFormShown() {
        return isElementPresented(titleLoginForm);
    }

    public boolean isUsernameOrEmailInputLoginFormShown() {
        return isElementPresented(usernameOrEmailInputLoginForm);
    }

    public boolean isPasswordInputLoginFormShown() {
        return isElementPresented(passwordInputLoginForm);
    }

    public boolean isRememberMeCheckboxLoginFormShown() {
        return isElementPresented(rememberMeCheckboxLoginForm);
    }

    public boolean isSignInBtnLoginFormShown() {
        return isElementPresented(signInBtnLoginForm);
    }

    public boolean isRegisterLinkLoginFormShown() {
        return isElementPresented(registerLinkLoginForm);
    }

    public boolean isUnsuccessfulToastMsgShown() {
        return isElementPresented(unsuccessfulLoginToastMsg);
    }
}
