package org.diana;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class NavBar extends BasePage{

    //ELEMENTS
    @FindBy(id = "homeIcon")
    private WebElement homeIcon;

    @FindBy(id = "nav-link-home")
    private WebElement homeLinkNavBar;

    //when logged out
    @FindBy(id = "nav-link-login")
    private WebElement loginLinkNavBar;

    @FindBy(id = "nav-link-profile")
    private WebElement profileLinkNavBar;

    @FindBy(id = "nav-link-new-post")
    private WebElement newPostLinkNavBar;

    @FindBy(id = "search-bar")
    private WebElement searchNavBar;

    @FindBy(xpath = "//a/i[contains(@class, \"fas fa-sign-out-alt fa-lg\")]")
    private WebElement exitBtnNavBar;

    public NavBar(WebDriver driver, Logger log) {
        super(driver, log);
        PageFactory.initElements(driver, this);
    }

    //USER ACTIONS
    public void clickOnHomeIconNavBar() {
        clickOn(homeIcon);
    }

    public void clickOnHomeLinkNavBar() {
        clickOn(homeLinkNavBar);
    }

    public void clickOnLoginLinkNavBar() {
        clickOn(loginLinkNavBar);
    }

    public void clickOnProfileLinkNavBar() {
        clickOn(profileLinkNavBar);
    }

    public void clickOnNewPostLinkNavBar() {
        clickOn(newPostLinkNavBar);
    }

    public void typeTextInSearchNavBar(String txt) {
        typeTextIn(searchNavBar, txt);
    }

    public void clickOnLogoutBtnNavBar() {
        clickOn(exitBtnNavBar);
    }

    //BOOLEANS
    public boolean isHomeIconShown() {
        return isElementPresented(homeIcon);
    }

    public boolean isHomeLinkNavBarShown() {
        return isElementPresented(homeLinkNavBar);
    }

    public boolean isLoginLinkNavBarShown() {
        return isElementPresented(loginLinkNavBar);
    }

    public boolean isProfileLinkNavBarShown() {
        return isElementPresented(profileLinkNavBar);
    }

    public boolean isNewPostLinkNavBarShown() {
        return isElementPresented(newPostLinkNavBar);
    }

    public boolean isSearchNavBarShown() {
        return isElementPresented(searchNavBar);
    }

    public boolean isExitBtnNavBarShown() {
        return isElementPresented(exitBtnNavBar);
    }
}
