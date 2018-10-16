package com.shop.pages;

import org.openqa.selenium.WebDriver;

import static com.shop.driver.WebDriverFactory.getDriver;
import static com.shop.pages.PageFactory.at;

public interface Page {

    WebDriver driver = getDriver();

    default Page navigateTo() {
        return navigateTo(getPageUrl());
    }

    default String getPageUrl() {
        //TODO: move to properties file
        return "https://georgel8.wixsite.com/ait-ht";
    }

    default CartWidget withCartWidget() {
        return at(CartWidget.class);
    }

    Page navigateTo(String url);
}
