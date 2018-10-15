package com.shop.pages;

import org.openqa.selenium.WebDriver;

import static com.shop.driver.WebDriverFactory.getDriver;

public interface Page {

    WebDriver driver = getDriver();

    default Page navigateTo() {
        return navigateTo(getPageUrl());
    }

    default String getPageUrl() {
        //TODO: move to properties file
        return "https://georgel8.wixsite.com/ait-ht";
    }

    Page navigateTo(String url);
}
