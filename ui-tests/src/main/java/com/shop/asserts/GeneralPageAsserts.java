package com.shop.asserts;

import org.openqa.selenium.WebElement;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class GeneralPageAsserts {

    private GeneralPageAsserts() {}

    public static void isPageOpen(WebElement element) {
        assertThat(element.isDisplayed(), is(equalTo(true)));
    }

    public static void checkText(WebElement element, String text) {
        assertThat(element.getText(), containsString(text));
    }
}