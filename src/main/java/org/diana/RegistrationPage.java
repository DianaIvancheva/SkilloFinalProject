package org.diana;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class RegistrationPage extends BasePage {

    //CONSTANTS
    public static final String REGISTRATION_PAGE_SUFIX = "/users/register";

    public static final String VALID_BIRTH_DATE = "24042002";
    public static final String VALID_PASSWORD = "12345678V";
    public static final String VALID_PUBLIC_INFO = "Hello!";

    //ELEMENTS
    @FindBy(xpath = "//h4[contains(text(), \"Sign up\")]")
    private WebElement titleRegForm;

    @FindBy(xpath = "//input[contains(@name, \"username\")]")
    private WebElement usernameInput;

    @FindBy(xpath = "//input[contains(@type, \"email\")]")
    private WebElement emailInput;

    @FindBy(xpath = "//input[contains(@formcontrolname, \"birthDate\")]")
    private WebElement birthDateInput;

    @FindBy(id = "defaultRegisterFormPassword")
    private WebElement passwordInput;

    @FindBy(id = "defaultRegisterPhonePassword")
    private WebElement confirmPasswordInput;

    @FindBy(xpath = "//textarea[contains(@formcontrolname, \"publicInfo\")]")
    private WebElement publicInfoTextArea;

    @FindBy(id = "sign-in-button")
    private WebElement signUpBtn;

    //CONSTRUCTOR
    public RegistrationPage(WebDriver driver, Logger log) {
        super(driver, log);
        PageFactory.initElements(driver, this);
    }

    //NAVIGATION
    public void navigateTo() {
        navigateTo(REGISTRATION_PAGE_SUFIX);
    }

    //USER ACTIONS
    public void provideUsername(String txt) {
        typeTextIn(usernameInput, txt);
    }

    public void provideValidUsername() {
        provideUsername(generateRandomValidUsername());
    }

    public void provideEmail(String txt) {
        typeTextIn(emailInput, txt);
    }

    public void provideValidEmail() {
        provideEmail(generateRandomValidEmail());
    }

    public void provideBirthDate(String txt) {
        typeTextIn(birthDateInput, txt);
    }

    public void provideValidBirthDate() {
        provideBirthDate(VALID_BIRTH_DATE);
    }

    public void providePassword(String txt) {
        typeTextIn(passwordInput, txt);
    }

    public void provideValidPassword() {
        providePassword(VALID_PASSWORD);
    }

    public void provideConfirmPassword(String txt) {
        typeTextIn(confirmPasswordInput, txt);
    }

    public void provideValidConfirmPassword() {
        provideConfirmPassword(VALID_PASSWORD);
    }

    public void providePublicInfo(String txt) {
        typeTextIn(publicInfoTextArea, txt);
    }

    public void provideValidPublicInfo() {
        providePublicInfo(VALID_PUBLIC_INFO);
    }

    public void clickOnSignUpBtn() {
        clickOn(signUpBtn);
    }

    //SUPPORT METHODS
    public String getTitle() {
        return getElementText(titleRegForm);
    }

    public String getUsernameInputPlaceholder() {
        return getElementPlaceholderValue(usernameInput);
    }

    public String getEmailInputPlaceholder() {
        return getElementPlaceholderValue(emailInput);
    }

    public String getPasswordInputPlaceholder() {
        return getElementPlaceholderValue(passwordInput);
    }

    public String getConfirmPasswordInputPlaceholder() {
        return getElementPlaceholderValue(confirmPasswordInput);
    }

    public String getPublicInfoPlaceholder() {
        return getElementPlaceholderValue(publicInfoTextArea);
    }

    public String getSignUpBtnText() {
        return getElementText(signUpBtn);
    }

    public String getCurrentTime() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HHmmss");
        String formattedDateTime = now.format(formatter);
        return formattedDateTime;
    }

    public String generateRandomValidUsername() {
        String username = "Demo" + getCurrentTime();
        return username;
    }

    public String generateRandomValidEmail() {
        String email = "demo" + getCurrentTime() + "@gmail.com";
        return email;
    }

    //BOOLEANS
    public boolean isTitleShown() {
        return isElementPresented(titleRegForm);
    }

    public boolean isUsernameInputShown() {
        return isElementPresented(usernameInput);
    }

    public boolean isEmailInputShown() {
        return isElementPresented(emailInput);
    }

    public boolean isBirthDateInputShown() {
        return isElementPresented(birthDateInput);
    }

    public boolean isPasswordInputShown() {
        return isElementPresented(passwordInput);
    }

    public boolean isConfirmPasswordInputShown() {
        return isElementPresented(confirmPasswordInput);
    }

    public boolean isPublicInfoTextAreaShown() {
        return isElementPresented(publicInfoTextArea);
    }

    public boolean isSignUpBtnShown() {
        return isElementPresented(signUpBtn);
    }

    public boolean isSignUpBtnEnabled() {
        return signUpBtn.isEnabled();
    }
}

