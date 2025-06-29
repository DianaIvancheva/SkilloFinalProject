package org.diana;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

import static org.diana.HomePage.HOMEPAGE_SUFIX;
import static org.diana.LoginPage.LOGIN_PAGE_SUFIX;

public class LoginFunctionalityTests extends BaseTest {

    private static final String TITLE_LOGIN_FORM = "Sign in";
    private static final String USERNAME_OR_EMAIL_INPUT_PLACEHOLDER = "Username or email";
    private static final String PASSWORD_INPUT_PLACEHOLDER = "Password";
    private static final String SIGN_IN_BTN_TEXT = "Sign in";

    @Test
    public void verifyLoginPageLayoutIsCorrect() {
        LoginPage loginPage = new LoginPage(super.driver, log);
        loginPage.navigateToLoginPage();

        String actualUrl = driver.getCurrentUrl();
        Assert.assertTrue(actualUrl.contains(LOGIN_PAGE_SUFIX));

        Assert.assertTrue(loginPage.isTitleShown());
        String actualTitleLoginForm = loginPage.getTitle();
        Assert.assertEquals(actualTitleLoginForm, TITLE_LOGIN_FORM);

        Assert.assertTrue(loginPage.isUsernameOrEmailInputShown());
        String actualUsernameOrEmailInputPlaceholder = loginPage.getUsernameOrEmailInputPlaceholder();
        Assert.assertEquals(actualUsernameOrEmailInputPlaceholder, USERNAME_OR_EMAIL_INPUT_PLACEHOLDER);

        Assert.assertTrue(loginPage.isPasswordInputShown());
        String actualPasswordInputPlaceholder = loginPage.getPasswordInputPlaceholder();
        Assert.assertEquals(actualPasswordInputPlaceholder, PASSWORD_INPUT_PLACEHOLDER );

        Assert.assertTrue(loginPage.isRememberMeCheckboxShown());

        Assert.assertTrue(loginPage.isSignInBtnShown());
        String actualSignInBtnText = loginPage.getSignInBtnText();
        Assert.assertEquals(actualSignInBtnText, SIGN_IN_BTN_TEXT);

        Assert.assertTrue(loginPage.isRegisterLinkShown());
    }

    @Test
    public void verifyUserCanLoginWithValidCredentials() {
        LoginPage loginPage = new LoginPage(super.driver, log);

        loginPage.navigateToLoginPage();

        String actualUrl = driver.getCurrentUrl();
        Assert.assertTrue(actualUrl.contains(LOGIN_PAGE_SUFIX));

        loginPage.provideValidUsername();
        loginPage.provideValidPassword();

        loginPage.clickOnSignInBtn();

        HomePage homePage = new HomePage(super.driver, log);
        Assert.assertTrue(homePage.isSuccessfulLoginToastMsgShown());

        NavBar navBar = new NavBar(super.driver, log);
        Assert.assertTrue(navBar.isProfileLinkShown());

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.urlContains(HOMEPAGE_SUFIX));
        String actualLinkWhenLoggedIn = driver.getCurrentUrl();
        Assert.assertTrue(actualLinkWhenLoggedIn.contains(HOMEPAGE_SUFIX));

    }

    @Test
    public void verifyUserCannotLoginWithInvalidPassword() {
        LoginPage loginPage = new LoginPage(super.driver, log);

        loginPage.navigateToLoginPage();

        loginPage.provideValidUsername();
        loginPage.provideInvalidPassword();

        loginPage.clickOnSignInBtn();

        Assert.assertTrue(loginPage.isUnsuccessfulToastMsgShown());

        String actualUrlWhenNotLoggedIn = driver.getCurrentUrl();
        Assert.assertTrue(actualUrlWhenNotLoggedIn.contains(LOGIN_PAGE_SUFIX));
    }

}
