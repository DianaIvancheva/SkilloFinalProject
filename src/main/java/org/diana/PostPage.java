package org.diana;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.io.File;

public class PostPage extends BasePage {

    //CONSTANTS
    public static final String NEW_POST_SUFIX = "/posts/create";

    private String CAPTION = "Test!";

    //ELEMENTS
    @FindBy(xpath = "//app-create-post//h3")
    private WebElement titlePostForm;

    @FindBy(xpath = "//input[@type='file']")
    private WebElement fileUpload;

    @FindBy(id = "choose-file")
    private WebElement browseBtn;

    @FindBy(xpath = "//input[@formcontrolname = \"caption\"]")
    private WebElement captionField;

    @FindBy(xpath = "//input[@type = \"checkbox\"]")
    private WebElement publicOrPrivateCheckbox;

    @FindBy(id = "create-post")
    private WebElement createPostBtn;

    @FindBy(xpath = "//div[@aria-label=\"Post created!\"]")
    private WebElement successfulPostToastMsg;

    //CONSTRUCTOR
    public PostPage(WebDriver driver, Logger log) {
        super(driver, log);
        PageFactory.initElements(driver, this);
    }

    //USER ACTIONS
    public void uploadPicture(File file) {
        isElementPresented(fileUpload);
        fileUpload.sendKeys(file.getAbsolutePath());

        log.info("CONFIRMATION # The file was successfully uploaded.");
    }

    public void provideCaption() {
        typeTextIn(captionField, CAPTION);
    }

    public void clickOnCreatePostBtn() {
        clickOn(createPostBtn);
    }
    //SUPPORT METHODS
    //to be added

    //BOOLEANS
    public boolean isSuccessfulPostToastMsgShown() {
        return isElementPresented(successfulPostToastMsg);
    }
}
