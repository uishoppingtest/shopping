package com.shop.asserts;

import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class PositionAsserts {

    private PositionAsserts() {}

    public static void checkElementIsOnTheRightSide(WebElement element, WebDriver driver) {
        Point elementLocation = element.getLocation();
        int elementX = elementLocation.getX();
        int windowWidth = driver.manage().window().getSize().width;
        assertThat(elementX > windowWidth / 2, is(true));
    }
}
