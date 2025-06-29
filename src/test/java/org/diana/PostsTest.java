package org.diana;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;

public class PostsTest extends BaseTest {

    File postPicture = new File("src/test/resources/upload/testUpload.jpg");

    @Test
    public void verifyUserCanCreateAPost() throws InterruptedException {
        LoginPage loginPage = new LoginPage(super.driver, log);
        loginPage.navigateToLoginPage();
        loginPage.loginAsValidUser();

        NavBar navBar = new NavBar(super.driver, log);
        navBar.clickOnNewPostLink();

        PostPage postPage = new PostPage(super.driver, log);

        postPage.uploadPicture(postPicture);
        postPage.provideCaption();
        postPage.clickOnCreatePostBtn();

        Assert.assertTrue(postPage.isSuccessfulPostToastMsgShown());
    }
}
