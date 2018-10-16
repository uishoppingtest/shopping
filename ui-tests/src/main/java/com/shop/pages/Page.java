package com.shop.pages;

import org.openqa.selenium.WebDriver;

import java.util.ResourceBundle;

import static com.shop.driver.WebDriverFactory.getDriver;
import static com.shop.pages.PageFactory.at;

public interface Page {

    WebDriver driver = getDriver();
    ResourceBundle rb = ResourceBundle.getBundle("config");

    default Page navigateTo() {
        return navigateTo(getPageUrl());
    }

    default String getPageUrl() {
        return rb.getString("url");
    }

    default CartWidget withCartWidget() {
        return at(CartWidget.class);
    }

    Page navigateTo(String url);
}
