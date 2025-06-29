package org.diana;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

import static org.diana.HomePage.HOMEPAGE_SUFIX;
import static org.diana.RegistrationPage.REGISTRATION_PAGE_SUFIX;
public class RegistrationTests extends BaseTest {

    private static final String TITLE_REG_PAGE = "Sign up";
    private static final String USERNAME_INPUT_PLACEHOLDER = "Username";
    private static final String EMAIL_INPUT_PLACEHOLDER = "Email";
    private static final String PASSWORD_INPUT_PLACEHOLDER = "Password";
    private static final String CONFIRM_PASSWORD_INPUT_PLACEHOLDER = "Confirm Password";
    private static final String PUBLIC_INFO_PLACEHOLDER = "Public info";
    private static final String SIGN_UP_BTN_TEXT = "Sign up";

    private static final String INVALID_EMAIL = "invalid";

    @Test
    public void verifyAllElementsArePresented() {
        RegistrationPage regPage = new RegistrationPage(super.driver, log);
        regPage.navigateTo();

        String actualUrl = driver.getCurrentUrl();
        Assert.assertTrue(actualUrl.contains(REGISTRATION_PAGE_SUFIX));

        Assert.assertTrue(regPage.isTitleShown());
        String actualTitle = regPage.getTitle();
        Assert.assertEquals(actualTitle, TITLE_REG_PAGE);

        Assert.assertTrue(regPage.isUsernameInputShown());
        String actualUsernamePlaceholder = regPage.getUsernameInputPlaceholder();
        Assert.assertEquals(actualUsernamePlaceholder, USERNAME_INPUT_PLACEHOLDER);

        Assert.assertTrue(regPage.isEmailInputShown());
        String actualEmailPlaceholder = regPage.getEmailInputPlaceholder();
        Assert.assertEquals(actualEmailPlaceholder, EMAIL_INPUT_PLACEHOLDER);

        Assert.assertTrue(regPage.isBirthDateInputShown());

        Assert.assertTrue(regPage.isPasswordInputShown());
        String actualPasswordPlaceholder = regPage.getPasswordInputPlaceholder();
        Assert.assertEquals(actualPasswordPlaceholder, PASSWORD_INPUT_PLACEHOLDER);

        Assert.assertTrue(regPage.isConfirmPasswordInputShown());
        String actualConfirmPasswordPlaceholder = regPage.getConfirmPasswordInputPlaceholder();
        Assert.assertEquals(actualConfirmPasswordPlaceholder, CONFIRM_PASSWORD_INPUT_PLACEHOLDER);

        Assert.assertTrue(regPage.isPublicInfoTextAreaShown());
        String actualPublicInfoTextAreaPlaceholder = regPage.getPublicInfoPlaceholder();
        Assert.assertEquals(actualPublicInfoTextAreaPlaceholder, PUBLIC_INFO_PLACEHOLDER);

        Assert.assertTrue(regPage.isSignUpBtnShown());
        String actualSignUpBtnText = regPage.getSignUpBtnText();
        Assert.assertEquals(actualSignUpBtnText, SIGN_UP_BTN_TEXT);
    }

    @Test
    public void verifyUserCanRegisterWithValidCredentials() {
        RegistrationPage regPage = new RegistrationPage(super.driver, log);
        regPage.navigateTo();

        regPage.provideValidUsername();
        regPage.provideValidEmail();
        regPage.provideValidBirthDate();
        regPage.provideValidPassword();
        regPage.provideValidConfirmPassword();
        regPage.provideValidPublicInfo();
        regPage.clickOnSignUpBtn();

        HomePage homePage = new HomePage(super.driver, log);
        Assert.assertTrue(homePage.isSuccessfulRegistrationToastMsgShown(), "Toast msg is not shown");

        NavBar navBar = new NavBar(super.driver, log);
        Assert.assertTrue(navBar.isProfileLinkShown(), "Profile link is not shown");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.urlContains(HOMEPAGE_SUFIX));
        String actualUrlWhenLoggedIn = driver.getCurrentUrl();
        Assert.assertTrue(actualUrlWhenLoggedIn.contains(HOMEPAGE_SUFIX), "URL is not homepage URL");
    }

    @Test
    public void verifyUserCannotRegisterWithInvalidEmail() {
        RegistrationPage regPage = new RegistrationPage(super.driver, log);

        regPage.navigateTo();

        regPage.provideValidUsername();
        regPage.provideEmail(INVALID_EMAIL);
        regPage.provideValidBirthDate();
        regPage.provideValidPassword();
        regPage.provideValidConfirmPassword();
        regPage.provideValidPublicInfo();

        Assert.assertFalse(regPage.isSignUpBtnEnabled());
    }
}
