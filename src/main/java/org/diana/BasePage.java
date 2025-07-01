package org.diana;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


import java.time.Duration;

public class BasePage {

    public static final String BASE_URL = "http://training.skillo-bg.com:4300";

    protected WebDriver driver;
    protected Logger log;
    protected WebDriverWait wait;

    public BasePage(WebDriver driver, Logger log) {
        this.driver = driver;
        this.log = log;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void navigateTo(String pageURLSUFIX) {
        String url = BASE_URL + pageURLSUFIX;
        driver.get(url);

        log.info("The user has navigated to: " + url);
    }

    public void clickOn(WebElement elm) {
        waitForVisibility(elm);
        waitForClickability(elm);
        elm.click();

        log.info("The user has clicked on: " + elm);
    }

    public void typeTextIn(WebElement elm, String txt) {
        waitForVisibility(elm);
        waitForClickability(elm);
        elm.clear();
        elm.sendKeys(txt);

        log.info("The user has provided text: " + txt + " in element: " + elm);
    }

    public String getElementText(WebElement element){
        wait.until(ExpectedConditions.visibilityOf(element));
        String elementText = element.getText();

        log.info("# CONFIRM THE WEB ELEMENT TEXT IS " + elementText);
        return elementText;
    }

    public String getElementAttributeValue(WebElement element){
        wait.until(ExpectedConditions.visibilityOf(element));
        String elementText = element.getAttribute("value");

        log.info("# CONFIRM THE WEB ELEMENT ATTRIBUTE VALUE IS " + elementText);
        return elementText;
    }

    public String getElementPlaceholderValue(WebElement element){
        wait.until(ExpectedConditions.visibilityOf(element));
        String elementText = element.getAttribute("placeholder");

        log.info("# CONFIRM THE WEB ELEMENT PLACEHOLDER VALUE IS " + elementText);
        return elementText;
    }

    // VERIFICATIONS BOOLEANS
    public boolean isElementPresented(WebElement element) {
        boolean isWebElementShown = false;
        String locatorInfo = locatorInfo(element);

        log.info("[ACTION] Verifying visibility of element: {}", locatorInfo);
        try {
            wait.until(ExpectedConditions.visibilityOf(element));
            isWebElementShown = true;
            log.info("[PASS] Element is visible: {}", locatorInfo);
        } catch (TimeoutException e) {
            log.warn("[FAIL] Element NOT visible: {}", locatorInfo);
        }
        return isWebElementShown;
    }

    public boolean isElementClickable(WebElement element){
        boolean isElementClickable = false;
        String locatorInfo = locatorInfo(element);
        log.info("[ACTION] The user is verifying if web element is shown with locator: info ", locatorInfo);
        try {
            wait.until(ExpectedConditions.elementToBeClickable(element));
            isElementClickable = true;
            log.info("# [PASS] The web element is shown with info: " + locatorInfo);
        } catch (TimeoutException e) {
            log.error("!!! [FAIL] ERROR NOT CLICKABLE Web element with: "+ locatorInfo);
        }
        return isElementClickable;
    }

    private String locatorInfo(WebElement elm) {
        try {
            String raw = elm.toString();
            String locatorPart = raw.substring(raw.indexOf("->") + 2, raw.length() - 1).trim();
            // locatorPart e например: "xpath: //div[@id='abc']"
            int colonIndex = locatorPart.indexOf(":");
            String strategy = locatorPart.substring(0, colonIndex).trim().toUpperCase();
            String expression = locatorPart.substring(colonIndex + 1).trim();

            return String.format("[BY: %s] [EXPR: %s]", strategy, expression);
        } catch (Exception e) {
            return "[Unknown Locator Info]";
        }
    }

    //waits
    public WebElement waitForVisibility(WebElement element) {
        return wait.until(ExpectedConditions.visibilityOf(element));
    }

    public WebElement waitForClickability(WebElement element) {
        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }
}
