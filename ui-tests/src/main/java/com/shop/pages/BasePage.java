package com.shop.pages;

import com.shop.utils.WaitCondition;
import io.qameta.allure.Step;
import javaslang.control.Try;
import jdk.nashorn.internal.objects.annotations.SpecializedFunction;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public abstract class BasePage implements Page {

    private final WebDriver driver;
    //private final WebDriverWait wait;
    private final FluentWait<WebDriver> wait;

    public BasePage() {
        this.driver = Page.driver;
        //TODO: Move constant 10 to properties file
        wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(5))
                .pollingEvery(Duration.ofSeconds(1))
                .ignoring(NoSuchElementException.class);
        Page.driver.switchTo().defaultContent();
    }

    @Override
    @Step("Navigare to url {url}")
    public Page navigateTo(String url) {
        Try.run(() -> driver.get(url)).onFailure(ex -> {
            throw new IllegalArgumentException("Unable to open " + url, ex);
        });
        return this;
    }

    public abstract void isOpen();

    @Step("Click on element by locator {locator}")
    protected void click(By locator) {
        click(locator, WaitCondition.enabled);
    }

    @Step("Click on element by locator {locator} with condition {condition}")
    protected void click(By locator, WaitCondition condition) {
        waitFor(locator, condition).click();
    }

    @Step("Switch to frame be locator {locator} with condition {condition}")
    protected void switchToFrame(By locator, WaitCondition condition) {
        driver.switchTo().frame(waitFor(locator, condition));
    }

    protected By byXPath(String xpath) {
        return By.xpath(xpath);
    }

    @Step("Find element by locator {locator}")
    protected WebElement find(By locator) {
        return waitFor(locator, WaitCondition.visible);
    }

    @Step("Find element by locator {locator} with condition {condition}")
    protected WebElement find(By locator, WaitCondition condition) {
        return waitFor(locator, condition);
    }

    @Step("Move to element with locator {locator}")
    protected void moveToElement(By locator) {
        Actions action = new Actions(driver);
        action.moveToElement(find(locator, WaitCondition.visible)).pause(1000).build().perform();
    }

    @Step("Populate text {text} in element by locator {locator}")
    protected void putText(By locator, String text) {
        Actions action = new Actions(driver);
        action.moveToElement(find(locator, WaitCondition.visible)).click().release()
                .sendKeys("\u0008").sendKeys(text).build().perform();
    }

    @Step("Wait for element invisibility {locator}")
    protected void waitForInvisibility(By locator) {
        wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }

    @Step("Check if element {locator} is present")
    protected boolean isElementPresent(By locator) {
        try {
            driver.findElement(locator);
            return true;
        } catch (NoSuchElementException ex) {
            return false;
        }
    }

    protected boolean waitForJStoLoad() {
        JavascriptExecutor js = (JavascriptExecutor)driver;
        // wait for jQuery to load
        ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                try {
                    return ((Long)js.executeScript("return jQuery.active") == 0);
                }
                catch (Exception e) {
                    return true;
                }
            }
        };

        // wait for Javascript to load
        ExpectedCondition<Boolean> jsLoad = new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                return js.executeScript("return document.readyState")
                        .toString().equals("complete");
            }
        };

        return wait.until(jQueryLoad) && wait.until(jsLoad);
    }

    private WebElement waitFor(By locator, WaitCondition condition) {
        return wait.until(condition.getType().apply(locator));
    }
}
