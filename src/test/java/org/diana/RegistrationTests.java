package org.diana;

import org.testng.Assert;
import org.testng.annotations.Test;

import static org.diana.RegistrationPage.REGISTRATION_PAGE_SUFIX;
public class RegistrationTests extends BaseTest {

    private static final String TITLE_REG_PAGE = "Sign up";
    private static final String USERNAME_INPUT_PLACEHOLDER = "Username";
    private static final String EMAIL_INPUT_PLACEHOLDER = "Email";
    private static final String PASSWORD_INPUT_PLACEHOLDER = "Password";
    private static final String CONFIRM_PASSWORD_INPUT_PLACEHOLDER = "Confirm Password";
    private static final String PUBLIC_INFO_PLACEHOLDER = "Public info";
    private static final String SIGN_UP_BTN_TEXT = "Sign up";

    @Test
    public void verifyAllElementsArePresented() {
        RegistrationPage regPage = new RegistrationPage(super.driver, log);
        regPage.navigateToRegPage();

        String actualUrl = driver.getCurrentUrl();
        Assert.assertTrue(actualUrl.contains(REGISTRATION_PAGE_SUFIX));

        Assert.assertTrue(regPage.isTitleRegPageShown());
        String actualTitle = regPage.getTitleRegPage();
        Assert.assertEquals(actualTitle, TITLE_REG_PAGE);

        Assert.assertTrue(regPage.isUsernameInputRegFormShown());
        String actualUsernamePlaceholder = regPage.getUsernameInputPlaceholder();
        Assert.assertEquals(actualUsernamePlaceholder, USERNAME_INPUT_PLACEHOLDER);

        Assert.assertTrue(regPage.isEmailInputRegFormShown());
        String actualEmailPlaceholder = regPage.getEmailInputPlaceholder();
        Assert.assertEquals(actualEmailPlaceholder, EMAIL_INPUT_PLACEHOLDER);

        Assert.assertTrue(regPage.isBirthDateInputRegFormShown());

        Assert.assertTrue(regPage.isPasswordInputRegFormShown());
        String actualPasswordPlaceholder = regPage.getPasswordInputPlaceholder();
        Assert.assertEquals(actualPasswordPlaceholder, PASSWORD_INPUT_PLACEHOLDER);

        Assert.assertTrue(regPage.isConfirmPasswordInputRegFormShown());
        String actualConfirmPasswordPlaceholder = regPage.getConfirmPasswordInputPlaceholder();
        Assert.assertEquals(actualConfirmPasswordPlaceholder, CONFIRM_PASSWORD_INPUT_PLACEHOLDER);

        Assert.assertTrue(regPage.isPublicInfoTextAreaRegFormShown());
        String actualPublicInfoTextAreaPlaceholder = regPage.getPublicInfoPlaceholder();
        Assert.assertEquals(actualPublicInfoTextAreaPlaceholder, PUBLIC_INFO_PLACEHOLDER);

        Assert.assertTrue(regPage.isSignUpBtnRegFormShown());
        String actualSignUpBtnText = regPage.getSignUpBtnText();
        Assert.assertEquals(actualSignUpBtnText, SIGN_UP_BTN_TEXT);
    }

    @Test
    public void verifyUserCanRegisterWithValidCredentials() {
        RegistrationPage regPage = new RegistrationPage(super.driver, log);
        regPage.navigateToRegPage();

        regPage.provideValidUsernameRegForm();
        regPage.provideValidEmailRegForm();
        regPage.provideValidBirthDateRegForm();
        regPage.provideValidPasswordRegForm();
        regPage.provideValidConfirmPasswordRegForm();
        regPage.provideValidPublicInfoRegPage();
        regPage.clickOnSignUpBtnRegForm();

        HomePage homePage = new HomePage(super.driver, log);
        Assert.assertTrue(homePage.isSuccessfulRegistrationToastMsgShown());
    }
}
