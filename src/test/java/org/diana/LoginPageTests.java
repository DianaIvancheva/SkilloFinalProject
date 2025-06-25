package org.diana;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

import static org.diana.HomePage.HOMEPAGE_SUFIX;
import static org.diana.LoginPage.LOGIN_PAGE_SUFIX;

public class LoginPageTests extends BaseTest {

    private static final String TITLE_LOGIN_FORM = "Sign in";
    private static final String USERNAME_OR_EMAIL_INPUT_PLACEHOLDER = "Username or email";
    private static final String PASSWORD_INPUT_PLACEHOLDER = "Password";
    private static final String SIGN_IN_BTN_TEXT = "Sign in";

    @Test
    public void verifyAllElementsArePresented() {
        LoginPage loginPage = new LoginPage(super.driver, log);
        loginPage.navigateToLoginPage();

        String actualUrl = driver.getCurrentUrl();
        Assert.assertTrue(actualUrl.contains(LOGIN_PAGE_SUFIX));

        Assert.assertTrue(loginPage.isTitleLoginFormShown());
        String actualTitleLoginForm = loginPage.getTitleLoginPage();
        Assert.assertEquals(actualTitleLoginForm, TITLE_LOGIN_FORM);

        Assert.assertTrue(loginPage.isUsernameOrEmailInputLoginFormShown());
        String actualUsernameOrEmailInputPlaceholder = loginPage.getUsernameOrEmailInputPlaceholderLoginForm();
        Assert.assertEquals(actualUsernameOrEmailInputPlaceholder, USERNAME_OR_EMAIL_INPUT_PLACEHOLDER);

        Assert.assertTrue(loginPage.isPasswordInputLoginFormShown());
        String actualPasswordInputPlaceholder = loginPage.getPasswordInputPlaceholderLoginForm();
        Assert.assertEquals(actualPasswordInputPlaceholder, PASSWORD_INPUT_PLACEHOLDER );

        Assert.assertTrue(loginPage.isRememberMeCheckboxLoginFormShown());

        Assert.assertTrue(loginPage.isSignInBtnLoginFormShown());
        String actualSignInBtnText = loginPage.getSignInBtnTextLoginForm();
        Assert.assertEquals(actualSignInBtnText, SIGN_IN_BTN_TEXT);

        Assert.assertTrue(loginPage.isRegisterLinkLoginFormShown());

    }
    @Test
    public void verifyUserCanLoginWithValidCredentials() {
        LoginPage loginPage = new LoginPage(super.driver, log);

        loginPage.navigateToLoginPage();

        String actualUrl = driver.getCurrentUrl();
        Assert.assertTrue(actualUrl.contains(LOGIN_PAGE_SUFIX));

        loginPage.provideValidUsernameLoginForm();
        loginPage.provideValidPasswordLoginForm();

        loginPage.clickOnSignInBtnLoginForm();

        HomePage homePage = new HomePage(super.driver, log);
        Assert.assertTrue(homePage.isSuccessfulLoginToastMsgShown());

        NavBar navBar = new NavBar(super.driver, log);
        Assert.assertTrue(navBar.isProfileLinkNavBarShown());

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.urlContains(HOMEPAGE_SUFIX));
        String actualLinkWhenLoggedIn = driver.getCurrentUrl();
        Assert.assertTrue(actualLinkWhenLoggedIn.contains(HOMEPAGE_SUFIX));

    }

    @Test
    public void verifyUserCannotLoginWithInvalidPassword() {
        LoginPage loginPage = new LoginPage(super.driver, log);

        loginPage.navigateToLoginPage();

        loginPage.provideValidUsernameLoginForm();
        loginPage.provideInvalidPasswordLoginForm();

        loginPage.clickOnSignInBtnLoginForm();

        Assert.assertTrue(loginPage.isUnsuccessfulToastMsgShown());

        String actualUrlWhenNotLoggedIn = driver.getCurrentUrl();
        Assert.assertTrue(actualUrlWhenNotLoggedIn.contains(LOGIN_PAGE_SUFIX));
    }

}
