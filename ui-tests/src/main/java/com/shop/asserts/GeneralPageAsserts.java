package com.shop.asserts;

import org.openqa.selenium.WebElement;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;

public class GeneralPageAsserts {

    private GeneralPageAsserts() {}

    public static void checkText(WebElement element, String text) {
        assertThat(element.getText(), containsString(text));
    }
}
