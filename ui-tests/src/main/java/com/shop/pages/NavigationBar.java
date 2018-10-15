package com.shop.pages;

import org.openqa.selenium.By;

import static com.shop.asserts.GeneralPageAssers.isPageOpen;
import static com.shop.pages.PageFactory.at;

public class NavigationBar extends BasePage {

    private final By STORES_LINK = By.xpath("//a[@role='button']//p[text()='STORES']");
    private final By SITE_NAVIGATION = By.xpath("//ul[@aria-label='Site navigation']");

    public NavigationBar() {
        super();
        isOpen();
    }

    @Override
    public void isOpen() {
        isPageOpen(find(SITE_NAVIGATION));
    }

    public ShopPage navigateToShop() throws InterruptedException {
        //TODO: fix this shit. Wait for animation (close cart) to be finished
        Thread.sleep(1000);
        click(STORES_LINK);
        return at(ShopPage.class);
    }

    @Override
    public NavigationBar withNavigationBar() {
        throw new UnsupportedOperationException("Navigation bar is not available from Navigation bar!");
    }
}
