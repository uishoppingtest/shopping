package com.shop.asserts;

import org.openqa.selenium.WebElement;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class CartAsserts {

    private CartAsserts() {}

    public static void checkCartSize(int actualSize, int expectedSize) {
        assertThat(actualSize, is(equalTo(expectedSize)));
    }

    public static void checkIsCartEmpty(WebElement cart) {
        assertThat(cart.getText(), is(equalTo("Cart is empty")));
    }
}
