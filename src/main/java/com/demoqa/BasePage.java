package com.demoqa;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.FrameworkConstants;

import java.time.Duration;

public class BasePage {

    protected WebDriver driver;
    protected WebDriverWait wait;
    protected Actions actions;
    protected long timeoutSeconds = 20;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds));
        this.actions = new Actions(driver);
    }

    // common action methods
    public boolean isElementInViewport(WebDriver driver, WebElement element) {
        return (Boolean) ((JavascriptExecutor) driver).executeScript(
                "var rect = arguments[0].getBoundingClientRect();" +
                        "return rect.top >= 0 && rect.left >= 0 && rect.bottom <= (window.innerHeight || document.documentElement.clientHeight) && rect.right <= (window.innerWidth || document.documentElement.clientWidth);",
                element
        );
    }
    public void scrollToElement(WebDriver driver, WebElement element) {
        if (!isElementInViewport(driver, element)) {
            ((JavascriptExecutor) driver).executeScript(
                    "arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});",
                    element
            );
        }
    }
    public void scrollToElement(WebDriver driver, By locator) {
        WebElement element = driver.findElement(locator);
        if (!isElementInViewport(driver, element)) {
            ((JavascriptExecutor) driver).executeScript(
                    "arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});",
                    element
            );
        }
    }
    public void click(WebDriver driver, By locator) {
        WebElement ele = wait.until(ExpectedConditions.presenceOfElementLocated(locator));
        ele = wait.until(ExpectedConditions.visibilityOf(ele));
        ele = wait.until(ExpectedConditions.elementToBeClickable(ele));
        scrollToElement(driver,ele);
        actions.click(ele).perform();
    }
    public void doubleClick(WebDriver driver, By locator) {
        WebElement ele = wait.until(ExpectedConditions.presenceOfElementLocated(locator));
        ele = wait.until(ExpectedConditions.visibilityOf(ele));
        ele = wait.until(ExpectedConditions.elementToBeClickable(ele));
        scrollToElement(driver,ele);
        actions.doubleClick(ele).perform();
    }
    public void rightClick(WebDriver driver, By locator) {
        WebElement ele = wait.until(ExpectedConditions.presenceOfElementLocated(locator));
        ele = wait.until(ExpectedConditions.visibilityOf(ele));
        ele = wait.until(ExpectedConditions.elementToBeClickable(ele));
        scrollToElement(driver,ele);
        actions.contextClick(ele).perform();
    }
    public void sendKeys(WebDriver driver,  By locator, String value) {
        WebElement ele = driver.findElement(locator);
        scrollToElement(driver,ele);
        ele.sendKeys(value);
    }
    public String getText(WebDriver driver,  By locator) {
        WebElement ele = wait.until(ExpectedConditions.presenceOfElementLocated(locator));
        ele = wait.until(ExpectedConditions.visibilityOf(ele));
        ele = wait.until(ExpectedConditions.elementToBeClickable(ele));
        scrollToElement(driver,ele);
        return ele.getText();
    }
    public String getValue(WebDriver driver,  By locator, String value) {
        WebElement ele = wait.until(ExpectedConditions.presenceOfElementLocated(locator));
        ele = wait.until(ExpectedConditions.visibilityOf(ele));
        ele = wait.until(ExpectedConditions.elementToBeClickable(ele));
        scrollToElement(driver,ele);
        return ele.getAttribute("value");
    }
    public boolean isDisplayed(WebDriver driver, By locator) {
        try{
            WebElement ele = wait.until(ExpectedConditions.presenceOfElementLocated(locator));
            ele = wait.until(ExpectedConditions.visibilityOf(ele));
            ele = wait.until(ExpectedConditions.elementToBeClickable(ele));
            scrollToElement(driver,ele);
            return ele.isDisplayed();
        }catch (NoSuchElementException e){
            return false;
        }
    }
    public boolean isEnabled(WebDriver driver, By locator) {
        WebElement ele = wait.until(ExpectedConditions.presenceOfElementLocated(locator));
        ele = wait.until(ExpectedConditions.visibilityOf(ele));
        ele = wait.until(ExpectedConditions.elementToBeClickable(ele));
        scrollToElement(driver,ele);
        return ele.isEnabled();
    }
    public boolean isDisabled(WebDriver driver, By locator) {
        WebElement ele = wait.until(ExpectedConditions.presenceOfElementLocated(locator));
        ele = wait.until(ExpectedConditions.visibilityOf(ele));
        ele = wait.until(ExpectedConditions.elementToBeClickable(ele));
        scrollToElement(driver,ele);
        return !ele.isEnabled();
    }
    public void selectByVisibleText(WebDriver driver, By locator, String visibleText) {
        WebElement ele = wait.until(ExpectedConditions.presenceOfElementLocated(locator));
        ele = wait.until(ExpectedConditions.visibilityOf(ele));
        ele = wait.until(ExpectedConditions.elementToBeClickable(ele));
        scrollToElement(driver,ele);
        Select select = new Select(ele);
        select.selectByVisibleText(visibleText);
    }
    public void acceptAlert(WebDriver driver) {
        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        alert.accept();
    }
    public void dismissAlert(WebDriver driver) {
        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        alert.dismiss();
    }
    public String getAlertText(WebDriver driver) {
        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        return alert.getText();
    }
    public void sendTextToAlert(WebDriver driver, String text) {
        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        alert.sendKeys(text);
    }

    // common page methods

    public void clickOnSideBarLink(String topLinkText,String downLinkText){
        click(driver,By.xpath("//div[@class='header-text' and text()='"+topLinkText+"']"));
        click(driver,By.xpath("//ul//li//a//span[text()='"+downLinkText+"']"));
    }

}
