package com.shop.pages;

import com.shop.utils.WaitCondition;
import javaslang.control.Try;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class BasePage implements Page {

    private final WebDriver driver;
    private final WebDriverWait wait;

    public BasePage() {
        this.driver = Page.driver;
        //TODO: Move constant 10 to properties file
        wait = new WebDriverWait(driver, 10);
        //waitUntilPageLoads();
        Page.driver.switchTo().defaultContent();
    }

    @Override
    public Page navigateTo(String url) {
        Try.run(() -> driver.get(url)).onFailure(ex -> {
            throw new IllegalArgumentException("Unable to open " + url, ex);
        });
        return this;
    }

    public abstract NavigationBar withNavigationBar();

    protected void click(By locator) {
        click(locator, WaitCondition.enabled);
    }

    protected void click(By locator, WaitCondition condition) {
        waitFor(locator, condition).click();
    }

    protected void type(By locator, CharSequence text) {
        waitFor(locator, WaitCondition.visible).sendKeys(text);
    }

    protected void switchToFrame(By locator, WaitCondition condition) {
        driver.switchTo().frame(waitFor(locator, condition));
    }

    protected By byXPath(String xpath) {
        return By.xpath(xpath);
    }

    protected WebElement find(By locator, WaitCondition condition) {
        return waitFor(locator, condition);
    }

    protected void waitUntilPageLoads() {
        wait.until(executor -> String
                .valueOf(((JavascriptExecutor) executor).executeScript("return document.readyState"))
                .equals("complete"));
    }

    protected void moveToElement(By locator) {
        Actions action = new Actions(driver);
        action.moveToElement(find(locator, WaitCondition.visible)).pause(1000).build().perform();
    }

    protected void putText(By locator, String text) {
        Actions action = new Actions(driver);
        action.moveToElement(find(locator, WaitCondition.visible)).click().release()
                .sendKeys("\u0008").sendKeys(text).build().perform();
    }

    private WebElement waitFor(By locator, WaitCondition condition) {
        return wait.until(condition.getType().apply(locator));
    }
}