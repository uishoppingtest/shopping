package com.shop.pages;

import com.shop.utils.WaitCondition;
import org.openqa.selenium.By;


import static com.shop.asserts.GeneralPageAsserts.isPageOpen;
import static com.shop.pages.PageFactory.at;

public class HomePage extends BasePage {

    private static final By SHOP_BUTTON = By.id("comp-jhalo8eilabel");

    public StorePage navigateToStore() {
        click(SHOP_BUTTON, WaitCondition.visible);
        return at(StorePage.class);
    }

    @Override
    public void isOpen() {
        isPageOpen(find(SHOP_BUTTON));
    }

}
