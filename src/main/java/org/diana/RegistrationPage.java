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
    private WebElement usernameInputRegForm;

    @FindBy(xpath = "//input[contains(@type, \"email\")]")
    private WebElement emailInputRegForm;

    @FindBy(xpath = "//input[contains(@formcontrolname, \"birthDate\")]")
    private WebElement birthDateInputRegForm;

    @FindBy(id = "defaultRegisterFormPassword")
    private WebElement passwordInputRegForm;

    @FindBy(id = "defaultRegisterPhonePassword")
    private WebElement confirmPasswordInputRegForm;

    @FindBy(xpath = "//textarea[contains(@formcontrolname, \"publicInfo\")]")
    private WebElement publicInfoTextAreaRegForm;

    @FindBy(id = "sign-in-button")
    private WebElement signUpBtnRegForm;

    //CONSTRUCTOR
    public RegistrationPage(WebDriver driver, Logger log) {
        super(driver, log);
        PageFactory.initElements(driver, this);
    }

    //NAVIGATION
    public void navigateToRegPage() {
        navigateTo(REGISTRATION_PAGE_SUFIX);
    }

    //USER ACTIONS
    public void provideUsernameRegForm(String txt) {
        typeTextIn(usernameInputRegForm, txt);
    }

    public void provideValidUsernameRegForm() {
        provideUsernameRegForm(generateRandomValidUsername());
    }

    public void provideEmailRegForm(String txt) {
        typeTextIn(emailInputRegForm, txt);
    }

    public void provideValidEmailRegForm() {
        provideEmailRegForm(generateRandomValidEmail());
    }

    public void provideBirthDateRegForm(String txt) {
        typeTextIn(birthDateInputRegForm, txt);
    }

    public void provideValidBirthDateRegForm() {
        provideBirthDateRegForm(VALID_BIRTH_DATE);
    }

    public void providePasswordRegForm(String txt) {
        typeTextIn(passwordInputRegForm, txt);
    }

    public void provideValidPasswordRegForm() {
        providePasswordRegForm(VALID_PASSWORD);
    }

    public void provideConfirmPasswordRegForm(String txt) {
        typeTextIn(confirmPasswordInputRegForm, txt);
    }

    public void provideValidConfirmPasswordRegForm() {
        provideConfirmPasswordRegForm(VALID_PASSWORD);
    }

    public void providePublicInfoRegPage(String txt) {
        typeTextIn(publicInfoTextAreaRegForm, txt);
    }

    public void provideValidPublicInfoRegPage() {
        providePublicInfoRegPage(VALID_PUBLIC_INFO);
    }

    public void clickOnSignUpBtnRegForm() {
        clickOn(signUpBtnRegForm);
    }

    //SUPPORT METHODS
    public String getTitleRegPage() {
        return getElementText(titleRegForm);
    }

    public String getUsernameInputPlaceholder() {
        return getElementPlaceholderValue(usernameInputRegForm);
    }

    public String getEmailInputPlaceholder() {
        return getElementPlaceholderValue(emailInputRegForm);
    }

    public String getPasswordInputPlaceholder() {
        return getElementPlaceholderValue(passwordInputRegForm);
    }

    public String getConfirmPasswordInputPlaceholder() {
        return getElementPlaceholderValue(confirmPasswordInputRegForm);
    }

    public String getPublicInfoPlaceholder() {
        return getElementPlaceholderValue(publicInfoTextAreaRegForm);
    }

    public String getSignUpBtnText() {
        return getElementText(signUpBtnRegForm);
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
    public boolean isTitleRegPageShown() {
        return isElementPresented(titleRegForm);
    }

    public boolean isUsernameInputRegFormShown() {
        return isElementPresented(usernameInputRegForm);
    }

    public boolean isEmailInputRegFormShown() {
        return isElementPresented(emailInputRegForm);
    }

    public boolean isBirthDateInputRegFormShown() {
        return isElementPresented(birthDateInputRegForm);
    }

    public boolean isPasswordInputRegFormShown() {
        return isElementPresented(passwordInputRegForm);
    }

    public boolean isConfirmPasswordInputRegFormShown() {
        return isElementPresented(confirmPasswordInputRegForm);
    }

    public boolean isPublicInfoTextAreaRegFormShown() {
        return isElementPresented(publicInfoTextAreaRegForm);
    }

    public boolean isSignUpBtnRegFormShown() {
        return isElementPresented(signUpBtnRegForm);
    }
}
