package org.diana;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class NavBar extends BasePage{

    //ELEMENTS
    @FindBy(id = "homeIcon")
    private WebElement homeIcon;

    @FindBy(id = "nav-link-home")
    private WebElement homeLink;

    //when logged out
    @FindBy(id = "nav-link-login")
    private WebElement loginLink;

    @FindBy(id = "nav-link-profile")
    private WebElement profileLink;

    @FindBy(id = "nav-link-new-post")
    private WebElement newPostLink;

    @FindBy(id = "search-bar")
    private WebElement search;

    @FindBy(xpath = "//a/i[contains(@class, \"fas fa-sign-out-alt fa-lg\")]")
    private WebElement exitBtn;

    public NavBar(WebDriver driver, Logger log) {
        super(driver, log);
        PageFactory.initElements(driver, this);
    }

    //USER ACTIONS
    public void clickOnHomeIcon() {
        clickOn(homeIcon);
    }

    public void clickOnHomeLink() {
        clickOn(homeLink);
    }

    public void clickOnLoginLink() {
        clickOn(loginLink);
    }

    public void clickOnProfileLink() {
        clickOn(profileLink);
    }

    public void clickOnNewPostLink() {
        clickOn(newPostLink);
    }

    public void typeTextInSearch(String txt) {
        typeTextIn(search, txt);
    }

    public void clickOnLogoutBtn() {
        clickOn(exitBtn);
    }

    //BOOLEANS
    public boolean isHomeIconShown() {
        return isElementPresented(homeIcon);
    }

    public boolean isHomeLinkShown() {
        return isElementPresented(homeLink);
    }

    public boolean isLoginLinkShown() {
        return isElementPresented(loginLink);
    }

    public boolean isProfileLinkShown() {
        return isElementPresented(profileLink);
    }

    public boolean isNewPostLinkShown() {
        return isElementPresented(newPostLink);
    }

    public boolean isSearchShown() {
        return isElementPresented(search);
    }

    public boolean isExitBtnShown() {
        return isElementPresented(exitBtn);
    }
}
