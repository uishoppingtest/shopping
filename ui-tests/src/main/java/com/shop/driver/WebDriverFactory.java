package com.shop.driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.ResourceBundle;

import static com.google.common.base.Predicates.isNull;
import static javaslang.API.*;
import static javaslang.Predicates.*;


public class WebDriverFactory {

    public static final String FIREFOX = "firefox";
    public static final String CHROME = "chrome";

    private static final ResourceBundle RB = ResourceBundle.getBundle("config");

    public static WebDriver getDriver() {
        return Match(RB.getString("browser")).of(
                Case(anyOf(isNull(), String::isEmpty), () -> { throw new IllegalStateException("'browser' property is missing!"); }),
                Case(CHROME::equalsIgnoreCase, () -> new ChromeDriver()),
                Case(FIREFOX::equalsIgnoreCase, () -> new FirefoxDriver()),
                Case($(), browser -> { throw new IllegalArgumentException(browser + " browser is not supported!"); }));
    }
}
