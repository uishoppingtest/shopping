package com.shop.pages;

import static org.joor.Reflect.on;

public class PageFactory {

    public static <T extends Page> T open(final Class<T> pageClass) {
        return (T) at(pageClass).navigateTo();
    }

    public static <T extends Page> T at(final Class<T> pageClass) {
        return on(pageClass).create().get();
    }

}
